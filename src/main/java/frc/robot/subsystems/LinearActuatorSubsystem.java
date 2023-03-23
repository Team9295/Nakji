package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.LinearActuatorConstants;

public class LinearActuatorSubsystem extends SubsystemBase {
  private final CANSparkMax m_pitchMotor = new CANSparkMax(LinearActuatorConstants.kLinearActuatorPort, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_pitchMotor.getEncoder();
  private final SparkMaxPIDController m_pidController = m_pitchMotor.getPIDController();

  public LinearActuatorSubsystem() {
    m_pitchMotor.restoreFactoryDefaults();
    m_pitchMotor.setInverted(LinearActuatorConstants.kLinearActuatorInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_pitchMotor.set(speed);
    }

}