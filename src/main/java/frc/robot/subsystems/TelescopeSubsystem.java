package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import frc.robot.Constants.TelescopeConstants;

public class TelescopeSubsystem extends SubsystemBase {
  private final CANSparkMax m_baseMotor = new CANSparkMax(TelescopeConstants.kTelescopePort, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_baseMotor.getEncoder(); 
  private final SparkMaxPIDController m_PidController = m_baseMotor.getPIDController();  

  public TelescopeSubsystem() {
    m_baseMotor.restoreFactoryDefaults();
    m_baseMotor.setInverted(TelescopeConstants.kTelescopeInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
      m_baseMotor.set(speed);
    }
    public void setDirection(double direction) {
      m_baseMotor.set(direction);
    }

    public void setPosition(double position) {
      m_PidController.setReference(position, ControlType.kPosition, TelescopeConstants.kPIDslot);
    }


}