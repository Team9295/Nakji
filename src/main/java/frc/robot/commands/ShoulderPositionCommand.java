package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderSubsystem;

public class ShoulderPositionCommand extends CommandBase{
    private final ShoulderSubsystem m_shoulderSubsystem;
    private final double m_position;
    private final Supplier<Double> m_thresh;

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, double position) {
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = position;
        m_thresh = () -> -1.0;
        addRequirements(m_shoulderSubsystem);
    }

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, Supplier<Double> thresh, double position) {
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = position;
        m_thresh = thresh;
        addRequirements(m_shoulderSubsystem);
    }

    public void execute() {
        System.out.println("VALUE IS "+m_thresh.get());
        double holdPos=0;
        if(m_thresh.get() <= -0.1){
            m_shoulderSubsystem.setPosition(m_position);
            holdPos=m_shoulderSubsystem.getPosition();
        }
        else if(m_thresh.get() >= 0.1){
            m_shoulderSubsystem.setPosition(-10);
            holdPos=m_shoulderSubsystem.getPosition();
        }
        else if(m_thresh.get() <= 0.1 && m_thresh.get() >= -0.1){
            m_shoulderSubsystem.setPosition(holdPos);
        }
    }

    public void end(boolean interrupted) {
        double target=m_shoulderSubsystem.getPosition();
        m_shoulderSubsystem.setPosition(target);
    }
}
