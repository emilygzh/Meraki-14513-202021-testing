package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="LinearArm1_2_16_Chain", group="Meraki 14513")
public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor linearChainMotor;
    private int currentPosition;

    @Override
    public void init() {
        linearChainMotor = hardwareMap.get(DcMotor.class, "LinearArm1_Motor");
        linearChainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        currentPosition = linearChainMotor.getCurrentPosition();
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        expand((int)gamepad2.right_stick_y * 30);
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expand(int increment) {
        if(increment < 1 && increment > -1) {
            linearChainMotor.setPower(0.0);
        } else {
            if (increment > 0) {
                linearChainMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            } else {
                linearChainMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            }
            currentPosition = currentPosition + increment;
            telemetry.addData("position: ", currentPosition);
            if (currentPosition < MINIMUM) {
                currentPosition = MINIMUM;
            } else if (currentPosition > MAXIMUM) {
                currentPosition = MAXIMUM;
            }

            linearChainMotor.setTargetPosition(currentPosition);
            linearChainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linearChainMotor.setPower(1.0);
            while (linearChainMotor.isBusy()) {
                telemetry.addData("currentposition", linearChainMotor.getCurrentPosition());
            }
            linearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
