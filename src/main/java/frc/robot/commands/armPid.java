/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class armPid extends Command {
public armPid(double setPoint){
  requires(Robot.claw);
}
  @Override
  protected void execute() {
    Robot.foot.reverse();
  }

  @Override
	protected void end() {
		Robot.foot.stop();
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }
}
