package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmUpConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmUpSubsystem;

public class ArmUpDownCommand extends CommandBase {
    private final ArmUpSubsystem m_ArmUpSubsystem;
    private final Supplier<Double> m_speed;

    public ArmUpDownCommand(ArmUpSubsystem ArmUpSubsystem, Supplier<Double> speed) {
        m_ArmUpSubsystem = ArmUpSubsystem;
        m_speed = speed;
        addRequirements(m_ArmUpSubsystem);
    }
    public void execute() {
        System.out.println("COMMAND RUNNING");
        double speed = Math.abs(m_speed.get()) > ControllerConstants.kDeadzone
        ? m_speed.get() : 0; 
        m_ArmUpSubsystem.setSpeed(speed*ArmUpConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_ArmUpSubsystem.setSpeed(0);
    }
}
