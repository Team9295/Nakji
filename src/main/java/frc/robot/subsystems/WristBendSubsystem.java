package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants.WristBendConstants;

public class WristBendSubsystem extends SubsystemBase {
  private final Servo m_motor = new Servo(WristBendConstants.kWristBendChannel);

  public WristBendSubsystem() {
    m_motor.setBounds(1.95, 0, 1.5, 0, 1.05);
  }
    public void periodic() {
    }
    public void setPosition(double position) {
        m_motor.set(position);
    }
    public double getPosition(){
      return m_motor.get(); 
    }

}