package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtendSubsystem;

public class ExtendLevel extends CommandBase{
    private final ArmExtendSubsystem m_ArmExtendSubsystem; 
    private final double m_setpoint; 
    
    public ExtendLevel(ArmExtendSubsystem armExtendSubsystem, double setpoint) {
        m_ArmExtendSubsystem = armExtendSubsystem;
        m_setpoint = setpoint; 
        addRequirements(m_ArmExtendSubsystem);
    }

    public void execute() {
        m_ArmExtendSubsystem.setPosition(m_setpoint);
    }
}
