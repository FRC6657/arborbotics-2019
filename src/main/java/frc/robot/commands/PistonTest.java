
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class PistonTest extends CommandGroup {
  
  public PistonTest(){

    addSequential(new SolOut());
    addSequential(new Sleep(1));
    addSequential(new SolIn());

  }

}
