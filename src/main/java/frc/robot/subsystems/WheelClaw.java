package frc.robot.subsystems;

import frc.robot.RobotMap;
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
		spark1.set(-(RobotMap.wheelClawSpeed + 0.20));
		spark2.set(-(RobotMap.wheelClawSpeed + 0.20));
	}

	public void release() {
		spark1.set(RobotMap.wheelClawSpeed + 0.15);
		spark2.set(RobotMap.wheelClawSpeed + 0.15);
	}
	
	public void stop() {
		spark1.set(0);
		spark2.set(0);
	}

	public void release(double releasespeed) {
	}

	public void grab(double intakeSpeed) {
	}		
}