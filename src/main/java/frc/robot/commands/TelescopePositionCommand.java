package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TelescopeSubsystem;

public class TelescopePositionCommand extends CommandBase {
    private final TelescopeSubsystem m_telescopeSubsystem;
    private final  double m_position;

    public TelescopePositionCommand(TelescopeSubsystem telescopeSubsystem, double position) {
        m_telescopeSubsystem = telescopeSubsystem;
        m_position = position;
        addRequirements(m_telescopeSubsystem);
    }

    public void execute() {
        m_telescopeSubsystem.setPosition(m_position);
    }

    public void end(boolean interrupted) {
        m_telescopeSubsystem.setSpeed(0);
    }
}
