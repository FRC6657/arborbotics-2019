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
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
  
	private WPI_TalonSRX liftMoter = new WPI_TalonSRX(RobotMap.liftID);


	public Lift() {
		liftMoter.setNeutralMode(NeutralMode.Brake);

 	}

	@Override
	protected void initDefaultCommand() {
		
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
