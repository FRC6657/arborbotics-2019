/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class Foot extends Subsystem {
  
  private Spark spark = new Spark(2);

  public Foot() {
  }

  @Override
  public void initDefaultCommand() {
  }

	public void forward() {
		spark.set(-(RobotMap.footSpeed));
	}
	
	public void reverse() {
		spark.set(RobotMap.footSpeed);
	}
	
	public void stop() {
		spark.set(0);
	}

}
