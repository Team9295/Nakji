// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class ControllerConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
		public static final double kDeadzone = 0.1;
		public static final double kTriggerDeadzone = .05;

		public static final class Axis {
			public static final int kLeftX = 0;
			public static final int kLeftY = 1;
			public static final int kRightX = 4;
			public static final int kLeftTrigger = 2;
			public static final int kRightTrigger = 3;
			public static final int kRightY = 5;
		}

		public static final class Button {
			public static final int kSquare = 1;
			public static final int kX = 2;
			public static final int kCircle = 3;
			public static final int kTriangle = 4;
			public static final int kLeftBumper = 5;
			public static final int kRightBumper = 6;
			public static final int kShare = 9;
			public static final int kOptions = 10;
			public static final int kLeftStick = 11;
			public static final int kRightStick = 12;
			public static final int kPS = 13;
			public static final int kTrackpad = 14;
		}

		public static final class DPad {
			public static final int kUp = 0;
			public static final int kRight = 90;
			public static final int kDown = 180;
			public static final int kLeft = 270;
		}
  }
  public static final class DriveConstants {
    public static final int kMasterLeftPort = 1; // left motors 1
		public static final boolean kMasterLeftInvert = true;
		public static final int kFollowerLeftPort = 2; // left motor 2
		public static final boolean kFollowerLeftOppose = true;

		public static final int kMasterRightPort = 3; // right motor 3
		public static final boolean kMasterRightInvert = false;
		public static final int kFollowerRightPort = 4; // right motor 4
		public static final boolean kFollowerRightOppose = false;

		public static final double kSpeedLimitFactor = .5;
		public static final double kTurningMultiplier = .5;
  }
  //TODO: add constants for other subsystems (sparkmax - port, invert, percentoutput)

  public static final class RotatingBaseConstants {
	public static final int kRotatingBasePort = 5;
	public static final boolean kRotatingBaseInvert = false;
	public static final double kSpeedLimitFactor = 1;
  }
  public static final class ArmUpConstants {
	public static final int kArmUpPort = 8;
	public static final boolean kArmUpInvert = false;
	public static final double kSpeedLimitFactor = 1;
	public static final int kPIDSlot = 0;
  }
  public static final class ArmExtendConstants {
	public static final int kArmExtendPort = 7;
	public static final boolean kArmExtendInvert = false;
	public static final double kSpeedLimitFactor = 1;
	public static final int kPIDslot = 0; 
  }
  public static final class ArmGrabConstants {
	public static final int kArmGrabPort = 6;
	public static final boolean kArmGrabInvert = false;
	public static final double kSpeedLimitFactor = 1;
  }
  public static final class WristRotationConstants {
	public static final int kWristRotationPort = 9;
	public static final boolean kWristRotationInvert = false;
	public static final double kSpeedLimitFactor = 1;
  }
  public static final class LinearActuatorConstants {
	public static final int kLinearActuatorPort = 10;
	public static final boolean kLinearActuatorInvert = false;
	public static final double kSpeedLimitFactor = 1;
  }
}
