package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.ArmUpConstants;

public class ArmUpSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(ArmUpConstants.kArmUpPort, MotorType.kBrushless);

  public ArmUpSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(ArmUpConstants.kArmUpInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_baseMotor.set(speed);
    }

}