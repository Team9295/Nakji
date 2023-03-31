package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TurretPositionCommand extends CommandBase {
    private final TurretSubsystem m_TurretSubsystem;
    private final double m_position;
    
    public TurretPositionCommand(TurretSubsystem TurretSubsystem, double position) {
        m_TurretSubsystem = TurretSubsystem;
        m_position = position;
        addRequirements(m_TurretSubsystem);
    }

    public void execute() {
        m_TurretSubsystem.setPosition(m_position);
    }

    public void end(boolean interrupted) {
        m_TurretSubsystem.setSpeed(0);
    }
}
