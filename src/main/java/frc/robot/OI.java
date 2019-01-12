/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ClawGrab;
import frc.robot.commands.ClawRelease;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
	private Joystick happyStick;
	public JoystickButton clawGrab;
	public JoystickButton clawRelease;

	public OI() {
		happyStick = new Joystick(RobotMap.driveControllerID);
		clawGrab = new JoystickButton(happyStick, 5);
		clawRelease = new JoystickButton(happyStick, 3);
		
		ClawGrab cg = new ClawGrab();
		clawGrab.whileHeld(cg);
		
		ClawRelease cr = new ClawRelease();
		clawRelease.whileHeld(cr);
	}

	public double getArcadeSpeed() {
		double supaY = ((Joystick) happyStick).getY();
		return -supaY*supaY*Math.signum(supaY) * RobotMap.joystickArcadeSpeedModifier;
	}
   
 	public double getArcadeRotation() {
		double supaTwist = ((Joystick) happyStick).getTwist();
		return supaTwist * RobotMap.joystickArcadeRotationModifier;
	}
	
	public double getArcadeRoationThrottle() {
		double supaTwist = ((Joystick) happyStick).getTwist();
		return supaTwist;
	}
	
	public double getScaledThrottle() {
		double supaThrottle = ((Joystick) happyStick).getThrottle();
		return (0.5 * supaThrottle) + 0.5;
  }
}
