package frc.robot.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.Timed.*;

public class BasicHatchAuto extends CommandGroup {

    public BasicHatchAuto() {

        addSequential(new TimedDrive(1, 0.5));
        addParallel(new TimedTurnLeft(0.5, 0.5));
        addSequential(new TimedDrive(1, 0.5));

    }
}