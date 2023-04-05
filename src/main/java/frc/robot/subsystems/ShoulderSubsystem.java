package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShoulderConstants;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleboardLogging;

public class ShoulderSubsystem extends SubsystemBase implements ShuffleboardLogging {
  private final CANSparkMax m_motor = new CANSparkMax(ShoulderConstants.kMotorPort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_motor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  private double m_setPoint;

  public ShoulderSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(false);
    m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor.enableVoltageCompensation(12);
    m_pidController.setP(ShoulderConstants.kP);
    m_pidController.setI(ShoulderConstants.kI);
    m_pidController.setIZone(ShoulderConstants.kIz);
    m_pidController.setD(ShoulderConstants.kD);
    m_pidController.setFF(ShoulderConstants.kFF);
    m_motor.burnFlash();
    resetEncoder();

    setPosition(-ShoulderConstants.kMinPosition);
  }

  public void periodic() {
  }

  public void setSpeed(double speed) {
    m_motor.set(speed);
  }

  public void setMaxSpeed(double maxSpeed) {
    m_pidController.setOutputRange(-maxSpeed, maxSpeed);
  }

  public void setLevel(double level) {
    m_pidController.setReference(level, ControlType.kPosition, ShoulderConstants.kPIDSlot);
  }

  public void setPosition(double position) {
    // System.out.println("setpoint: " + position);
    m_setPoint = position;
    m_pidController.setReference(m_setPoint, ControlType.kPosition, ShoulderConstants.kPIDSlot);
  }
  public double getReference() {
    return m_setPoint;
  }

  public double getPosition() {
    return m_encoder.getPosition();
  }

  public void resetEncoder() {
    m_encoder.setPosition(0);
    setPosition(0);
  }
  
  public double getVelocity() {
    return m_encoder.getVelocity();
}
public boolean atSetpoint() {
  return (Math.abs(m_setPoint - getPosition()) <= ShoulderConstants.kPositionTolerance);
}
  public void configureShuffleboard(boolean inCompetitionMode) {
    if (!inCompetitionMode) {
      ShuffleboardTab posTab = Shuffleboard.getTab("posTab");
      posTab.addBoolean("Shoulder at setpoint", () -> atSetpoint()).withSize(1, 1).withPosition(0, 2)
      .withWidget(BuiltInWidgets.kBooleanBox);
      ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Shoulder");
      shuffleboardTab.addNumber("Encoder Position", () -> getPosition()).withSize(4, 2).withPosition(0, 0)
      .withWidget(BuiltInWidgets.kGraph);
shuffleboardTab.addNumber("Encoder Velocity", () -> getVelocity()).withSize(4, 2).withPosition(4, 0)
      .withWidget(BuiltInWidgets.kGraph);
shuffleboardTab.addBoolean("At setpoint", () -> atSetpoint()).withSize(1, 1).withPosition(0, 2)
      .withWidget(BuiltInWidgets.kBooleanBox);
    }
  }
}