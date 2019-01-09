package frc.robot.hardware;

import edu.wpi.first.wpilibj.AnalogInput;

public class MB1013Ultrasonic {
    public static final double VI_FRACTIONAL = 5.0 / 1024;

    private AnalogInput device;

    public MB1013Ultrasonic(int port) {
        device = new AnalogInput(port);
    }

    public double getDistance() {
        return (5.0 * (device.getVoltage() / VI_FRACTIONAL)) / 10;
    }
}