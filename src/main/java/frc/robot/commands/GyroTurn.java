/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GyroTurn extends Command {
  private double targetAngle;
  public GyroTurn(double degrees) {
    
    requires(Robot.driveLocomotive);
    targetAngle = degrees + Robot.driveLocomotive.getAngle();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveLocomotive.reset();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if( Math.abs(Robot.driveLocomotive.getAngle()) >= Math.abs(targetAngle))  {
      
      Robot.driveLocomotive.drive(0, 0);
    
    } else if( targetAngle > Robot.driveLocomotive.getAngle() ) {
    
      Robot.driveLocomotive.drive(-0.5, 0.5);
    
    } else {

      Robot.driveLocomotive.drive(0.5, 0.5);

    }

    if(Math.abs(Robot.driveLocomotive.getAngle() - targetAngle) <= 2) {
      Robot.driveLocomotive.stop();
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
    Robot.driveLocomotive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveLocomotive.stop();
  }
}
