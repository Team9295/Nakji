package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristRotationSubsystem;

public class WristRotationPositionCommand extends CommandBase{
    private final WristRotationSubsystem m_wristRotationSubsystem;
    private final double m_Position;

    public WristRotationPositionCommand(WristRotationSubsystem wristRotationSubsystem, double Position) {
        m_wristRotationSubsystem = wristRotationSubsystem;
        m_Position = Position;
        addRequirements(m_wristRotationSubsystem);
    }

    public void execute() {
        m_wristRotationSubsystem.setPosition(m_Position);
    }
}
