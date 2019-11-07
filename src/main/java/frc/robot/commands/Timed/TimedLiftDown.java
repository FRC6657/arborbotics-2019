package frc.robot.Commands.Timed;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class TimedLiftDown extends TimedCommand {

    double speed;
    
    public TimedLiftDown(double secs, double spd) {

        super(secs);
        speed = spd;

    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

        Robot.lift.liftMotor.set(-speed);

    }

    @Override
    protected void end() {
        Robot.lift.liftMotor.set(0);
    }
}
