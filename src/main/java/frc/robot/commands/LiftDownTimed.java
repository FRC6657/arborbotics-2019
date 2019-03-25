/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class LiftDownTimed extends TimedCommand {
  double liftspeed;
  public LiftDownTimed(int secs, double speed) {
    super(secs);
    requires(Robot.lift);
    this.liftspeed = speed;
  }

  @Override
  protected void execute() {
    Robot.lift.lower(liftspeed);
  }

  @Override
  protected void end() {
    Robot.lift.stop();
  }
}
