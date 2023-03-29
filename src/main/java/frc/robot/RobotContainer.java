// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmExtendConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.LinearActuatorConstants;
import frc.robot.Constants.RotatingBaseConstants;
import frc.robot.Constants.WristRotationConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ArmOutInCommand;
import frc.robot.commands.ArmUpDownCommand;
import frc.robot.commands.BaseSpeedCommand;
import frc.robot.commands.SuctionCommand; 
import frc.robot.commands.WristRotateCommand;
import frc.robot.commands.WristBendCommand;
import frc.robot.subsystems.ArmExtendSubsystem;
import frc.robot.subsystems.SuctionSubsystem;
import frc.robot.subsystems.ArmUpSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LinearActuatorSubsystem;
import frc.robot.subsystems.RotatingBaseSubsystem;
import frc.robot.subsystems.WristRotationSubsystem;
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
	private final ArmExtendSubsystem m_armExtendSubsystem = new ArmExtendSubsystem(); 
  private final SuctionSubsystem m_armGrabSubsystem = new SuctionSubsystem(); 
  private final ArmUpSubsystem m_armUpSubsystem = new ArmUpSubsystem(); 
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final LinearActuatorSubsystem m_linearActuatorSubsystem = new LinearActuatorSubsystem(); 
  private final RotatingBaseSubsystem m_rotatingBaseSubsystem = new RotatingBaseSubsystem(); 
  private final WristRotationSubsystem m_wristRotationSubsystem = new WristRotationSubsystem(); 
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
      new ArcadeDriveCommand(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kLeftY),
          () -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
          () -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));
    new POVButton(m_driverController, DPad.kLeft).whileTrue(new SuctionCommand(m_suctionSubsystem));
    //operator controls
    //move shoulder up and down
    m_armUpSubsystem.setDefaultCommand(
      new ArmUpDownCommand(m_armUpSubsystem, () -> m_operatorController.getRawAxis(Axis.kRightY)));
      //arm extend arm retract
    m_armExtendSubsystem.setDefaultCommand(
      //for some reason issue here don't know why, might need execute in command?
      new ArmOutInCommand(m_armExtendSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY))); 
      //turn base left turn base right
      new JoystickButton(m_operatorController, Button.kLeftBumper).whileTrue(new BaseSpeedCommand(m_rotatingBaseSubsystem, RotatingBaseConstants.kSpeedLimitFactor)); 
      new JoystickButton(m_operatorController, Button.kRightBumper).whileTrue(new BaseSpeedCommand(m_rotatingBaseSubsystem, -RotatingBaseConstants.kSpeedLimitFactor));
      //rotate wrist left rotate wrist right  
      new JoystickButton(m_operatorController, Button.kLeftMenu).whileTrue(new WristRotateCommand(m_wristRotationSubsystem, WristRotationConstants.kSpeedLimitFactor)); 
      new JoystickButton(m_operatorController, Button.kRightMenu).whileTrue(new WristRotateCommand(m_wristRotationSubsystem, -WristRotationConstants.kSpeedLimitFactor)); 
      //bend wrist up bend wrist down
      new POVButton(m_operatorController, DPad.kUp).whileTrue(new WristBendCommand(m_linearActuatorSubsystem, 1));
      new POVButton(m_operatorController, DPad.kDown).whileTrue(new WristBendCommand(m_linearActuatorSubsystem, -1));
      
      //TODO: add the presets for a,x,and y levels -- create armToPosition command (command group?)
      /*
       * somethiing like this? (using kPIDSlot as placeholder will add constants for specific levels): 
       * 
       * new JoystickButton(m_operatorConroller, Button.kA).whileTrue(new ExtendLevelCommand(m_armExtendSubsystem, ArmExtendConstants.kPIDslot) && new ArmUpLevelCommand(m_armUpSubsystem, ArmUpContants.kPIDSlot)); 
       * new JoystickButton(m_operatorConroller, Button.kX).whileTrue(new ExtendLevelCommand(m_armExtendSubsystem, ArmExtendConstants.kPIDslot) && new ArmUpLevelCommand(m_armUpSubsystem, ArmUpContants.kPIDSlot));
       * new JoystickButton(m_operatorConroller, Button.kY).whileTrue(new ExtendLevelCommand(m_armExtendSubsystem, ArmExtendConstants.kPIDslot) && new ArmUpLevelCommand(m_armUpSubsystem, ArmUpContants.kPIDSlot));
       * 
       */
      

  }



}
