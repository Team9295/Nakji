package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;
import frc.robot.Constants.WristRotationConstants;

public class WristRotationSubsystem extends SubsystemBase {
  private final CANSparkMax m_wristMotor = new CANSparkMax(WristRotationConstants.kWristRotationPort, MotorType.kBrushed);
  private final RelativeEncoder m_encoder = m_wristMotor.getEncoder(Type.kQuadrature, 1680);
  private final SparkMaxPIDController m_pidController = m_wristMotor.getPIDController();

  public WristRotationSubsystem() {
    m_wristMotor.restoreFactoryDefaults();
    m_wristMotor.setInverted(WristRotationConstants.kWristRotationInvert);

  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_wristMotor.set(speed*WristRotationConstants.kSpeedLimitFactor);
    }

    public void setPosition(double position) {
      m_wristMotor.set(position);
    }
    public double getPosition(){
     return m_wristMotor.get(); 
    }

    

}