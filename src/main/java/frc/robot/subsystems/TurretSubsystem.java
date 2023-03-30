package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.RotatingBaseConstants;

public class TurretSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(RotatingBaseConstants.kRotatingBasePort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_baseMotor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_baseMotor.getPIDController();

  public TurretSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(RotatingBaseConstants.kRotatingBaseInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}