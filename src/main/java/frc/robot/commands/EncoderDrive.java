/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderDrive extends Command {

  double dis;
  double spd;
  double endDistance;

  public EncoderDrive(double speed, double distance) {
    
    requires(Robot.driveTrain);
    dis = distance;
    spd = speed;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    endDistance = Robot.driveTrain.getRightEncoderDistance() + dis;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.driveTrain.driveStrait(spd);

  }

  @Override
  protected boolean isFinished() {
    return (Robot.driveTrain.getLeftEncoderDistance() >= endDistance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
