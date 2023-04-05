package frc.robot.commands.WristRotateCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristRotateSubsystem;

public class WristRotatePositionCommand extends CommandBase {
    private final WristRotateSubsystem m_wristRotationSubsystem;
    private final double m_position;

    public WristRotatePositionCommand(WristRotateSubsystem wristRotationSubsystem, double position) {
        m_wristRotationSubsystem = wristRotationSubsystem;
        m_position = position;
        addRequirements(m_wristRotationSubsystem);
    }

    public void execute() {
        m_wristRotationSubsystem.setPosition(m_position);
    }

    public void end(boolean interrupted) {
        m_wristRotationSubsystem.setSpeed(0);
    }

}
