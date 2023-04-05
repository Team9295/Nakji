package frc.robot.commands.TurretCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.TurretSubsystem;

public class TurretPositionCommand extends CommandBase {
    private final TurretSubsystem m_turretSubsystem;
    private final double m_position;

    public TurretPositionCommand(TurretSubsystem TurretSubsystem, double position) {
        m_turretSubsystem = TurretSubsystem;
        m_position = position;
        addRequirements(m_turretSubsystem);
    }

    public void execute() {
        m_turretSubsystem.setPosition(m_position);
        // System.out.println(m_TurretSubsystem.getPosition());
    }

    public boolean isFinished() {
        // System.out.println("TURRET POS IS "+m_turretSubsystem.getPosition());
        // System.out.println("TURRET TARGET POS IS "+m_position);
        return Math.abs(m_turretSubsystem.getPosition() - m_position) <= TurretConstants.kPositionTolerance;
    }

    public void end(boolean interrupted) {
        System.out.println("TURRET DONE");

        m_turretSubsystem.setSpeed(0);
    }
    
}
