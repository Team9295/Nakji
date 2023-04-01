package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristBendSubsystem;
import frc.robot.Constants.WristBendConstants;

public class WristBendSpeedCommand extends CommandBase{
    private final WristBendSubsystem m_wristBendSubsystem;
    private final int m_direction;

    public WristBendSpeedCommand(WristBendSubsystem wristBendSubsystem, int direction) {
        m_wristBendSubsystem = wristBendSubsystem; 
        m_direction = direction;
        addRequirements(m_wristBendSubsystem);
    }

    public void initialize() {
        double position = m_wristBendSubsystem.getPosition(); 
        while(position <= 1 && position >= 0){
            position += WristBendConstants.kWristBendSpeed*m_direction;
            m_wristBendSubsystem.setPosition(position);
        }
    }

    public void end(boolean interrupted) {}
}
