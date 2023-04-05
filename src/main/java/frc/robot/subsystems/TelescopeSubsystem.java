package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TelescopeConstants;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleboardLogging;

public class TelescopeSubsystem extends SubsystemBase implements ShuffleboardLogging {
  private final CANSparkMax m_motor = new CANSparkMax(TelescopeConstants.kMotorPort, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_motor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  private double m_setPoint;

  public TelescopeSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(TelescopeConstants.kTelescopeInvert);
    m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor.enableVoltageCompensation(12);
    m_pidController.setP(TelescopeConstants.kP);
    m_pidController.setI(TelescopeConstants.kI);
    m_pidController.setIZone(TelescopeConstants.kIz);
    m_pidController.setD(TelescopeConstants.kD);
    m_pidController.setFF(TelescopeConstants.kFF);
    resetEncoder();
  }

  public void periodic() {
  }

  public void setSpeed(double speed) {
    m_motor.set(speed);
  }

  public void setDirection(double direction) {
    m_motor.set(direction);
  }

  public double getPosition() {
    
    return m_encoder.getPosition();
  }

  public void setPosition(double position) {
    m_setPoint = position;

    m_pidController.setReference(m_setPoint, ControlType.kPosition, TelescopeConstants.kPIDSlot);
  }
  public double getReference() {
    return m_setPoint;
  }

  public void resetEncoder() {
    m_encoder.setPosition(0);
    setPosition(0);
  }
  public double getVelocity() {
    return m_encoder.getVelocity();
}
public boolean atSetpoint() {
  return (Math.abs(m_setPoint - getPosition()) <= TelescopeConstants.kPositionTolerance);
}
  public void configureShuffleboard(boolean inCompetitionMode) {
    if (!inCompetitionMode) {
      ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Telescope");
      shuffleboardTab.addNumber("Encoder Position", () -> getPosition()).withSize(4, 2).withPosition(0, 0)
      .withWidget(BuiltInWidgets.kGraph);
shuffleboardTab.addNumber("Encoder Velocity", () -> getVelocity()).withSize(4, 2).withPosition(4, 0)
      .withWidget(BuiltInWidgets.kGraph);
shuffleboardTab.addBoolean("At setpoint", () -> atSetpoint()).withSize(1, 1).withPosition(0, 2)
      .withWidget(BuiltInWidgets.kBooleanBox);
    }
  }
}