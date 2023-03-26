package frc.robot.commands.PresetCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtendSubsystem;

public class ExtendLevelCommand extends CommandBase{
    private final ArmExtendSubsystem m_ArmExtendSubsystem; 
    private final double m_setpoint; 
    
    public ExtendLevelCommand(ArmExtendSubsystem armExtendSubsystem, double setpoint) {
        m_ArmExtendSubsystem = armExtendSubsystem;
        m_setpoint = setpoint; 
        addRequirements(m_ArmExtendSubsystem);
    }

    public void execute() {
        m_ArmExtendSubsystem.setPosition(m_setpoint);
    }
}
