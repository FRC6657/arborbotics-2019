/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class ArmJointTimed extends TimedCommand {
  public ArmJointTimed(double secs) {
    super(secs);
    requires(Robot.lift);
  }

  @Override
  protected void execute() {
    Robot.lift.armJointDown();
  }

  @Override
	protected void end() {
		Robot.lift.armStop();
  }
}