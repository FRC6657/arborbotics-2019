package frc.robot.Commands.Timed;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class TimedDrive extends TimedCommand{

    double speed;

    public TimedDrive(double secs, double spd){

        super(secs);
        speed = spd;

    }
    
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.driveTrain.frontLeftMotor.set(speed);
        Robot.driveTrain.frontRightMotor.set(-speed);
    }
  
    @Override
    protected void end() {
        Robot.driveTrain.frontLeftMotor.set(0);
        Robot.driveTrain.frontRightMotor.set(0);
    }
  }
