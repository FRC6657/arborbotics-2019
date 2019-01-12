package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelClaw extends Subsystem {
	
	private Spark spark1 = new Spark(0);
	private Spark spark2 = new Spark(1);

	public WheelClaw() {
		
 	}

	@Override
	protected void initDefaultCommand() {
	}
	
	public void grab() {
		spark1.set(-(RobotMap.wheelClawSpeed));
		spark2.set(-(RobotMap.wheelClawSpeed));
	}
	
	public void release() {
		spark1.set(RobotMap.wheelClawSpeed);
		spark2.set(RobotMap.wheelClawSpeed);
	}
	
	public void stop() {
		spark1.set(0);
		spark2.set(0);
	}
}