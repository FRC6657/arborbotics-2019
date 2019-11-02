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
  public ClawReleaseTimed(double secs) {
    super(secs);
    requires(Robot.claw);
  }

  @Override
  protected void execute() {
    Robot.claw.release();
  }

  @Override
  protected void end() {
    Robot.claw.stop();
  }
}