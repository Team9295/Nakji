package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Relay;
import frc.robot.Constants.SuctionConstants;
import frc.robot.subsystems.SuctionSubsystem;

public class SuctionCommand extends CommandBase{
    private final SuctionSubsystem m_SuctionSubsystem;
    private final Relay m_relay = 
    public SuctionCommand(SuctionSubsystem suctionSubsystem) {
        m_SuctionSubsystem = suctionSubsystem;
        addRequirements(m_SuctionSubsystem);
    }

    public void execute() {
        m_SuctionSubsystem.setSpeed(SuctionConstants.kSuctionSpeed);
    }

    public void end(boolean interrupted) {
        m_SuctionSubsystem.setSpeed(0);
    }
}
