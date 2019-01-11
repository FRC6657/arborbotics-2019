/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class UltrasonicAuto extends Command {
  private double centimeterTarget;
  public UltrasonicAuto(double centimetersTo) {
    requires(Robot.driveLocomotive);
    centimeterTarget = centimetersTo;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveLocomotive.reset();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  while(Robot.driveLocomotive.getUltraSonicDistance() > centimeterTarget) {
    Robot.driveLocomotive.drive(0.4, 0.4);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveLocomotive.getUltraSonicDistance() <= (centimeterTarget + 2.5) && Robot.driveLocomotive.getUltraSonicDistance() >= (centimeterTarget - 2.5)) {
      Robot.driveLocomotive.stop();
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveLocomotive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveLocomotive.stop();
  }
}
