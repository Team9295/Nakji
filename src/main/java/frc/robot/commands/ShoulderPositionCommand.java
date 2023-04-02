package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderSubsystem;
import frc.robot.Constants.ShoulderConstants;

public class ShoulderPositionCommand extends CommandBase{
    private final ShoulderSubsystem m_shoulderSubsystem;
    private final double m_position;
    private final Supplier<Double> m_thresh;
    private final boolean m_fixedPosition;

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, double position) {
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = position;
        m_thresh = () -> 0.0;
        m_fixedPosition = true;
        addRequirements(m_shoulderSubsystem);
    }

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, Supplier<Double> thresh) {
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = m_shoulderSubsystem.getReference();
        m_thresh = thresh;
        m_fixedPosition = false;
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
        if(m_thresh.get() >= 0.1){
            double currPosition = m_shoulderSubsystem.getReference();
            double newPosition = currPosition >= -ShoulderConstants.kMaxPosition ? currPosition + -m_thresh.get() * ShoulderConstants.kStepSize: currPosition;
            m_shoulderSubsystem.setPosition(newPosition);
        }
        else if(m_thresh.get() <= -0.1){
            double currPosition = m_shoulderSubsystem.getReference();
            double newPosition = currPosition <= -ShoulderConstants.kMinPosition ? currPosition + -m_thresh.get() * ShoulderConstants.kStepSize: currPosition;
            m_shoulderSubsystem.setPosition(newPosition);
        }
        
        // else if(m_thresh.get() <= 0.1 && m_thresh.get() >= -0.1){
        //     m_shoulderSubsystem.setPosition(holdPos);
        // }
    }

    public void end(boolean interrupted) {
        double target=m_shoulderSubsystem.getPosition();
        m_shoulderSubsystem.setPosition(target);
    }

    public boolean isFinished() {
        if (m_fixedPosition) {
            return Math.abs(m_shoulderSubsystem.getPosition() - m_position) <= 1;
        } else {
            return false;
        }
    }
}
