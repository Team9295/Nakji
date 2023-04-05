// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.LoggingConstants;
import frc.robot.Constants.TelescopeConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.ShoulderConstants;
import frc.robot.Constants.WristBendConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.WristRotateConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.autonomous.TimeBasedAutoCommand;
import frc.robot.commands.autonomous.AutoBalanceCommand;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ShoulderCommands.ShoulderPositionCommand;
import frc.robot.commands.ShoulderCommands.ShoulderSpeedCommand;
import frc.robot.commands.TelescopeCommands.TelescopePositionCommand;
import frc.robot.commands.TelescopeCommands.TelescopeSpeedCommand;
import frc.robot.commands.TurretCommands.TurretPositionCommand;
import frc.robot.commands.TurretCommands.TurretSpeedCommand;
import frc.robot.commands.WristBendCommands.WristBendPositionCommand;
import frc.robot.commands.WristBendCommands.WristBendSpeedCommand;
import frc.robot.commands.WristRotateCommands.WristRotatePositionCommand;
import frc.robot.commands.WristRotateCommands.WristRotateSpeedCommand;
import frc.robot.commands.SuctionCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.TelescopeSubsystem;
import frc.robot.subsystems.SuctionSubsystem;
import frc.robot.subsystems.ShoulderSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WristBendSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.WristRotateSubsystem;

import frc.robot.Constants.ControllerConstants.Axis;
import frc.robot.Constants.ControllerConstants.Button;
import frc.robot.Constants.ControllerConstants.DPad;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final TelescopeSubsystem m_telescopeSubsystem = new TelescopeSubsystem();
  private final ShoulderSubsystem m_shoulderSubsystem = new ShoulderSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final WristBendSubsystem m_wristBendSubsystem = new WristBendSubsystem();
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  private final WristRotateSubsystem m_wristRotateSubsystem = new WristRotateSubsystem();
  private final SuctionSubsystem m_suctionSubsystem = new SuctionSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);
  private final Joystick m_operatorController = new Joystick(ControllerConstants.kOperatorControllerPort);

  private final SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  private final ShuffleboardLogging[] m_subsystems = { m_driveSubsystem, m_shoulderSubsystem, m_suctionSubsystem,
      m_telescopeSubsystem, m_turretSubsystem, m_wristBendSubsystem, m_wristRotateSubsystem };

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    configureShuffleboard();
    m_autoChooser.addOption("Drive Auto", new ParallelCommandGroup(new TimeBasedAutoCommand(m_driveSubsystem, 2, -.3),
        new TimeBasedAutoCommand(m_driveSubsystem, 4, .3)));
    m_autoChooser.addOption("Balance Auto", new AutoBalanceCommand());
    SmartDashboard.putData(m_autoChooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    /*
     * =========================================
     * | DRIVER CONTROLS |
     * =========================================
     */
    m_driveSubsystem.setDefaultCommand(
        new ArcadeDriveCommand(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kRightY),
            () -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
            () -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));

    new JoystickButton(m_driverController, Button.kRightBumper).whileTrue(new ArcadeDriveCommand(m_driveSubsystem,
        () -> 0.0, () -> -DriveConstants.kFineTurningSpeed,
        () -> DriveConstants.kFineTurningSpeed));

    new JoystickButton(m_driverController, Button.kLeftBumper).whileTrue(new ArcadeDriveCommand(m_driveSubsystem,
        () -> 0.0, () -> DriveConstants.kFineTurningSpeed,
        () -> -DriveConstants.kFineTurningSpeed));

    new POVButton(m_driverController, DPad.kLeft).toggleOnTrue(new SuctionCommand(m_suctionSubsystem));

    // Driver direct slow shoulder control to do

    // Driver direct slow turret control to do

    /*
     * =========================================
     * | OPERATOR CONTROLS |
     * =========================================
     */
    // move shoulder up and down
    m_shoulderSubsystem.setDefaultCommand(
        new ShoulderPositionCommand(m_shoulderSubsystem, () -> m_operatorController.getRawAxis(Axis.kRightY),
            () -> m_driverController.getRawAxis(Axis.kLeftY)));

    new JoystickButton(m_operatorController, Button.kRightTriggerButton).whileTrue(
        new ShoulderSpeedCommand(m_shoulderSubsystem, () -> -m_operatorController.getRawAxis(Axis.kRightY) / 2));
    // m_shoulderSubsystem.setDefaultCommand(
    // new ShoulderSpeedCommand(m_shoulderSubsystem, () ->
    // m_operatorController.getRawAxis(Axis.kRightY)));
    // arm extend arm retract
    // m_telescopeSubsystem.setDefaultCommand(
    // new TelescopeSpeedCommand(m_telescopeSubsystem, () ->
    // m_operatorController.getRawAxis(Axis.kLeftY)));
    m_telescopeSubsystem.setDefaultCommand(
        new TelescopePositionCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY),
            TelescopeConstants.kMaxPosition));

    new JoystickButton(m_operatorController, Button.kLeftTriggerButton).whileTrue(
        new TelescopeSpeedCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY)));
    // turn base left turn base right
    // new JoystickButton(m_operatorController, Button.kLeftBumper).whileTrue(new
    // TurretSpeedCommand(m_turretSubsystem, TurretConstants.kSpeedLimitFactor));
    // new JoystickButton(m_operatorController, Button.kRightBumper).whileTrue(new
    // TurretSpeedCommand(m_turretSubsystem, -TurretConstants.kSpeedLimitFactor));

    // POSITION CONTROL
    new JoystickButton(m_operatorController, Button.kLeftBumper)
        .whileTrue(new TurretPositionCommand(m_turretSubsystem, TurretConstants.kMaxPosition));
    new JoystickButton(m_operatorController, Button.kRightBumper)
        .whileTrue(new TurretPositionCommand(m_turretSubsystem, -TurretConstants.kMaxPosition));

    // rotate wrist left rotate wrist right
    new POVButton(m_operatorController, DPad.kLeft)
        .whileTrue(new WristRotateSpeedCommand(m_wristRotateSubsystem, WristRotateConstants.kSpeedLimitFactor));
    new POVButton(m_operatorController, DPad.kRight)
        .whileTrue(new WristRotateSpeedCommand(m_wristRotateSubsystem, -WristRotateConstants.kSpeedLimitFactor));

    // POSITION CONTROL
    // new JoystickButton(m_operatorController, Button.kLeftMenu).whileTrue(new
    // WristRotatePositionCommand(m_wristRotateSubsystem,
    // WristRotateConstants.kMaxPosition));
    // new JoystickButton(m_operatorController, Button.kRightMenu).whileTrue(new
    // WristRotatePositionCommand(m_wristRotateSubsystem,
    // -WristRotateConstants.kMaxPosition));

    // bend wrist up bend wrist down
    new POVButton(m_operatorController, DPad.kUp).whileTrue(new WristBendSpeedCommand(m_wristBendSubsystem, 1));
    new POVButton(m_operatorController, DPad.kDown).whileTrue(new WristBendSpeedCommand(m_wristBendSubsystem, -1));

    // preset positions
    // base
    new JoystickButton(m_operatorController, Button.kA).onTrue(new ParallelCommandGroup(
        new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kBasePos),
        new TelescopePositionCommand(m_telescopeSubsystem, () -> -1.0,
            TelescopeConstants.kBasePos),
        // new WristRotatePositionCommand(m_wristRotateSubsystem, 0),
        new WristBendPositionCommand(m_wristBendSubsystem, WristBendConstants.kBasePos),
        new TurretPositionCommand(m_turretSubsystem, 0)));
    // mid
    new JoystickButton(m_operatorController, Button.kX).onTrue(new ParallelCommandGroup(
        new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kMidPos),
        new TelescopePositionCommand(m_telescopeSubsystem, () -> -1.0,
            TelescopeConstants.kMidPos),
        // new WristRotatePositionCommand(m_wristRotateSubsystem, 0),
        new WristBendPositionCommand(m_wristBendSubsystem, WristBendConstants.kMidPos),
        new TurretPositionCommand(m_turretSubsystem, 0)));
    // top
    new JoystickButton(m_operatorController, Button.kY).onTrue(new ParallelCommandGroup(
        new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kTopPos),
        new TelescopePositionCommand(m_telescopeSubsystem, () -> -1.0,
            TelescopeConstants.kTopPos),
        // new WristRotatePositionCommand(m_wristRotateSubsystem, 0),
        new WristBendPositionCommand(m_wristBendSubsystem, WristBendConstants.kTopPos),
        new TurretPositionCommand(m_turretSubsystem, 0)));
    // retract (ON DRIVER)
    new JoystickButton(m_driverController, Button.kB).onTrue(new ParallelCommandGroup(
        new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kRetractPos),
        new TelescopePositionCommand(m_telescopeSubsystem, () -> 1.0,
            TelescopeConstants.kRetractPos),
        // new WristRotatePositionCommand(m_wristRotateSubsystem, 0),
        new WristBendPositionCommand(m_wristBendSubsystem, 0),
        new TurretPositionCommand(m_turretSubsystem, 0)));
    
        //human player position
    new JoystickButton(m_operatorController, Button.kB).onTrue(new ParallelCommandGroup(
          new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kHumanPlayerPos),
          new TelescopePositionCommand(m_telescopeSubsystem, () -> 1.0,
              TelescopeConstants.kHumanPlayerPos),
          // new WristRotatePositionCommand(m_wristRotateSubsystem, 0),
          new WristBendPositionCommand(m_wristBendSubsystem, 0),
          new TurretPositionCommand(m_turretSubsystem, 0)));
  }

  public void configureShuffleboard() {
    for (int i = 0; i < m_subsystems.length; i++) {
      if (LoggingConstants.kSubsystems[i]) {
        m_subsystems[i].configureShuffleboard(true);
      }
    }
  }

  public Command getAutonomousCommand() {
    return m_autoChooser.getSelected();
  }
}
