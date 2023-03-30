// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.TelescopeConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.WristBendConstants;
import frc.robot.Constants.RotatingBaseConstants;
import frc.robot.Constants.WristRotateConstants;
import frc.robot.commands.autonomous.autoBalance;
import frc.robot.commands.autonomous.simple;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.TelescopeSpeedCommand;
import frc.robot.commands.ShoulderSpeedCommand;
import frc.robot.commands.TurretSpeedCommand;
import frc.robot.commands.SuctionCommand; 
import frc.robot.commands.WristRotateCommand;
import frc.robot.commands.WristBendCommand;
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
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
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
  private final Joystick m_driverController =
      new Joystick(ControllerConstants.kDriverControllerPort);

  private final Joystick m_operatorController = 
      new Joystick(ControllerConstants.kOperatorControllerPort); 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //driver controls: drive and steer
    m_driveSubsystem.setDefaultCommand(
      new ArcadeDriveCommand(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kRightY),
          () -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
          () -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));
    new POVButton(m_driverController, DPad.kLeft).toggleOnTrue(new SuctionCommand(m_suctionSubsystem));
    //operator controls
    //move shoulder up and down
    m_shoulderSubsystem.setDefaultCommand(
      new ShoulderSpeedCommand(m_shoulderSubsystem, () -> m_operatorController.getRawAxis(Axis.kRightY)));
      //arm extend arm retract
    m_telescopeSubsystem.setDefaultCommand(
      new TelescopeSpeedCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY))); 
      //turn base left turn base right
      new JoystickButton(m_operatorController, Button.kLeftBumper).whileTrue(new TurretSpeedCommand(m_turretSubsystem, RotatingBaseConstants.kSpeedLimitFactor)); 
      new JoystickButton(m_operatorController, Button.kRightBumper).whileTrue(new TurretSpeedCommand(m_turretSubsystem, -RotatingBaseConstants.kSpeedLimitFactor));
      //rotate wrist left rotate wrist right  
      new JoystickButton(m_operatorController, Button.kLeftMenu).whileTrue(new WristRotateCommand(m_wristRotateSubsystem, WristRotateConstants.kSpeedLimitFactor)); 
      new JoystickButton(m_operatorController, Button.kRightMenu).whileTrue(new WristRotateCommand(m_wristRotateSubsystem, -WristRotateConstants.kSpeedLimitFactor)); 
      //bend wrist up bend wrist down
      new POVButton(m_operatorController, DPad.kUp).whileTrue(new WristBendCommand(m_wristBendSubsystem, 1));
      new POVButton(m_operatorController, DPad.kDown).whileTrue(new WristBendCommand(m_wristBendSubsystem, -1));
      
      //TODO: add the presets for a,x,and y levels -- create armToPosition command (command group?)
      /*
       * somethiing like this? (using kPIDSlot as placeholder will add constants for specific levels): 
       * 
       * new JoystickButton(m_operatorConroller, Button.kA).whileTrue(new ExtendLevelCommand(m_telescopeSubsystem, ArmExtendConstants.kPIDslot) && new ArmUpLevelCommand(m_shoulderSubsystem, ArmUpContants.kPIDSlot)); 
       * new JoystickButton(m_operatorConroller, Button.kX).whileTrue(new ExtendLevelCommand(m_telescopeSubsystem, ArmExtendConstants.kPIDslot) && new ArmUpLevelCommand(m_shoulderSubsystem, ArmUpContants.kPIDSlot));
       * new JoystickButton(m_operatorConroller, Button.kY).whileTrue(new ExtendLevelCommand(m_telescopeSubsystem, ArmExtendConstants.kPIDslot) && new ArmUpLevelCommand(m_shoulderSubsystem, ArmUpContants.kPIDSlot));
       * 
       */
      

  }



}
