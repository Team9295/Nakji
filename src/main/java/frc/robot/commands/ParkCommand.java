package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Relay; 
import frc.robot.Constants.ParkConstants;
import frc.robot.subsystems.ParkSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class ParkCommand extends CommandBase{
    private final ParkSubsystem m_ParkSubsystem; 
    private final Timer m_timer = new Timer(); 

    public ParkCommand(ParkSubsystem parkSubsystem) {
        m_ParkSubsystem = parkSubsystem; 
        addRequirements(m_ParkSubsystem);
    }
    public void initalize(){
        m_timer.start(); 
        m_ParkSubsystem.closeValve(); 
    }
    public void execute() {
        // m_ParkSubsystem.closeValve(); 
        while(m_timer.get()<ParkConstants.kPlateDownTime)
        m_ParkSubsystem.setSpeed(ParkConstants.kParkSpeed);
    }

    public void end(boolean interrupted) {
        m_ParkSubsystem.setSpeed(0);
        // m_ParkSubsystem.openValve();
    }
}
