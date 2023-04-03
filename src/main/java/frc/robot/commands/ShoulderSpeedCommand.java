package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShoulderConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ShoulderSubsystem;

public class ShoulderSpeedCommand extends CommandBase {
    private final ShoulderSubsystem m_ShoulderSubsystem;
    private final Supplier<Double> m_speed;

    public ShoulderSpeedCommand(ShoulderSubsystem ShoulderSubsystem, Supplier<Double> speed) {
        m_ShoulderSubsystem = ShoulderSubsystem;
        m_speed = speed;
        addRequirements(m_ShoulderSubsystem);
    }
    public void execute() {
        double speed = Math.abs(m_speed.get()) > ControllerConstants.kDeadzone
        ? m_speed.get() : 0; 
        m_ShoulderSubsystem.setSpeed(speed*ShoulderConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_ShoulderSubsystem.setSpeed(0);
        m_ShoulderSubsystem.setPosition(m_ShoulderSubsystem.getPosition());
    }
}
