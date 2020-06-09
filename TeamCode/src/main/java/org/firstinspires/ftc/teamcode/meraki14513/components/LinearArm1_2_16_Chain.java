package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="LinearArm1_2_16_Chain", group="Meraki 14513")
public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor linearChainMotor;

    @Override
    public void init() {
        linearChainMotor = hardwareMap.get(DcMotor.class, "LinearArm1_Motor");
        linearChainMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        linearChainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        if(gamepad2.right_stick_y != 0.0) {
            expandBy((int)-gamepad2.right_stick_y * 30);
        }
        else if(gamepad2.y) {
            expandTo(MAXIMUM);
        }
        else if(gamepad2.a) {
            expandTo(MINIMUM);
        }
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expandTo(int targetMotorPosition) {
        if (targetMotorPosition < MINIMUM) {
            targetMotorPosition = MINIMUM;
        } else if (targetMotorPosition > MAXIMUM) {
            targetMotorPosition = MAXIMUM;
        }
        int realIncrement = targetMotorPosition - linearChainMotor.getCurrentPosition();
        if(realIncrement > 0) {
            linearChainMotor.setPower(1.0);
        }
        else if (realIncrement < 0) {
            linearChainMotor.setPower(-1.0);
        }
        else {
            linearChainMotor.setPower(0.0);
            return;
        }
        linearChainMotor.setTargetPosition(targetMotorPosition);
        linearChainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (linearChainMotor.isBusy()) {
            telemetry.addData("currentPosition", linearChainMotor.getCurrentPosition());
        }
        linearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void expandBy(int increment) {
        expandTo(linearChainMotor.getCurrentPosition() + increment);
    }
}
