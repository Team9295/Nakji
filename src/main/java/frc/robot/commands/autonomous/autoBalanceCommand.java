/*******************
 * open source code link, courtsey of FRC 3683
 * https://github.com/FRC3683/OpenAutoBalance/blob/main/java/autoBalance.java
 * 
 ******************/

package frc.robot.commands.autonomous;

import java.lang.Math;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoBalanceCommand extends CommandBase{
    private final DriveSubsystem m_driveSubsystem;
      private BuiltInAccelerometer mRioAccel; 
      private int state; 
      private int debounceCount; 
      private double robotSpeedSlow; 
      private double robotSpeedFast; 
      private double onChargeStationDegree; 
      private double levelDegree;
      private double debounceTime;
        private double singleTapTime;
        private double scoringBackUpTime; 

    public AutoBalanceCommand(DriveSubsystem driveSubsystem) {
        m_driveSubsystem = driveSubsystem;
        mRioAccel = new BuiltInAccelerometer(); 
        state = 0; 
        debounceCount = 0;

        // config

        robotSpeedFast = 0.4; 
        robotSpeedSlow = 0.2; 
        onChargeStationDegree = 13.0; 
        levelDegree = 6.0; 
        debounceTime = 0.2;
        singleTapTime = 0.4; 
        scoringBackUpTime = 0.2; 

    }
        public double getPitch() {
            double pitch = Math.atan2((-mRioAccel.getX()) , Math.sqrt(mRioAccel.getY() * mRioAccel.getY() + mRioAccel.getZ() * mRioAccel.getZ())) *57.3; 
            System.out.println("pitch is" + pitch); 
            return Math.atan2((-mRioAccel.getX()) , Math.sqrt(mRioAccel.getY() * mRioAccel.getY() + mRioAccel.getZ() * mRioAccel.getZ())) *57.3; 
        
        }

        public double getRoll() {
            System.out.println("AUTO RUNNING");
            return Math.atan2(mRioAccel.getY() , mRioAccel.getZ()) * 57.3; 
        }

        public double getTilt() {
            System.out.println("AUTO RUNNING");
            if((getPitch() + getRoll()) >= 0) {
                return Math.sqrt(getPitch()*getPitch() + getRoll()*getRoll());
            } else {
                return -Math.sqrt(getPitch()*getPitch() + getRoll()*getRoll()); 
            }
        }

        public int secondsToTicks(double time) {
            System.out.println("AUTO RUNNING");
            return(int)(time * 50);
        }

        public double autoBalanceRoutine() {
            System.out.println("AUTO BALANCE IS RUNNING");
            switch (state) {
                case 0: 
                    if(getTilt() > onChargeStationDegree) {
                        debounceCount++; 
                    }
                    if(debounceCount > secondsToTicks(debounceTime)) {
                        state = 1; 
                        debounceCount = 0; 
                        return robotSpeedSlow; 
                    }
                    return robotSpeedFast;
                case 1: 
                    if (getTilt() < levelDegree) {
                        debounceCount++; 
                    }
                    if(debounceCount > secondsToTicks(debounceTime)) {
                        state = 2;
                        debounceCount = 0;
                        return 0;
                    }
                    return robotSpeedSlow; 

                case 2:
                    if(Math.abs(getTilt()) <= levelDegree/2) {
                        debounceCount++; 
                    }
                    if(debounceCount>secondsToTicks(debounceTime)) {
                        state = 4; 
                        debounceCount = 0; 
                        return 0; 
                    }
                    if(getTilt() >= levelDegree) {
                        return 0.1; 
                    } else if(getTilt() <= -levelDegree) {
                        return -0.1; 
                    }
                    System.out.println("AUTO RUNNING");
                case 3:
                    return 0; 

            }
            return 0; 

        }

        public double scoreAndBalance() {
            System.out.println("AUTO RUNNING");
            switch(state) {
                case 0:
                    debounceCount++; 
                    if(debounceCount < secondsToTicks(singleTapTime)) {
                        return -robotSpeedFast;
                    } else if(debounceCount < secondsToTicks(singleTapTime)) {
                        return robotSpeedFast;
                    } else if(debounceCount < secondsToTicks(singleTapTime + scoringBackUpTime)) {
                        return -robotSpeedFast;
                    } else {
                        debounceCount = 0; 
                        state = 1; 
                        return 0; 
                    }
                case 1: 
                    if(getTilt() > onChargeStationDegree) {
                        debounceCount++; 
                    }
                    if(debounceCount > secondsToTicks(debounceTime)) {
                        state = 2; 
                        debounceCount = 0; 
                        return robotSpeedSlow; 
                    }
                    return robotSpeedFast; 
                
                case 2:
                    if(getTilt() < levelDegree) {
                        debounceCount++;
                    }
                    if(debounceCount > secondsToTicks(debounceTime)) {
                        state = 3; 
                        debounceCount = 0; 
                        return 0; 
                    }
                    return robotSpeedSlow;

                case 3:
                    if(Math.abs(getTilt()) <= -levelDegree/2) {
                        debounceCount++; 
                    }
                    if(debounceCount>secondsToTicks(debounceTime)) {
                        state = 4; 
                        debounceCount = 0; 
                        return 0;
                    }
                    if(getTilt() >= levelDegree) {
                        return robotSpeedSlow/2;
                    } else if(getTilt() <= -levelDegree) {
                        return -robotSpeedSlow/2; 
                    }
                case 4: 
                    return 0; 
                
        }
        return 0; 
    }
    public void execute() {
        autoBalanceRoutine();
    }

}
