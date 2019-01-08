package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vacuum extends Subsystem {
	
	private Spark vacuum = new Spark(0);

	public Vacuum() {
    
  }

	@Override
	protected void initDefaultCommand() {
	}
	
	public void grab() {
		vacuum.set(RobotMap.vacuumSpeedInitial);
	}
	
	public void hooked() {
		vacuum.set(RobotMap.vacuumSpeedHooked);
  }
	
	public void stop() {
		vacuum.set(0);
	}
}