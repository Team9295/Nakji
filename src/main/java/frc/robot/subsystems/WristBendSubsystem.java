package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants.WristBendConstants;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleboardLogging;

public class WristBendSubsystem extends SubsystemBase implements ShuffleboardLogging {
  private final Servo m_servo = new Servo(WristBendConstants.kServoPort);
  private final Servo m_Servo1 = new Servo(WristBendConstants.kWristServo1Channel);
  private final Servo m_Servo2 = new Servo(WristBendConstants.kWristServo2Channel);

  public WristBendSubsystem() {
    m_servo.setBounds(1, 1.48, 1.5, 1.52, 2);
    // Servo bounds: .7ms = 0deg, 2.3ms = 180deg
    m_Servo1.setBounds(2.3, 0, 1.5, 0, 0.7);
    m_Servo2.setBounds(.7, 0, 1.5, 0, 2.3);
    // Servo1 and Servo2 should have opposite max and min relative to .7 thru 2.3
    // This means if one is from 2-2.3, the other should be from 1-.7 (same range,
    // opposite direction)
  }

  public void periodic() {
  }

  public void setSpeed(double speed) {
    m_servo.set(speed *WristBendConstants.kWristBendSpeed);
  }

  public void setPosition(double position) {
    m_servo.set(position);
    m_Servo1.set(position);
    m_Servo2.set(position);
    // System.out.println("Pos: " + position);
  }

  public double getPosition() {
    return m_servo.get();
  }

  public double getVelocity() {
    return m_servo.get(); 
  }

  public void configureShuffleboard(boolean inCompetitionMode) {
    if (!inCompetitionMode) {
      ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Wrist Bend");
    }
  }
}