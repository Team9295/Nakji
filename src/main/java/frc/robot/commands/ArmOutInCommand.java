package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtendSubsystem;
import frc.robot.Constants.ArmExtendConstants;
import frc.robot.Constants.ControllerConstants;


public class ArmOutInCommand extends CommandBase {
    private final ArmExtendSubsystem m_ArmExtendSubsystem;
    private final Supplier<Double> m_speed;

    public ArmOutInCommand(ArmExtendSubsystem armExtendSubsystem, Supplier<Double> speed) {
        m_ArmExtendSubsystem = armExtendSubsystem;
        m_speed = speed;
        addRequirements(m_ArmExtendSubsystem);
    }

    public void execute() {
        double speed = Math.abs(m_speed.get()) > ControllerConstants.kDeadzone
        ? m_speed.get() : 0; 
        m_ArmExtendSubsystem.setSpeed(speed*ArmExtendConstants.kSpeedLimitFactor);
        
    }

    public void end(boolean interrupted) {
        m_ArmExtendSubsystem.setSpeed(0);
    }
}
