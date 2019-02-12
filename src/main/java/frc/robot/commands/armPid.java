/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class armPid extends InstantCommand {
  private double pointTo;
  public armPid(double setPoint){
    requires(Robot.claw);
    pointTo = setPoint;
  }
  @Override
  protected void execute() {
    Robot.claw.armMovement(pointTo);
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
