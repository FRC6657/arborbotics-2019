/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ClawIntake;
import frc.robot.commands.ClawRelease;
import frc.robot.commands.LiftRaise;
import frc.robot.commands.LiftLower;
import frc.robot.commands.ArmJointMove;
import frc.robot.commands.ArmJointMoveTimed;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//Controllers
	private Joystick happyStick;
	private XboxController coolController;
	
	//Buttons
	public JoystickButton clawGrab;
	public JoystickButton clawRelease;
	public JoystickButton clawGrab1;
	public JoystickButton clawRelease1;
	public JoystickButton liftUp;
	public JoystickButton liftDown;
	public JoystickButton footForward;
	public JoystickButton footBackward;
	public JoystickButton armJointUp;
	public JoystickButton armJointDown;
	public JoystickButton armJointUp1;
	public JoystickButton armJointDown1;

	public OI() {
		//Configures controllers and buttons
		happyStick = new Joystick(RobotMap.joystickID);
		coolController = new XboxController(RobotMap.gamePadID);
		liftUp = new JoystickButton(happyStick, 1);
		liftDown = new JoystickButton(happyStick, 2);
		clawGrab1 = new JoystickButton(happyStick, 5);
		clawRelease1 = new JoystickButton(happyStick, 3);
		armJointUp1 = new JoystickButton(happyStick, 6);
		armJointDown1 = new JoystickButton(happyStick, 4);
		clawGrab = new JoystickButton(coolController, 1);
		clawRelease = new JoystickButton(coolController, 2);
		armJointUp = new JoystickButton(coolController, 6);
		armJointDown = new JoystickButton(coolController, 5);

		ArmJointMove aju = new ArmJointMove(RobotMap.armSpeed); //Arm Joint
		armJointUp.whileHeld(aju);
		armJointUp1.whileHeld(aju);
		ArmJointMove ajd = new ArmJointMove(- RobotMap.armSpeed);
		armJointDown.whileHeld(ajd);
		armJointDown1.whileHeld(ajd);

		ClawIntake cg = new ClawIntake(); //Claw
		clawGrab.whileHeld(cg);
		clawGrab1.whileHeld(cg);
		ClawRelease cr = new ClawRelease();
		clawRelease.whileHeld(cr);
		clawRelease1.whileHeld(cr);
		
		LiftRaise lu = new LiftRaise(); //Lift
		liftUp.whileHeld(lu);
		LiftLower ld = new LiftLower();
		liftDown.whileHeld(ld);
	}

	public double getArcadeSpeed() { //Gets modified joystick speed
		double supaY = happyStick.getY();
		return (-supaY*supaY*Math.signum(supaY) + 0.1) * RobotMap.joystickArcadeSpeedModifier;
	}

 	public double getArcadeRotation() { //Gets joystick rotation
		double supaTwist =  happyStick.getTwist();
		return (supaTwist) * RobotMap.joystickArcadeRotationModifier * getScaledThrottle();
	}
	
	public double getArcadeRoationThrottle() { //Gets joystick rotation
		double supaTwist = happyStick.getTwist() * getScaledThrottle();
		return supaTwist;
	}
	
	public double getScaledThrottle() { //Gets throttle switch as a double 0-1
		double supaThrottle = happyStick.getThrottle();
		if (supaThrottle < 0.4) 
			supaThrottle = 0.4;
		return (0.5 * supaThrottle) + 0.5;
  }

  public void setDriverProfile(String tag) { //Concept of using RFIDs for driver profiles (((even though they are objectively BAD)))
	switch(tag) {
	  case "DUAL":
	    clawGrab = new JoystickButton(coolController, 1);
	    clawRelease = new JoystickButton(coolController, 2);
	    liftUp = new JoystickButton(happyStick, 1);
	    liftDown = new JoystickButton(happyStick, 2);
	    armJointUp = new JoystickButton(coolController, 6);
	    armJointDown = new JoystickButton(coolController, 5);
	    footForward = new JoystickButton(happyStick, 9);
	    footBackward = new JoystickButton(happyStick, 11);
		break;
	  default:
	 	clawGrab = new JoystickButton(happyStick, 5);
		clawRelease = new JoystickButton(happyStick, 3);
		liftUp = new JoystickButton(happyStick, 1);
		liftDown = new JoystickButton(happyStick, 2);
		footForward = new JoystickButton(happyStick, 9);
		footBackward = new JoystickButton(happyStick, 11);
		break;
	  }
  }
}
