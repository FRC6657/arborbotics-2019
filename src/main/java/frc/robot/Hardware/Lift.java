package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants.Ports;

public class Lift extends Subsystem {

    private WPI_TalonSRX liftMotor = new WPI_TalonSRX(Ports.liftMotor.value);
    
    public Lift(){liftMotor.setNeutralMode(NeutralMode.Brake);}

    public void moveLift(Boolean direction, double speed) {

        if (direction = true) {

            liftMotor.set(speed);

        }
        else if(direction = false){

            liftMotor.set(-speed);

        }
        else{System.out.println("Error: Lift direction invalid");}

    }
    public void stopLift(){liftMotor.set(0);}

    @Override
    protected void initDefaultCommand() {}
}