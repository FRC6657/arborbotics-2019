/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class Solenoid extends InstantCommand {

  boolean solState;
  
  public Solenoid(boolean state) {
    
    super();
    solState = state;

    requires(Robot.pneumatics);

  }

  @Override
  protected void initialize() {

    if(solState = false){Robot.pneumatics.solReverse();}
    else if(solState = true){Robot.pneumatics.solForward();}

  }
}
