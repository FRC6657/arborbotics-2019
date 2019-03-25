/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class ClawReleaseTimed extends TimedCommand {
  double ReleaseSpeed;
  public ClawReleaseTimed(double secs, double speed) {
    super(secs);
    requires(Robot.claw);
    this.ReleaseSpeed = speed;
  }

  @Override
  protected void execute() {
    Robot.claw.release(ReleaseSpeed);
  }

  @Override
  protected void end() {
    Robot.claw.stop();
  }
}