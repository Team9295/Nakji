package frc.robot.commands.ShoulderCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderSubsystem;
import frc.robot.Constants.ShoulderConstants;

public class ShoulderPositionCommand extends CommandBase {
    private final ShoulderSubsystem m_shoulderSubsystem;
    private final double m_position;
    private final Supplier<Double> m_operatorThresh;
    private final Supplier<Double> m_driverThresh;
    private final boolean m_fixedPosition;
    private double m_currPos;

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, double position) {
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = position;
        m_operatorThresh = () -> 1.0;
        m_driverThresh = () -> 1.0;
        m_fixedPosition = true;
        addRequirements(m_shoulderSubsystem);
        m_currPos=0;
    }

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, Supplier<Double> operatorThresh,
            Supplier<Double> driverThresh) { //manual move
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = ShoulderConstants.kMaxPosition;
        m_operatorThresh = operatorThresh;
        m_driverThresh = driverThresh;
        m_fixedPosition = false;
        m_currPos=0;
        addRequirements(m_shoulderSubsystem);
    }

    public void initialize() {
        if (m_fixedPosition) {
            m_shoulderSubsystem.setMaxSpeed(ShoulderConstants.kRapidSpeed);
            m_shoulderSubsystem.setPosition(m_position);
        } else {
            m_shoulderSubsystem.setMaxSpeed(1.0);
        }
    }

    public void execute() {
        // System.out.println("holdPos: "+holdPos);
        // System.out.println("Current pos: " + m_shoulderSubsystem.getPosition());
        m_currPos=m_shoulderSubsystem.getPosition();
        if (m_operatorThresh.get() >= 0.1) {
            // double currPosition = m_shoulderSubsystem.getReference();
            // double newPosition = currPosition >= -ShoulderConstants.kMaxPosition
            //         ? currPosition + -m_thresh.get() * ShoulderConstants.kDriverStepSize
            //         : currPosition;
            m_shoulderSubsystem.setPosition(-m_position);
        } else if (m_operatorThresh.get() <= -0.1) {
            // double currPosition = m_shoulderSubsystem.getReference();
            // double newPosition = currPosition >= -ShoulderConstants.kMaxPosition
            //         ? currPosition + -m_thresh.get() * ShoulderConstants.kDriverStepSize
            //         : currPosition;
            m_shoulderSubsystem.setPosition(0);
        } else if (m_driverThresh.get() >= 0.1) {
            // double currPosition = m_shoulderSubsystem.getReference();
            // double newPosition = currPosition >= -ShoulderConstants.kMaxPosition
            //         ? currPosition + -m_thresh.get() * ShoulderConstants.kStepSize
            //         : currPosition;
            m_shoulderSubsystem.setPosition(0);
        } else if (m_driverThresh.get() <= -0.1) {
            // double currPosition = m_shoulderSubsystem.getReference();
            // double newPosition = currPosition <= -ShoulderConstants.kMinPosition
            //         ? currPosition + -m_thresh.get() * ShoulderConstants.kStepSize
            //         : currPosition;
            m_shoulderSubsystem.setPosition(-m_position);
        }
        else{
            System.out.println("HOLDING POSITION SHOULDER");
            m_shoulderSubsystem.setPosition(m_currPos-1);
        }

        // else if(m_thresh.get() <= 0.1 && m_thresh.get() >= -0.1){
        // m_shoulderSubsystem.setPosition(holdPos);
        // }
    }

    public void end(boolean interrupted) {
        System.out.println("SHOULDER DONE");

        // double target = m_shoulderSubsystem.getPosition();
        m_shoulderSubsystem.setSpeed(0);
        // m_shoulderSubsystem.setPosition(m_currPos);
    }

    public boolean isFinished() {
        // System.out.println("shoulder POS IS "+m_shoulderSubsystem.getPosition());
        // System.out.println("TARGET POS IS "+m_position);
        if(m_fixedPosition){
            return m_shoulderSubsystem.atSetpoint();
        }
        else{
            return m_driverThresh.get() <= 0.1 && m_driverThresh.get() >= -0.1 && m_operatorThresh.get() <= 0.1 && m_operatorThresh.get() >= -0.1;
        }
    }
}
