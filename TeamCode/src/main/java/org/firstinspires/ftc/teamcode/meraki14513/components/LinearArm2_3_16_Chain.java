package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="LinearArm2_3_Chain", group="Meraki 14513")
public final class LinearArm2_3_16_Chain extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private int leftPosition;
    private int rightPosition;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "LinearArmLeft");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setPower(0.0);
        leftPosition = leftMotor.getCurrentPosition();

        rightMotor = hardwareMap.get(DcMotor.class, "LinearArmRight");
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setPower(0.0);
        rightPosition = rightMotor.getCurrentPosition();

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        expandTogether((int)gamepad2.left_stick_y * 30);
        if(gamepad2.left_bumper) {
            expandSeparate(-5, true);
        }
        if(gamepad2.right_bumper) {
            expandSeparate(-5, false);
        }
        expandSeparate((int)gamepad2.left_trigger*10, true);
        expandSeparate((int)gamepad2.right_trigger*10, false);
        telemetry.addData("gamepad2.right_stick_y", gamepad2.left_stick_y);
    }

    public void expandToTogether(int targetPosition) {
        telemetry.addData("Target position: ", targetPosition);
        if (targetPosition < MINIMUM) {
            targetPosition = MINIMUM;
        } else if (targetPosition > MAXIMUM) {
            targetPosition = MAXIMUM;
        }
        leftMotor.setTargetPosition(targetPosition);
        rightMotor.setTargetPosition(targetPosition);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

        while (leftMotor.isBusy() || rightMotor.isBusy()) {
            telemetry.addData("Left position", leftMotor.getCurrentPosition());
            telemetry.addData("Right position", rightMotor.getCurrentPosition());
        }
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void expandByTogether(int increment) {
        expandByTogether(leftMotor.getCurrentPosition() + increment);
        expandByTogether(rightMotor.getCurrentPosition() + increment);
    }

    public void expandToSeparate(int targetPosition, boolean expandLeft) {
        DcMotor expandMotor;
        if(expandLeft) {
            expandMotor = leftMotor;
        }
        else{
            expandMotor = rightMotor;
        }
        telemetry.addData("Target position: ", targetPosition);
        if(targetPosition < MINIMUM) {
            targetPosition = MINIMUM;
        } else if(targetPosition > MAXIMUM) {
            targetPosition = MAXIMUM;
        }
        expandMotor.setTargetPosition(targetPosition);
        expandMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        expandMotor.setPower(1.0);

        while (expandMotor.isBusy()) {
            telemetry.addData("Current position", expandMotor.getCurrentPosition());
        }
        expandMotor.setPower(0.0);
        expandMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void expandBySeparate(int increment, boolean expandLeft) {
        DcMotor expandMotor;
        if(expandLeft) {
            expandMotor = leftMotor;
        }
        else{
            expandMotor = rightMotor;
        }
        expandToSeparate(expandMotor.getCurrentPosition() + increment, expandLeft);
    }
}
