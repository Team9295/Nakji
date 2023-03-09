package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.WristRotationConstants;

public class WristRotationSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(WristRotationConstants.kWristRotationPort, MotorType.kBrushless);

  public WristRotationSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(WristRotationConstants.kWristRotationInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}