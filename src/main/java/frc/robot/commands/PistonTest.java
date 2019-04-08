
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class PistonTest extends CommandGroup {
  
  public PistonTest(){

    addSequential(new SolOut());
    addSequential(new SleepDrive(1));
    addSequential(new SolIn());

  }

}
