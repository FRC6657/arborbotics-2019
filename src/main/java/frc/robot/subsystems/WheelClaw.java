package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class WheelClaw extends Subsystem {
	
	private Spark spark1 = new Spark(0);
	private Spark spark2 = new Spark(1);
	private WPI_TalonSRX armJoint = new WPI_TalonSRX(RobotMap.motorArmJointID);

	public WheelClaw() {
		armJoint.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
		RobotMap.kPIDLoopIdx,
		RobotMap.kTimeoutMs);
	
		armJoint.setSensorPhase(RobotMap.kSensorPhase);
		armJoint.setInverted(RobotMap.kMotorInvert);
	
		/* Config the peak and nominal outputs, 12V means full */
		armJoint.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		armJoint.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		armJoint.configPeakOutputForward(1, RobotMap.kTimeoutMs);
		armJoint.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);
	
		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		armJoint.configAllowableClosedloopError(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	
		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		armJoint.config_kF(RobotMap.kPIDLoopIdx, RobotMap.kGains.kF, RobotMap.kTimeoutMs);
		armJoint.config_kP(RobotMap.kPIDLoopIdx, RobotMap.kGains.kP, RobotMap.kTimeoutMs);
		armJoint.config_kI(RobotMap.kPIDLoopIdx, RobotMap.kGains.kI, RobotMap.kTimeoutMs);
		armJoint.config_kD(RobotMap.kPIDLoopIdx, RobotMap.kGains.kD, RobotMap.kTimeoutMs);
	
		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = armJoint.getSensorCollection().getPulseWidthPosition();
	
		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (RobotMap.kSensorPhase) { absolutePosition *= -1; }
		if (RobotMap.kMotorInvert) { absolutePosition *= -1; }
			
		/* Set the quadrature (relative) sensor to match absolute */
		armJoint.setSelectedSensorPosition(absolutePosition, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}


	@Override
	protected void initDefaultCommand() {
	}
	
	public void grab() {
		spark1.set(-(RobotMap.wheelClawSpeed));
		spark2.set(-(RobotMap.wheelClawSpeed));
	}
	
	public void armMovement(double setPoint){
		armJoint.set(ControlMode.MotionMagic, setPoint);
	}

	public void armStop(){
		armJoint.stopMotor();
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