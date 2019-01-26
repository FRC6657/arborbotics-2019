/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ClawGrab;
import frc.robot.commands.ClawRelease;
import frc.robot.commands.LiftRaise;
import frc.robot.commands.LiftLower;
import frc.robot.commands.FootForward;
import frc.robot.commands.FootReverse;
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
	public JoystickButton liftUp;
	public JoystickButton liftDown;
	public JoystickButton footForward;
	public JoystickButton footBackward;

	public OI() {
		happyStick = new Joystick(RobotMap.driveControllerID);
		clawGrab = new JoystickButton(happyStick, 5);
		clawRelease = new JoystickButton(happyStick, 3);
		liftUp = new JoystickButton(happyStick, 2);
		liftDown = new JoystickButton(happyStick, 1);
		footForward = new JoystickButton(happyStick, 9);
		footBackward = new JoystickButton(happyStick, 11);
		
		ClawGrab cg = new ClawGrab();
		clawGrab.whileHeld(cg);
		ClawRelease cr = new ClawRelease();
		clawRelease.whileHeld(cr);
		
		LiftRaise lu = new LiftRaise();
		liftUp.whileHeld(lu);
		LiftLower ld = new LiftLower();
		liftDown.whileHeld(ld);

		FootForward ff = new FootForward();
		footForward.whileHeld(ff);
		FootReverse fb = new FootReverse();
		footBackward.whileHeld(fb);
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
