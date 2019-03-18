/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
  
	private WPI_VictorSPX liftMoter = new WPI_VictorSPX(RobotMap.liftID);
	private WPI_TalonSRX armJoint = new WPI_TalonSRX(RobotMap.motorArmJointID);

	public Lift() {
		liftMoter.setNeutralMode(NeutralMode.Brake);
		armJoint.setNeutralMode(NeutralMode.Brake);
 	}

	@Override
	protected void initDefaultCommand() {
		
	}

	public void armJointUp(){
		armJoint.set(RobotMap.armSpeed);
	}

	public void armJointDown(){
		armJoint.set(-(RobotMap.armSpeed));
	}

	public void armStop(){
		armJoint.stopMotor();
	}
	
	public void raise() {
    	liftMoter.set(RobotMap.liftSpeed);
	}
	
	public void lower() {
   		liftMoter.set(-(RobotMap.liftSpeed));
	}
	
	public void stop() {
    	liftMoter.set(0);
	}
}
