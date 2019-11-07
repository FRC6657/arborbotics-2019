package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class TimedTurnLeft extends TimedCommand{

    double speed;
    
    public TimedTurnLeft(double secs, double spd){

        super(secs);
        speed = spd;

    }
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

        Robot.driveTrain.frontLeftMotor.set(-speed);
        Robot.driveTrain.backLeftMotor.set(-speed);
        Robot.driveTrain.frontRightMotor.set(-speed);
        Robot.driveTrain.backRightMotor.set(-speed);

    }

    @Override
    protected void end() {

        Robot.driveTrain.frontLeftMotor.set(0);
        Robot.driveTrain.backLeftMotor.set(0);
        Robot.driveTrain.frontRightMotor.set(0);
        Robot.driveTrain.backRightMotor.set(0);
        
    }
  }
