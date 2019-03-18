/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class TimedReverse extends TimedCommand {
  double speed;
  public TimedReverse(double secs, double spd) {
    super(secs);
    speed = spd;
    requires(Robot.driveLocomotive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveLocomotive.drive(speed, speed);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveLocomotive.drive(0, 0);
  }
}
