/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ArmJointMove extends Command {
  double speed;
  public ArmJointMove(double speed) {
    requires(Robot.joint);
    this.speed = speed;
  }

  @Override
  protected void execute() {
    Robot.joint.armJointMove(speed);
  }

  @Override
	protected void end() {
		Robot.joint.armStop();
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }
} 
