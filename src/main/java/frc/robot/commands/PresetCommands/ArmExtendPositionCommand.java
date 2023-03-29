package frc.robot.commands.PresetCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtendSubsystem;

public class ArmExtendPositionCommand extends CommandBase {
    private final ArmExtendSubsystem m_armExtendSubsystem;
    private final  double m_Position;

    public ArmExtendPositionCommand(ArmExtendSubsystem armExtendSubsystem, double Position) {
        m_armExtendSubsystem = armExtendSubsystem;
        m_Position = Position;
        addRequirements(m_armExtendSubsystem);
    }

    public void execute() {
        m_armExtendSubsystem.setPosition(m_Position);
    }
}
