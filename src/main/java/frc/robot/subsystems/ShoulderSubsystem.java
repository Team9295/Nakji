package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.ShoulderConstants;

public class ShoulderSubsystem extends SubsystemBase {
  private final CANSparkMax m_shoulderMotor = new CANSparkMax(ShoulderConstants.kShoulderPort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_shoulderMotor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_shoulderMotor.getPIDController();

  public ShoulderSubsystem() {
    m_shoulderMotor.restoreFactoryDefaults();
    m_shoulderMotor.setInverted(ShoulderConstants.kShoulderInvert);
  }
    public void periodic() {
      System.out.println("ARM RUNNING");
    }
    public void setSpeed(double speed) {
        m_shoulderMotor.set(speed);
        System.out.println("ARM SPEED IS "+speed);
    }
    public void setLevel(double level) {
      m_pidController.setReference(level, ControlType.kPosition, ShoulderConstants.kPIDSlot);
    }

    public void setPosition(double position) {
      m_encoder.setPosition(position);
    }
    public double getPosition(){
     return m_encoder.getPosition(); 
    }

}