package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class TimeBasedAutoCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem; 
    private final int m_seconds; 
    private final Timer m_timer = new Timer(); 

    public TimeBasedAutoCommand(DriveSubsystem driveSubsystem, int seconds) {
        m_driveSubsystem = driveSubsystem; 
        m_seconds = seconds; 
    } 

    public void initalize() {
        m_timer.start(); 
    }

    public void execute() {
        if(m_timer.get() < m_seconds) {
            m_driveSubsystem.tankDrive(1, 1);
        } 
    }

    public boolean isFinished(){
        if(m_timer.get() >= m_seconds) {
            return true; 
        } else {
            return false;
        }
    }

    public void end() {
        m_driveSubsystem.tankDrive(0,0); 
    }
}
