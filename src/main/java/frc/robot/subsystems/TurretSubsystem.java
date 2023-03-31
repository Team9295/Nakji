package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(TurretConstants.kTurretPort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_motor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  public TurretSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(TurretConstants.kTurretInvert);
    m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor.enableVoltageCompensation(12);
    m_pidController.setP(TurretConstants.kP);
    m_pidController.setI(TurretConstants.kI);
    m_pidController.setIZone(TurretConstants.kIz);
    m_pidController.setD(TurretConstants.kD);
    m_pidController.setFF(TurretConstants.kFF);
    resetEncoder();
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_motor.set(speed);
    }
    public void setPosition(double position) {
      m_pidController.setReference(position, ControlType.kPosition, TurretConstants.kPIDSlot);
    }
    public void resetEncoder() {
        m_encoder.setPosition(0);
        setPosition(0);
    }

}