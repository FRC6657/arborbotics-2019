package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import frc.robot.Robot;

public class Wait extends TimedCommand{

    public Wait(double secs) {
        
        super(secs);

    }
      
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    Robot.driveTrain.frontLeftMotor.set(0);
    Robot.driveTrain.backLeftMotor.set(0);
    Robot.driveTrain.frontRightMotor.set(0);
    Robot.driveTrain.backRightMotor.set(0);
    Robot.lift.liftMotor.set(0);
    Robot.armJoint.armJointMotor.set(0);
    Robot.pneumatics.compressorOff();    
  }

  // Called once after timeout
  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }

}