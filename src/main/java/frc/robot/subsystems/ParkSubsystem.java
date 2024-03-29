package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import frc.robot.Constants.ParkConstants;
import frc.robot.Constants.SuctionConstants;
import edu.wpi.first.wpilibj.Relay;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleboardLogging;

public class ParkSubsystem extends SubsystemBase implements ShuffleboardLogging {
    private final CANSparkMax m_motor = new CANSparkMax(ParkConstants.kMotorPort, MotorType.kBrushed);
    private final Relay m_relay = new Relay(ParkConstants.kRelayPort);
    private final SparkMaxPIDController m_pidController = m_motor.getPIDController();

    public ParkSubsystem() {
        m_motor.restoreFactoryDefaults();
        m_motor.setInverted(ParkConstants.kParkInvert);
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
        if (!inCompetitionMode) {
            ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Drive");
        }
    }
}
