/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class ClawIntakeTimed extends TimedCommand {
  double IntakeSpeed;
  public ClawIntakeTimed(double secs, double speed) {
    super(secs);
    requires(Robot.claw);
    this.IntakeSpeed = speed;
  }

  @Override
  protected void execute() {
    Robot.claw.grab(IntakeSpeed);
  }

  @Override
  protected void end() {
    Robot.claw.stop();
  }
}
