/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Solenoid;



public class Pneumatics extends Subsystem {
  private Spark spark3 = new Spark(2);
  private Solenoid s1,s2;

  @Override
  public void initDefaultCommand() {
    
    s1 = new Solenoid(1);
    s2 = new Solenoid(2);



  }
  public void fireSol() {

    s1.set(true);
    s2.set(true);

  }
  public void retractSol() {
    
    s1.set(false);
    s2.set(false);
  
  }
}
