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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick happyStick;
	private XboxController coolController;
	public JoystickButton clawGrab;
	public JoystickButton clawRelease;
	public JoystickButton liftUp;
	public JoystickButton liftDown;
	public JoystickButton footForward;
	public JoystickButton footBackward;

	public OI() {
		happyStick = new Joystick(RobotMap.driveControllerID);
		coolController = new XboxController(RobotMap.gamePadID);
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
		double supaY = happyStick.getY();
		return supaY > RobotMap.joystickSpeedDeadband ? (-supaY*supaY*Math.signum(supaY) + 0.1) * RobotMap.joystickArcadeSpeedModifier : 0;
	}

 	public double getArcadeRotation() {
		double supaTwist =  happyStick.getTwist();
		return supaTwist > RobotMap.joystickRotationDeadband ? (supaTwist + 0.1) * RobotMap.joystickArcadeRotationModifier : 0;
	}
	
	public double getArcadeRoationThrottle() {
		double supaTwist = happyStick.getTwist();
		return supaTwist;
	}
	
	public double getScaledThrottle() {
		double supaThrottle = happyStick.getThrottle();
		return (0.5 * supaThrottle) + 0.5;
  }

  public void setDriverProfile(String RFID) {
	  switch(RFID) {
		  case "DUAL":
			clawGrab = new JoystickButton(coolController, 1);
			clawRelease = new JoystickButton(coolController, 2);
			liftUp = new JoystickButton(coolController, 5);
			liftDown = new JoystickButton(coolController, 6);
		  	footForward = new JoystickButton(coolController, 4);
		  	footBackward = new JoystickButton(coolController, 3);
		    break;
		  default:
		 	clawGrab = new JoystickButton(happyStick, 5);
			clawRelease = new JoystickButton(happyStick, 3);
			liftUp = new JoystickButton(happyStick, 2);
			liftDown = new JoystickButton(happyStick, 1);
			footForward = new JoystickButton(happyStick, 9);
			footBackward = new JoystickButton(happyStick, 11);
			break;
	  }
  }
}
