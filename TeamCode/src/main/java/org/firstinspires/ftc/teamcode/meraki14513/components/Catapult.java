package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Catapult", group="Meraki 14513")
public class Catapult extends BaseComponent{

    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 180;

    private CRServo oneServo;

    @Override
    public void init() {
        oneServo = hardwareMap.get(CRServo.class, "LinearArm1_Motor");
        oneServo.setDirection(CRServo.Direction.FORWARD);
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
        else if(gamepad2.x) {
            expandBy(1.0, 1000);
            // maximum positive power and time
        }
        else if(gamepad2.b) {
            expandBy(-1.0, 1000);
            // maximum negative power and time
        }
        else {
            stop();
        }
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expandBy(double power, int milliseconds) {
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        oneServo.setPower(power);
        while(timer.milliseconds() < milliseconds) {
            telemetry.addData("timer", timer.milliseconds());
        }
    }

    public void stop() {
        oneServo.setPower(0.0);
    }
}

