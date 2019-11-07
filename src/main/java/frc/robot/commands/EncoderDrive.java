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

  public EncoderDrive(double distance, double speed) {
    
    dis = distance;
    spd = speed;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(Robot.driveTrain.leftEncoder.getDistance() < dis){
     
      Robot.driveTrain.frontLeftMotor.set(spd);
      Robot.driveTrain.frontRightMotor.set(-spd);

    } else 
    {
      Robot.driveTrain.frontLeftMotor.set(0);
      Robot.driveTrain.frontRightMotor.set(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
