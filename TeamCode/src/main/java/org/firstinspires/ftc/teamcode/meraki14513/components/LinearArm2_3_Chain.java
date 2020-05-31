package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="LinearArm2_3_Chain", group="Meraki 14513")
public final class LinearArm2_3_Chain extends BaseComponent {       
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

        rightMotor = hardwareMap.get(DcMotor.class, "LinearArmLeft");
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
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expandTogether(int increment) {
        leftPosition = leftPosition + increment;
        telemetry.addData("Target position: ", leftPosition);
        if(leftPosition < MINIMUM) {
            leftPosition = MINIMUM;
        } else if(leftPosition > MAXIMUM) {
            leftPosition = MAXIMUM;
        }
        rightPosition = leftPosition;

        leftMotor.setTargetPosition(leftPosition);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setTargetPosition(rightPosition);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

        while (leftMotor.isBusy() || rightMotor.isBusy()) {
            telemetry.addData("Current position",
                    leftMotor.getCurrentPosition() + " | " + rightMotor.getCurrentPosition());
        }
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
