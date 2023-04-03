package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Relay; 
import frc.robot.Constants.ParkConstants;
import frc.robot.subsystems.ParkSubsystem;

public class ParkCommand extends CommandBase{
    private final ParkSubsystem m_ParkSubsystem; 
    public ParkCommand(ParkSubsystem parkSubsystem) {
        m_ParkSubsystem = parkSubsystem; 
        addRequirements(m_ParkSubsystem);
    }

    public void execute() {
        m_ParkSubsystem.setSpeed(ParkConstants.kParkSpeed);
        m_ParkSubsystem.closeValve(); 
    }

    public void end(boolean interrupted) {
        m_ParkSubsystem.setSpeed(0);
        m_ParkSubsystem.openValve();
    }
}
