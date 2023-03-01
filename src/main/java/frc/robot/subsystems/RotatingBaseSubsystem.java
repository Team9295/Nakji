package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.RotatingBaseConstants;

public class RotatingBaseSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(RotatingBaseConstants.kRotatingBasePort, MotorType.kBrushless);

  public RotatingBaseSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(RotatingBaseConstants.kRotatingBaseInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}