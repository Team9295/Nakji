package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants.LinearActuatorConstants;

public class LinearActuatorSubsystem extends SubsystemBase {
  private final Servo m_pitchMotor = new Servo(LinearActuatorConstants.kLinearActuatorChannel);

  public LinearActuatorSubsystem() {
    m_pitchMotor.setBounds(1.95, 0, 1.5, 0, 1.05);
  }
    public void periodic() {
    }
    public void setPosition(double position) {
        m_pitchMotor.set(position);
    }
    public double getPosition(){
      return m_pitchMotor.get(); 
    }

}