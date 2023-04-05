package frc.robot.commands.TurretCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSpeedCommand extends CommandBase {
    private final TurretSubsystem m_TurretSubsystem;
    private final double m_speed;

    public TurretSpeedCommand(TurretSubsystem turretSubsystem, double speed) {
        m_TurretSubsystem = turretSubsystem;
        m_speed = speed;
        addRequirements(m_TurretSubsystem);
    }

    public void execute() {
        m_TurretSubsystem.setSpeed(m_speed * TurretConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_TurretSubsystem.setSpeed(0);
    }
}
