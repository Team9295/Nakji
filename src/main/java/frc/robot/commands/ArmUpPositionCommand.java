package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmUpSubsystem;

public class ArmUpPositionCommand extends CommandBase{
    private final ArmUpSubsystem m_armUpSubsystem;
    private final double m_Position;

    public ArmUpPositionCommand(ArmUpSubsystem armUpSubsystem, double Position) {
        m_armUpSubsystem = armUpSubsystem;
        m_Position = Position;
        addRequirements(m_armUpSubsystem);
    }

    public void execute() {
        m_armUpSubsystem.setPosition(m_Position);
    }
}
