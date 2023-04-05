package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristRotateConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleboardLogging;

public class WristRotateSubsystem extends SubsystemBase implements ShuffleboardLogging {
  private final CANSparkMax m_motor = new CANSparkMax(WristRotateConstants.kMotorPort, MotorType.kBrushed);
  private final RelativeEncoder m_encoder = m_motor.getEncoder(Type.kQuadrature, 1680);
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  private double m_setPoint;
  public WristRotateSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(WristRotateConstants.kWristRotateInvert);
    m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor.enableVoltageCompensation(12);
    m_pidController.setP(WristRotateConstants.kP);
    m_pidController.setI(WristRotateConstants.kI);
    m_pidController.setIZone(WristRotateConstants.kIz);
    m_pidController.setD(WristRotateConstants.kD);
    m_pidController.setFF(WristRotateConstants.kFF);
    resetEncoder();
    m_encoder.setPosition(0);
    m_motor.setSoftLimit(SoftLimitDirection.kForward, (float).1);
    m_motor.setSoftLimit(SoftLimitDirection.kReverse, (float).1);
  }

  public void periodic() {
  }

  public void setSpeed(double speed) {
    m_motor.set(speed * WristRotateConstants.kSpeedLimitFactor);
  }

  public void setPosition(double position) {
    m_setPoint = position;
    m_pidController.setReference(m_setPoint, ControlType.kPosition, WristRotateConstants.kPIDSlot);
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
  return (Math.abs(m_setPoint - getPosition()) <= WristRotateConstants.kPositionTolerance);
}
  public void configureShuffleboard(boolean inCompetitionMode) {
    if (!inCompetitionMode) {
      ShuffleboardTab posTab = Shuffleboard.getTab("posTab");
      posTab.addBoolean("Wrist Rotate at setpoint", () -> atSetpoint()).withSize(1, 1).withPosition(0, 2)
      .withWidget(BuiltInWidgets.kBooleanBox);
      
      ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Wrist Rotate");
      shuffleboardTab.addNumber("Encoder Position", () -> getPosition()).withSize(4, 2).withPosition(0, 0)
      .withWidget(BuiltInWidgets.kGraph);
shuffleboardTab.addNumber("Encoder Velocity", () -> getVelocity()).withSize(4, 2).withPosition(4, 0)
      .withWidget(BuiltInWidgets.kGraph);
shuffleboardTab.addBoolean("At setpoint", () -> atSetpoint()).withSize(1, 1).withPosition(0, 2)
      .withWidget(BuiltInWidgets.kBooleanBox);
    }
  }

}