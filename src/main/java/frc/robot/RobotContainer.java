// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.TelescopeConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.ShoulderConstants;
import frc.robot.Constants.WristBendConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.WristRotateConstants;
import frc.robot.Constants.DriveConstants;

import frc.robot.commands.autonomous.autoBalance;
import frc.robot.commands.autonomous.simple;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.TelescopePositionCommand;
import frc.robot.commands.TelescopeSpeedCommand;
import frc.robot.commands.ShoulderPositionCommand;
import frc.robot.commands.ShoulderSpeedCommand;
import frc.robot.commands.TurretPositionCommand;
import frc.robot.commands.TurretSpeedCommand;
import frc.robot.commands.WristRotatePositionCommand;
import frc.robot.commands.WristRotateSpeedCommand;
import frc.robot.commands.WristBendPositionCommand;
import frc.robot.commands.WristBendSpeedCommand;
import frc.robot.commands.SuctionCommand; 


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

import edu.wpi.first.wpilibj.Relay;



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
    /* =========================================
     * |            DRIVER CONTROLS            |
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

    

    /* =========================================
     * |           OPERATOR CONTROLS           |
     * ========================================= 
     */
    //move shoulder up and down
    m_shoulderSubsystem.setDefaultCommand(
      new ShoulderPositionCommand(m_shoulderSubsystem, () -> -m_operatorController.getRawAxis(Axis.kRightY), () -> -m_driverController.getRawAxis(Axis.kLeftY)));
      
      new JoystickButton(m_operatorController, Button.kRightTriggerButton).whileTrue(
        new ShoulderSpeedCommand(m_shoulderSubsystem, () -> m_operatorController.getRawAxis(Axis.kRightY)/2));
      // m_shoulderSubsystem.setDefaultCommand(
      // new ShoulderSpeedCommand(m_shoulderSubsystem, () -> m_operatorController.getRawAxis(Axis.kRightY)));
      //arm extend arm retract
    // m_telescopeSubsystem.setDefaultCommand(
    //   new TelescopeSpeedCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY))); 
    m_telescopeSubsystem.setDefaultCommand(
      new TelescopePositionCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY), TelescopeConstants.kMaxPosition));
    
      new JoystickButton(m_operatorController, Button.kLeftTriggerButton).whileTrue(
          new TelescopeSpeedCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY)));
      //turn base left turn base right
      // new JoystickButton(m_operatorController, Button.kLeftBumper).whileTrue(new TurretSpeedCommand(m_turretSubsystem, TurretConstants.kSpeedLimitFactor)); 
      // new JoystickButton(m_operatorController, Button.kRightBumper).whileTrue(new TurretSpeedCommand(m_turretSubsystem, -TurretConstants.kSpeedLimitFactor));

      //POSITION CONTROL
      new JoystickButton(m_operatorController, Button.kLeftBumper).whileTrue(new TurretPositionCommand(m_turretSubsystem, TurretConstants.kMaxPosition)); 
      new JoystickButton(m_operatorController, Button.kRightBumper).whileTrue(new TurretPositionCommand(m_turretSubsystem, -TurretConstants.kMaxPosition));

      //rotate wrist left rotate wrist right  
      new JoystickButton(m_operatorController, Button.kLeftMenu).whileTrue(new WristRotateSpeedCommand(m_wristRotateSubsystem, WristRotateConstants.kSpeedLimitFactor)); 
      new JoystickButton(m_operatorController, Button.kRightMenu).whileTrue(new WristRotateSpeedCommand(m_wristRotateSubsystem, -WristRotateConstants.kSpeedLimitFactor)); 
      
      //POSITION CONTROL
      // new JoystickButton(m_operatorController, Button.kLeftMenu).whileTrue(new WristRotatePositionCommand(m_wristRotateSubsystem, WristRotateConstants.kMaxPosition)); 
      // new JoystickButton(m_operatorController, Button.kRightMenu).whileTrue(new WristRotatePositionCommand(m_wristRotateSubsystem, -WristRotateConstants.kMaxPosition)); 
      
      //bend wrist up bend wrist down
      new POVButton(m_operatorController, DPad.kUp).whileTrue(new WristBendSpeedCommand(m_wristBendSubsystem, 1));
      new POVButton(m_operatorController, DPad.kDown).whileTrue(new WristBendSpeedCommand(m_wristBendSubsystem, -1));

      //preset positions
      //base
      new JoystickButton(m_operatorController, Button.kA).onTrue(new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kBasePos));
      new JoystickButton(m_operatorController, Button.kA).onTrue(new TelescopePositionCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY), TelescopeConstants.kBasePos));
      
      //mid
      new JoystickButton(m_operatorController, Button.kX).onTrue(new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kMidPos));
      new JoystickButton(m_operatorController, Button.kX).onTrue(new TelescopePositionCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY), TelescopeConstants.kMidPos));

      //top
      new JoystickButton(m_operatorController, Button.kY).onTrue(new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kTopPos));
      new JoystickButton(m_operatorController, Button.kY).onTrue(new TelescopePositionCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY), TelescopeConstants.kTopPos));

      //retract
      new JoystickButton(m_operatorController, Button.kB).onTrue(new ShoulderPositionCommand(m_shoulderSubsystem, -ShoulderConstants.kRetractPos));
      new JoystickButton(m_operatorController, Button.kB).onTrue(new TelescopePositionCommand(m_telescopeSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY), TelescopeConstants.kRetractPos));

      // new JoystickButton(m_operatorController, Button.kA).whileTrue(new ExtendLevelCommand(m_telescopeSubsystem, ArmExtendConstants.kPIDSlot) && new ArmUpLevelCommand(m_shoulderSubsystem, ArmUpContants.kPIDSlot)); 
      // new JoystickButton(m_operatorController, Button.kX).whileTrue(new ExtendLevelCommand(m_telescopeSubsystem, ArmExtendConstants.kPIDSlot) && new ArmUpLevelCommand(m_shoulderSubsystem, ArmUpContants.kPIDSlot));
      // new JoystickButton(m_operatorController, Button.kY).whileTrue(new ExtendLevelCommand(m_telescopeSubsystem, ArmExtendConstants.kPIDSlot) && new ArmUpLevelCommand(m_shoulderSubsystem, ArmUpContants.kPIDSlot));

  }



}
