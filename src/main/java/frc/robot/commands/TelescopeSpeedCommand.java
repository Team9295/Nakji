package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TelescopeSubsystem;
import frc.robot.Constants.TelescopeConstants;
import frc.robot.Constants.ControllerConstants;


public class TelescopeSpeedCommand extends CommandBase {
    private final TelescopeSubsystem m_TelescopeSubsystem;
    private final Supplier<Double> m_speed;

    public TelescopeSpeedCommand(TelescopeSubsystem TelescopeSubsystem, Supplier<Double> speed) {
        m_TelescopeSubsystem = TelescopeSubsystem;
        m_speed = speed;
        addRequirements(m_TelescopeSubsystem);
    }

    public TelescopeSpeedCommand(TelescopeSubsystem TelescopeSubsystem, double position) {
        m_TelescopeSubsystem = TelescopeSubsystem;
        m_TelescopeSubsystem.setPosition(position);
        // -1 and 1 may need to be reversed
        if(m_TelescopeSubsystem.getPosition()-position>0)
        {
            m_speed = () -> -1.0;
        } else {
            m_speed = () -> 1.0;
        }
        
    }

    public void execute() {
        double speed = Math.abs(m_speed.get()) > ControllerConstants.kDeadzone
        ? m_speed.get() : 0; 
        m_TelescopeSubsystem.setSpeed(speed*TelescopeConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_TelescopeSubsystem.setSpeed(0);
    }
}
