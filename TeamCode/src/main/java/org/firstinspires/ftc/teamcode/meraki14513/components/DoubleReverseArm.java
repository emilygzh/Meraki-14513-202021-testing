package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="DoubleReverseArm", group="Meraki 14513")
public final class DoubleReverseArm extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    //or servo

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "DRArm_LeftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "DRArm_RightMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

    private void expandTo(int targetPosition) {
        if (targetPosition < MINIMUM) {
            targetPosition = MINIMUM;
        } else if (targetPosition > MAXIMUM) {
            targetPosition = MAXIMUM;
        }
        int leftIncrement = targetPosition - leftMotor.getCurrentPosition();
        int rightIncrement = -leftIncrement;
        int leftTarget = leftMotor.getCurrentPosition() + leftIncrement;
        int rightTarget = rightMotor.getCurrentPosition() + rightIncrement;
        leftMotor.setTargetPosition(leftTarget);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setTargetPosition(rightTarget);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(leftIncrement > 0) {
            leftMotor.setPower(1.0);
            rightMotor.setPower(-1.0);
        }
        else if (leftIncrement < 0) {
            leftMotor.setPower(-1.0);
            rightMotor.setPower(1.0);
        }
        else {
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            return;
        }

        while (leftMotor.isBusy() || rightMotor.isBusy()) {

        }
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void expandBy(int i) {
        expandTo(leftMotor.getTargetPosition()+i);
    }
}

