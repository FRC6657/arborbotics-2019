/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;



public class Pneumatics extends Subsystem {

//DoubleSolenoid sol1 = new DoubleSolenoid(forwardChannel, reverseChannel);
//DoubleSolenoid sol2 = new DoubleSolenoid(forwardChannel, reverseChannel);

  @Override
  public void initDefaultCommand() {
  }
  public void fireSol() {

  //sol1.set(Value.kForward);
  //sol2.set(Value.kForward);

  }
  public void retractSol() {
    
    //sol1.set(Value.kReverse);
    //sol2.set(Value.kReverse);
  
  }
  
}
