package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SuctionSubsystem;


public class SuctionPositionCommand extends CommandBase{
    private final SuctionSubsystem m_suctionSubsystem;
    private final double m_Position;

    public SuctionPositionCommand(SuctionSubsystem suctionSubsystem, double Position) {
        m_suctionSubsystem = suctionSubsystem;
        m_Position = Position;
        addRequirements(m_suctionSubsystem);
    }

    public void execute() {
        m_suctionSubsystem.setPosition(m_Position);
    }

    public void end(boolean interrupted) {
        m_suctionSubsystem.setSpeed(0);
    }
}
