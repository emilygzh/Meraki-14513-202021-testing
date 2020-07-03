package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="DoubleReverseArm", group="Meraki 14513")
public final class DoubleReverseArm extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor DoubleReverseArmMotor;
    //or servo

    @Override
    public void init() {
        DoubleReverseArmMotor = hardwareMap.get(DcMotor.class, "DoubleReverseArm_Motor");
        DoubleReverseArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        DoubleReverseArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DoubleReverseArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    private void expandTo(int targetMotorPosition) {
        if (targetMotorPosition < MINIMUM) {
            targetMotorPosition = MINIMUM;
        } else if (targetMotorPosition > MAXIMUM) {
            targetMotorPosition = MAXIMUM;
        }
        int realIncrement = targetMotorPosition - DoubleReverseArmMotor.getCurrentPosition();
        if(realIncrement > 0) {
            DoubleReverseArmMotor.setPower(1.0);
        }
        else if (realIncrement < 0) {
            DoubleReverseArmMotor.setPower(-1.0);
        }
        else {
            DoubleReverseArmMotor.setPower(0.0);
            return;
        }
        DoubleReverseArmMotor.setTargetPosition(targetMotorPosition);
        DoubleReverseArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (DoubleReverseArmMotor.isBusy()) {
            telemetry.addData("currentPosition", DoubleReverseArmMotor.getCurrentPosition());
        }
        DoubleReverseArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void expandBy(int i) {
    }
}

