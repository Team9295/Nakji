package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RotatingBaseSubsystem;

public class BaseSpeedCommand extends CommandBase {
    private final RotatingBaseSubsystem m_RotatingBaseSubsystem;
    private final double m_speed;

    public BaseSpeedCommand(RotatingBaseSubsystem rotatingBaseSubsystem, double speed) {
        m_RotatingBaseSubsystem = rotatingBaseSubsystem;
        m_speed = speed;
        addRequirements(m_RotatingBaseSubsystem);
    }

    public void initialize() {
        m_RotatingBaseSubsystem.setSpeed(m_speed);
    }

    public void end(boolean interrupted) {
        m_RotatingBaseSubsystem.setSpeed(0);
    }
}
