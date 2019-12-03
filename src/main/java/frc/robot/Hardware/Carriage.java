package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants.Ports;

public class Carriage extends Subsystem {

    public WPI_TalonSRX armJoint = new WPI_TalonSRX(Ports.armJointMotor.value);
    public Spark wheelClawL = new Spark(Ports.wheelClawL.value);
    public Spark wheelClawR = new Spark(Ports.wheelClawR.value);

    public Carriage(){

        armJoint.setNeutralMode(NeutralMode.Brake);

    }

    public void armJointMove(boolean direction, double speed){

        if (direction = true) {

            armJoint.set(speed);

        }
        else if(direction = false){

            armJoint.set(-speed);

        }
        else{System.out.println("Error: ArmJoint direction invalid");}

    }

    public void wheelClaw(boolean direction, double speed){

        if (direction = true) {

            wheelClawL.set(speed);
            wheelClawR.set(-speed);

        }
        else if(direction = false){

            wheelClawL.set(-speed);
            wheelClawR.set(speed);

        }
        else{System.out.println("Error: WheelClaw direction invalid");}

    }

    public void stopWheelClaw(){

        wheelClawL.set(0);
        wheelClawR.set(0);

    }
    public void stopArmJoint(){armJoint.set(0);}

    @Override
    protected void initDefaultCommand() {}
}