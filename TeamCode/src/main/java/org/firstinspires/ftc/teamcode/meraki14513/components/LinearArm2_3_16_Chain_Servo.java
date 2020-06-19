package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Catapult", group="Meraki 14513")
public class LinearArm2_3_16_Chain_Servo extends BaseComponent{

    public static final double MINIMUM = 0.0;
    public static final double MAXIMUM = 2500.0;

    private CRServo oneServo;
    private double counter;

    @Override
    public void init() {
        oneServo = hardwareMap.get(CRServo.class, "CatapultServo");
        oneServo.setDirection(CRServo.Direction.FORWARD);
        counter = 0.0;
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // change these controls to proper ones to avoid simultaneous moving of arms
        if(gamepad2.right_stick_x != 0.0) {
            int milliseconds = (int) Math.abs(gamepad2.right_stick_x * 100);
            double power = 1.0;
            if(gamepad2.right_stick_x < 0.0) {
                power = -1.0;
            }
            expandBy(power, milliseconds);
        }
        else if(gamepad2.b) {
            expandBy(1.0, 2000);
            // maximum positive power and time
        }
        else if(gamepad2.x) {
            expandBy(-1.0, 2000);
            // maximum negative power and time
        }
        else {
            stop();
        }
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expandBy(double power, int milliseconds) {
        double target = power*milliseconds;
        if(target > MAXIMUM) {
            target = MAXIMUM;
        }
        else if(target < MINIMUM) {
            target = MINIMUM;
        }
        milliseconds = (int)(target/power);
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        oneServo.setPower(power);
        while(timer.milliseconds() < milliseconds) {
            telemetry.addData("timer", timer.milliseconds());
        }
        counter += power*milliseconds;
    }

    public void stop() {
        oneServo.setPower(0.0);
    }
}