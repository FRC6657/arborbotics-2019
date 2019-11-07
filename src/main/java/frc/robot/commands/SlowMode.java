/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SlowMode extends Command {
  public SlowMode() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Robot.driveTrain.SpeedModifier = 0.2;

    /*
    Robot.driveTrain.frontLeftMotor.set(Robot.driveTrain.frontLeftMotor.getMotorOutputPercent() - 0.3);
    Robot.driveTrain.backLeftMotor.set(Robot.driveTrain.backLeftMotor.getMotorOutputPercent() - 0.3);
    Robot.driveTrain.frontRightMotor.set(Robot.driveTrain.frontRightMotor.getMotorOutputPercent() - 0.3);
    Robot.driveTrain.backRightMotor.set(Robot.driveTrain.backRightMotor.getMotorOutputPercent() - 0.3);
    */
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    Robot.driveTrain.SpeedModifier = 0.5;
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
