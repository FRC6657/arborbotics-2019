/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class CompressorControl extends InstantCommand {

  boolean compressorState;
  
  public CompressorControl(boolean state) {
    super();

    compressorState = state;

  }

  @Override
  protected void initialize() {

    if(compressorState = false){Robot.pneumatics.compressorOff();}
    else if(compressorState = true){Robot.pneumatics.compressorOn();}
    
  }
}
