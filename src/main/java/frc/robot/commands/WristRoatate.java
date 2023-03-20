package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristRotationSubsystem;

public class WristRoatate extends CommandBase{
    private final WristRotationSubsystem m_WristRotationSubsystem;
    private final double m_speed;
    
    public WristRoatate(WristRotationSubsystem wristRotationSubsystem, double speed) {
        m_WristRotationSubsystem = wristRotationSubsystem; 
        m_speed = speed; 
        addRequirements(m_WristRotationSubsystem);
    }

    public void initialize() {
        m_WristRotationSubsystem.setSpeed(m_speed);
    }

    public void end(boolean interrupted) {
        m_WristRotationSubsystem.setSpeed(0); 
    }
}
