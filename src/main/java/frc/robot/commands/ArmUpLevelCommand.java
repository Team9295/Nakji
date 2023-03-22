package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmUpSubsystem;

public class ArmUpLevelCommand extends CommandBase {
    private final ArmUpSubsystem m_armUpSubsystem;
    private final double m_setpoint;

    public ArmUpLevelCommand(ArmUpSubsystem armUpSubsystem, double setpoint) {
        m_armUpSubsystem = armUpSubsystem;
        m_setpoint = setpoint;
        addRequirements(m_armUpSubsystem);
    }

    public void execute() {
        m_armUpSubsystem.setLevel(m_setpoint);
    }
}
