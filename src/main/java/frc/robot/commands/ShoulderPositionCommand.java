package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderSubsystem;

public class ShoulderPositionCommand extends CommandBase{
    private final ShoulderSubsystem m_shoulderSubsystem;
    private final double m_position;

    public ShoulderPositionCommand(ShoulderSubsystem shoulderSubsystem, double position) {
        m_shoulderSubsystem = shoulderSubsystem;
        m_position = position;
        addRequirements(m_shoulderSubsystem);
    }

    public void execute() {
        m_shoulderSubsystem.setPosition(m_position);
    }

    public void end(boolean interrupted) {
        m_shoulderSubsystem.setSpeed(0);
    }
}
