/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ArmJointDown extends Command {
  public ArmJointDown() {
    requires(Robot.claw);
  }

  @Override
  protected void execute() {
    Robot.claw.armJointDown();
  }

  @Override
	protected void end() {
		Robot.claw.armStop();
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }
}