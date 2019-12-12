/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToLocation extends Command {

  Double TarL;
  Double TarR;

  public DriveToLocation(double tarL, double tarR) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    TarL = tarL;
    TarR = tarR;
  }
  
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   
    Robot.drivetrain.driveRobotToTargetWithEncoders(TarL, TarR);
  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() { 
    
    if(Math.abs(Robot.drivetrain.getLeftEncoderDistance()) > Math.abs(TarL) && Math.abs(Robot.drivetrain.getRightEncoderDistance()) > Math.abs(TarR)){

      return true;
      
    }
    else{return false;}

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
