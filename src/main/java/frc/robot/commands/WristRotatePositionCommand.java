package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristRotateSubsystem;

public class WristRotatePositionCommand extends CommandBase{
    private final WristRotateSubsystem m_wristRotationSubsystem;
    private final double m_Position;

    public WristRotatePositionCommand(WristRotateSubsystem wristRotationSubsystem, double Position) {
        m_wristRotationSubsystem = wristRotationSubsystem;
        m_Position = Position;
        addRequirements(m_wristRotationSubsystem);
    }

    public void execute() {
        m_wristRotationSubsystem.setPosition(m_Position);
    }

    public void end(boolean interrupted) {
        m_wristRotationSubsystem.setSpeed(0);
    }
}
