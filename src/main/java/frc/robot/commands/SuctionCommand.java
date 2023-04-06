package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Relay;
import frc.robot.Constants.SuctionConstants;
import frc.robot.subsystems.SuctionSubsystem;

public class SuctionCommand extends CommandBase {
    private final SuctionSubsystem m_SuctionSubsystem;

    public SuctionCommand(SuctionSubsystem suctionSubsystem) {
        m_SuctionSubsystem = suctionSubsystem;
        addRequirements(m_SuctionSubsystem);
    }

    public void execute() {
        m_SuctionSubsystem.setSpeed(SuctionConstants.kSuctionSpeed);
        System.out.println("SOLENOID CLOSED");
        m_SuctionSubsystem.closeValve();
    }

    public void end(boolean interrupted) {
        m_SuctionSubsystem.setSpeed(0);
        System.out.println("SOLENOID RELEASED");
        m_SuctionSubsystem.openValve();
    }
}
