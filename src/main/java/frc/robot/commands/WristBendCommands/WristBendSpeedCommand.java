package frc.robot.commands.WristBendCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristBendSubsystem;
import frc.robot.Constants.WristBendConstants;

public class WristBendSpeedCommand extends CommandBase {
    private final WristBendSubsystem m_wristBendSubsystem;
    private final int m_direction;
    double m_endPos=0;

    public WristBendSpeedCommand(WristBendSubsystem wristBendSubsystem, int direction) {
        m_wristBendSubsystem = wristBendSubsystem;
        m_direction = direction;
        addRequirements(m_wristBendSubsystem);
    }

    public void execute() {
        double position = m_wristBendSubsystem.getPosition();
        position += WristBendConstants.kWristBendSpeed * m_direction;
        m_wristBendSubsystem.setPosition(position);
        m_endPos=m_wristBendSubsystem.getPosition();
    }
    public void end(boolean interrupted) {

        m_wristBendSubsystem.setPosition(m_endPos);

        // m_wristBendSubsystem.setSpeed(0);
    }
}
