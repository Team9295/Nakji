package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtendSubsystem;

public class ArmOutInCommand extends CommandBase {
    private final ArmExtendSubsystem m_ArmExtendSubsystem;
    private final double m_speed;
    private final double m_direction; 

    public ArmOutInCommand(ArmExtendSubsystem armExtendSubsystem, double speed, double direction) {
        m_ArmExtendSubsystem = armExtendSubsystem;
        m_speed = speed;
        m_direction = direction;
        addRequirements(m_ArmExtendSubsystem);
    }

    public void initialize() {
        m_ArmExtendSubsystem.setSpeed(m_speed);
        m_ArmExtendSubsystem.setDirection(m_direction); 
    }

    public void end(boolean interrupted) {
        m_ArmExtendSubsystem.setSpeed(0);
        m_ArmExtendSubsystem.setDirection(0);
    }
}
