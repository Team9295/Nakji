package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristRotateConstants;

public class WristRotateSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(WristRotateConstants.kWristRotatePort, MotorType.kBrushed);
  private final RelativeEncoder m_encoder = m_motor.getEncoder(Type.kQuadrature, 1680);
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  public WristRotateSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(WristRotateConstants.kWristRotateInvert);
    m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor.enableVoltageCompensation(12);
    m_pidController.setP(WristRotateConstants.kP);
    m_pidController.setI(WristRotateConstants.kI);
    m_pidController.setIZone(WristRotateConstants.kIz);
    m_pidController.setD(WristRotateConstants.kD);
    m_pidController.setFF(WristRotateConstants.kFF);
    resetEncoder();
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
      m_motor.set(speed*WristRotateConstants.kSpeedLimitFactor);
    }

    public void setPosition(double position) {
      m_pidController.setReference(position, ControlType.kPosition, WristRotateConstants.kPIDSlot);
    }
    public double getPosition(){
     return m_motor.get(); 
    }
    public void resetEncoder() {
        m_encoder.setPosition(0);
        setPosition(0);
    }

    

}