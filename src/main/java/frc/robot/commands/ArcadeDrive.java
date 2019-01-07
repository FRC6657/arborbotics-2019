/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
  public ArcadeDrive() {
    requires(Robot.driveLocomotive);
  }

  @Override
  protected void execute() {
    Robot.driveLocomotive.arcadeDrive(Robot.oi.getArcadeSpeed(), Robot.oi.getArcadeRoationThrottle());
  }

  @Override
  protected void end() {
    Robot.driveLocomotive.stop();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
