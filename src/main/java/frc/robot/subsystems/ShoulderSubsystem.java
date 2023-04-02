package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShoulderConstants;

public class ShoulderSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(ShoulderConstants.kShoulderPort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_motor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  private double m_setPoint;

  public ShoulderSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(ShoulderConstants.kShoulderInvert);
    m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor.enableVoltageCompensation(12);
    m_pidController.setP(ShoulderConstants.kP);
    m_pidController.setI(ShoulderConstants.kI);
    m_pidController.setIZone(ShoulderConstants.kIz);
    m_pidController.setD(ShoulderConstants.kD);
    m_pidController.setFF(ShoulderConstants.kFF);
    resetEncoder();

    setPosition(-ShoulderConstants.kMinPosition); 
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_motor.set(speed);
    }
    public void setMaxSpeed(double maxSpeed) {
      m_pidController.setOutputRange(-maxSpeed, maxSpeed);
    }
    public void setLevel(double level) {
      m_pidController.setReference(level, ControlType.kPosition, ShoulderConstants.kPIDSlot);
    }

    public void setPosition(double position) {
      // System.out.println("setpoint: " + position);
      m_setPoint = position;
      m_pidController.setReference(position, ControlType.kPosition, ShoulderConstants.kPIDSlot);
    }
    public double getReference() {
      return m_setPoint;
    }
    public double getPosition(){
     return m_encoder.getPosition(); 
    }
    public void resetEncoder() {
        m_encoder.setPosition(0);
        setPosition(0);
    }

}