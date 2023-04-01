package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderSubsystem;
import frc.robot.Constants.ShoulderConstants;

public class ShoulderPositionCommand extends CommandBase{
    private final ShoulderSubsystem m_shoulderSubsystem;
    private final double m_position;
    private final Supplier<Double> m_thresh;
    private double holdPos=0;

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

    public void initialize() {
        holdPos = -ShoulderConstants.kMinPosition;
        m_shoulderSubsystem.setPosition(holdPos);
    }

    public void execute() {
        // System.out.println("holdPos: "+holdPos);
        // System.out.println("Current pos: " + m_shoulderSubsystem.getPosition());
        if(m_thresh.get() >= 0.1){
            holdPos = holdPos >= m_position ? holdPos + -m_thresh.get() * ShoulderConstants.kStepSize: holdPos;
            m_shoulderSubsystem.setPosition(holdPos);
        }
        else if(m_thresh.get() <= -0.1){
            holdPos = holdPos <= -ShoulderConstants.kMinPosition ? holdPos + -m_thresh.get() * ShoulderConstants.kStepSize : holdPos;
            m_shoulderSubsystem.setPosition(holdPos);
        }
        // else if(m_thresh.get() <= 0.1 && m_thresh.get() >= -0.1){
        //     m_shoulderSubsystem.setPosition(holdPos);
        // }
    }

    public void end(boolean interrupted) {
        double target=m_shoulderSubsystem.getPosition();
        m_shoulderSubsystem.setPosition(target);
    }
}
