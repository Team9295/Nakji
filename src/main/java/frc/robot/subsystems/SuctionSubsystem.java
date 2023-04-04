package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import frc.robot.Constants.SuctionConstants;
import edu.wpi.first.wpilibj.Relay;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleboardLogging;

public class SuctionSubsystem extends SubsystemBase implements ShuffleboardLogging{
  private final CANSparkMax m_motor = new CANSparkMax(SuctionConstants.kMotorPort, MotorType.kBrushed);
  private final Relay m_relay = new Relay(SuctionConstants.kRelayPort);
  private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

  public SuctionSubsystem() {
    m_motor.restoreFactoryDefaults();
    m_motor.setInverted(SuctionConstants.kSuctionInvert);
  }
    public void periodic() {
    }
    public void setSpeed(double speed) {
        m_motor.set(speed);
    }
  public void openValve() {
    m_relay.set(Relay.Value.kForward);
  }
  public void closeValve() {
    m_relay.set(Relay.Value.kOff);
  }

  public void configureShuffleboard(boolean inCompetitionMode) {
    if(!inCompetitionMode){
            ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Drive");
    }
  }
}