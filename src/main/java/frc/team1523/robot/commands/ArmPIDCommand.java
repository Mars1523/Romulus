package frc.team1523.robot.commands;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1523.robot.Robot;

import static frc.team1523.robot.RobotMap.*;

public class ArmPIDCommand extends Command { // This system extends PIDSubsystem

    private Spark armSpark = new Spark(ARM_SPARK);

    private double minAngle = 62;
    private double maxAngle = 186.2;


    // P: 0.04, I: 0.003, D: 0.002
    public ArmPIDCommand(double startPoint) {

    }

    private double clamp(double value) {
        return Math.max(minAngle, Math.min(maxAngle, value));
    }

    // TODO: Fix this
    public void execute() {
        SmartDashboard.putData(this);
//        if (Robot.oi.joystick.getRawButton(9)) {
//            setSetpoint(switchPos);
//            return;
//        }
//        if (Robot.oi.joystick.getRawButton(10)) {
//            setSetpoint(scalePos);
//            return;
//        }
//        if (Robot.oi.joystick.getRawButton(11)) {
//            setSetpoint(0);
//            return;
//        }


        double rightBumper = Robot.oi.gamepad.getRawAxis(BUMPER_ANALOG_RIGHT);
        double leftBumper = Robot.oi.gamepad.getRawAxis(BUMPER_ANALOG_LEFT);

        if (rightBumper > 0.05) {
            armSpark.set(-squared(rightBumper));
        } else if (leftBumper > 0.05) {
            armSpark.set(squared(leftBumper) * .55);
        }
    }

    private double squared(double input) {
        // Make sure to keep the sign so the direction you input is not changed by squaring it
        if (input > 0) {
            return input * input;
        } else {
            return -(input * input);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}

//package frc.team1523.robot.commands;
//
//import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.command.PIDCommand;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.team1523.robot.Robot;
//
//import static frc.team1523.robot.RobotMap.*;
//
//public class ArmPIDCommand extends PIDCommand { // This system extends PIDSubsystem
//
//    private Spark armSpark = new Spark(ARM_SPARK);
//
//    public double setpoint = 0;
//
////    private double minAngle = 154.4;
////    private double maxAngle = 289.2;
//
//    private double minAngle = 62;
//    private double maxAngle = 186.2;
//
////    private double switchPos = 196;
////    private double scalePos = 277;
//
//    private double switchPos = 78;
//    private double scalePos = 192;
//
//
//    // P: 0.04, I: 0.003, D: 0.002
//    public ArmPIDCommand(double startPoint) {
////        super("ArmPIDCommand", 0.06, 0.003, 0.004);
//        super("ArmPIDCommand", 0.03, 0.0003, 0);
//        getPIDController().setPercentTolerance(10);
//
//        getPIDController().setContinuous(false);
//
//        setInputRange(minAngle, maxAngle);
//
//        getPIDController().setOutputRange(-.2, .7);// -.2, 6
//
//        getPIDController().setSetpoint(startPoint);
//        setpoint = startPoint;
//    }
//
//    private double clamp(double value) {
//        return Math.max(minAngle, Math.min(maxAngle, value));
//    }
//
//    public void setSetpoint(double setpoint) {
//        this.setpoint = clamp(setpoint);
//        getPIDController().setSetpoint(this.setpoint);
//    }
//
//    public void enable() {
//        getPIDController().enable();
//    }
//
//    public void disable() {
//        getPIDController().disable();
//    }
//
//    public boolean onTarget() {
//        return getPIDController().onTarget();
//    }
//
//    protected double returnPIDInput() {
//        return Robot.armEncoder.getPWMAngle();
//    }
//
//    protected void usePIDOutput(double output) {
//        armSpark.set(-output);
//    }
//
//    // TODO: Fix this
//    public void execute() {
//        SmartDashboard.putData(this);
//        if (Robot.oi.joystick.getRawButton(9)) {
//            setSetpoint(switchPos);
//            return;
//        }
//        if (Robot.oi.joystick.getRawButton(10)) {
//            setSetpoint(scalePos);
//            return;
//        }
//        if (Robot.oi.joystick.getRawButton(11)) {
//            setSetpoint(0);
//            return;
//        }
//
//
//        double rightBumper = Robot.oi.gamepad.getRawAxis(BUMPER_ANALOG_RIGHT);
//        double leftBumper = Robot.oi.gamepad.getRawAxis(BUMPER_ANALOG_LEFT);
//
//        if (rightBumper > 0.05) {
//            setSetpoint(setpoint + (0.85 * rightBumper));
//        } else if (leftBumper > 0.05) {
//            setSetpoint(setpoint - (0.7 * leftBumper));
//        }
//    }
//
//    @Override
//    protected boolean isFinished() {
//        return false;
//    }
//}