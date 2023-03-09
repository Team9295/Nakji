package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.ArmGrabConstants;

public class ArmGrabSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(ArmGrabConstants.kArmGrabPort, MotorType.kBrushless);

  public ArmGrabSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(ArmGrabConstants.kArmGrabInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}