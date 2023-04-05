package frc.robot.commands.WristRotateCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristRotateSubsystem;

public class WristRotateSpeedCommand extends CommandBase {
    private final WristRotateSubsystem m_WristRotateSubsystem;
    private final double m_speed;

    public WristRotateSpeedCommand(WristRotateSubsystem wristRotateSubsystem, double speed) {
        m_WristRotateSubsystem = wristRotateSubsystem;
        m_speed = speed;
        addRequirements(m_WristRotateSubsystem);
        // System.out.println("TEST" + m_WristRotateSubsystem.getPosition());
    }

    public void initialize() {
        m_WristRotateSubsystem.setSpeed(m_speed);
    }

    public void end(boolean interrupted) {
        m_WristRotateSubsystem.setSpeed(0);
    }
}
