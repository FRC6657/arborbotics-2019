/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Compressor;

//This holds the Solenoid and Compressor.
public class Pneumatics extends Subsystem {

  private DoubleSolenoid hatchIntake = new DoubleSolenoid(0, 1);
  private Compressor compressor = new Compressor();
  
  @Override
  public void initDefaultCommand() {


  }
  public void solForward(){hatchIntake.set(Value.kReverse);}
  public void solReverse(){hatchIntake.set(Value.kReverse);}
  public void compressorOn(){compressor.setClosedLoopControl(false);}
  public void compressorOff(){compressor.setClosedLoopControl(true);}

}

