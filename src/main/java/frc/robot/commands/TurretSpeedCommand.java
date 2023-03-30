package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.RotatingBaseConstants;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSpeedCommand extends CommandBase {
    private final TurretSubsystem m_RotatingBaseSubsystem;
    private final double m_speed;

    public TurretSpeedCommand(TurretSubsystem rotatingBaseSubsystem, double speed) {
        m_RotatingBaseSubsystem = rotatingBaseSubsystem;
        m_speed = speed;
        addRequirements(m_RotatingBaseSubsystem);
    }

    public void execute() {
        m_RotatingBaseSubsystem.setSpeed(m_speed*RotatingBaseConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_RotatingBaseSubsystem.setSpeed(0);
    }
}
