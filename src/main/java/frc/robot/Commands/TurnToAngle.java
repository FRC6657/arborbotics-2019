/*
package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {
  double Angle;
  boolean isFinished = false;
  boolean inErrorZone = false;
  int count;

  public TurnToAngle(double angle) {
    
    requires(Robot.pidDrivetrain);

    Angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.pidDrivetrain.rotateDeg(Angle);

    

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double error = Robot.pidDrivetrain.pid.getError();

    inErrorZone = (Math.abs(error) <2);
    //If the robot is in the error zone for 5 Rio Ticks stop the command
    if(inErrorZone){ 
      count++;
      isFinished = count >=5 ;
    }
    else{count = 0;}
  }

  // Make this return true whe-n this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.pidDrivetrain.pid.disable();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

*/