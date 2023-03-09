package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.ArmExtendConstants;

public class ArmExtendSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(ArmExtendConstants.kArmExtendPort, MotorType.kBrushless);

  public ArmExtendSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(ArmExtendConstants.kArmExtendInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}