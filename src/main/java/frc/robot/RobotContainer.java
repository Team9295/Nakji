// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmExtendConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ArmUpDownCommand;
import frc.robot.commands.WristRotateCommand;
import frc.robot.subsystems.ArmExtendSubsystem;
import frc.robot.subsystems.SuctionSubsystem;
import frc.robot.subsystems.ArmUpSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LinearActuatorSubsystem;
import frc.robot.subsystems.RotatingBaseSubsystem;
import frc.robot.subsystems.WristRotationSubsystem;
import frc.robot.Constants.ControllerConstants.Axis;
import frc.robot.Constants.ControllerConstants.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    m_driveSubsystem.setDefaultCommand(
      new ArcadeDriveCommand(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kLeftY),
          () -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
          () -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));

    m_armUpSubsystem.setDefaultCommand(
      new ArmUpDownCommand(m_armUpSubsystem, () -> m_operatorController.getRawAxis(Axis.kLeftY)));
      
    new JoystickButton(m_driverController, Button.kX).whileTrue(new ArmUpDownCommand(m_armUpSubsystem,  () -> .5));
  }



}
