package frc.robot.commands.WristBendCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristBendSubsystem;

public class WristBendPositionCommand extends CommandBase {
    private final WristBendSubsystem m_wristBendSubsystem;
    private final double m_position;
    
    public WristBendPositionCommand(WristBendSubsystem wristBendSubsystem, double position) {
        m_wristBendSubsystem = wristBendSubsystem;
        m_position = position;
        addRequirements(m_wristBendSubsystem);
    }

    public void execute() {
        m_wristBendSubsystem.setPosition(m_position); //position 0 to 1
    }

    public void end(boolean interrupted) {
    }
}
