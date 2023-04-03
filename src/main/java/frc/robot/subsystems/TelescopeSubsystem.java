package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TelescopeConstants;

public class TelescopeSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(TelescopeConstants.kTelescopePort, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_motor.getEncoder(); 
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();  

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
      m_pidController.setReference(position, ControlType.kPosition, TelescopeConstants.kPIDSlot);
    }

    public void resetEncoder() {
        m_encoder.setPosition(0);
        setPosition(0);
    }

}