package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import frc.robot.Constants.SuctionConstants;

public class SuctionSubsystem extends SubsystemBase {
  private final CANSparkMax m_suckMotor = new CANSparkMax(SuctionConstants.kSuctionPort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_suckMotor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_suckMotor.getPIDController();

  public SuctionSubsystem() {
    m_suckMotor.restoreFactoryDefaults();
    m_suckMotor.setInverted(SuctionConstants.kSuctionInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_suckMotor.set(speed);
    }

}