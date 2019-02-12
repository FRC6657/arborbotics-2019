/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
	public static int motorFrontLeftID = 1;
	public static int motorBackLeftID = 2;
	public static int motorFrontRightID = 3;
	public static int motorBackRightID = 4;
	public static int motorArmJointID = 6;
	public static int gyroID = 5;
	public static int liftID = 1;
	
	
	public static int driveControllerID = 0;
	public static int gamePadID = 1;

	public static double joystickSpeedDeadband = 0.35;
	public static double joystickRotationDeadband = 0.35;
	
	public static double joystickArcadeSpeedModifier = 1.0;
	public static double joystickArcadeRotationModifier = 1.0;

	public static double wheelClawSpeed = 0.4d;
	public static double liftSpeed = 0.3d;
	public static double footSpeed = 0.4d;

	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 30;
	
	/* Choose so that Talon does not report sensor out of phase */
	public static boolean kSensorPhase = true;

	/**
	 * Choose based on what direction you want to be positive,
	 * this does not affect motor invert. 
	 */
	public static boolean kMotorInvert = false;

	
	/**
	 * Gains used in Positon Closed Loop, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     */
    public static final Gains kGains = new Gains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
}

