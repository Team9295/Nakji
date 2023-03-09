package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.LinearMovementConstants;

public class LinearMovementSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(LinearMovementConstants.kLinearMovementPort, MotorType.kBrushless);

  public LinearMovementSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(LinearMovementConstants.kLinearMovementInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}