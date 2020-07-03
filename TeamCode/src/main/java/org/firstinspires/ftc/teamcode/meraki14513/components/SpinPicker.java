package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Spin Picker", group="Meraki 14513")
public class SpinPicker extends BaseComponent{

    private CRServo servoLeftUpper;
    private CRServo servoRightUpper;
    private CRServo servoLeftLower;
    private CRServo servoRightLower;


    @Override
    public void init() {
        servoLeftLower = hardwareMap.get(CRServo.class, "ServoLeftLower");
        servoLeftLower.setDirection(CRServo.Direction.FORWARD);
        servoRightLower = hardwareMap.get(CRServo.class, "ServoRightLower");
        servoRightLower.setDirection(CRServo.Direction.FORWARD);
        servoLeftUpper = hardwareMap.get(CRServo.class, "ServoLeftUpper");
        servoLeftUpper.setDirection(CRServo.Direction.FORWARD);
        servoRightUpper = hardwareMap.get(CRServo.class, "ServoRightUpper");
        servoRightUpper.setDirection(CRServo.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // change these controls to proper ones to avoid simultaneous moving of arms
        if(gamepad2.x) {
            double power = 1.0;
            spin(power, 2000);
        }
        else {
            stop();
        }
    }

    public void spin(double power, int milliseconds) {
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        servoLeftLower.setPower(-power);
        servoRightLower.setPower(power);
        servoLeftUpper.setPower(power);
        servoRightUpper.setPower(-power);
        while(timer.milliseconds() < milliseconds) {
            telemetry.addData("timer", timer.milliseconds());
        }
    }

    public void stop() {
        servoLeftUpper.setPower(0.0);
        servoRightUpper.setPower(0.0);
        servoLeftLower.setPower(0.0);
        servoRightLower.setPower(0.0);
    }
}

