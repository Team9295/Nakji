package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TelescopeSubsystem;

public class TelescopePositionCommand extends CommandBase {
    private final TelescopeSubsystem m_telescopeSubsystem;
    private final  double m_Position;

    public TelescopePositionCommand(TelescopeSubsystem telescopeSubsystem, double Position) {
        m_telescopeSubsystem = telescopeSubsystem;
        m_Position = Position;
        addRequirements(m_telescopeSubsystem);
    }

    public void execute() {
        m_telescopeSubsystem.setPosition(m_Position);
    }

    public void end(boolean interrupted) {
        m_telescopeSubsystem.setSpeed(0);
    }
}
