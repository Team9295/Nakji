package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class TimeBasedAutoTurnCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final double m_seconds;
    private final double m_speed;
    private final double m_direction; 
    private final Timer m_timer = new Timer();

    public TimeBasedAutoTurnCommand(DriveSubsystem driveSubsystem, double seconds, double speed, double direction) {
        m_driveSubsystem = driveSubsystem;
        m_seconds = seconds;
        m_speed = speed;
        m_direction = direction; 
    }

    public void initalize() {
        m_timer.start();
    }

    public void execute() {
        System.out.println("AUTO RUNNING");
        while (m_timer.get() < m_seconds) {
            m_driveSubsystem.tankDrive(m_speed * m_direction, m_speed * -m_direction);
        }
    }

    public boolean isFinished() {
        if (m_timer.get() >= m_seconds) {
            return true;
        } else {
            return false;
        }
    }

    public void end() {
        m_driveSubsystem.tankDrive(0, 0);
    }
}
