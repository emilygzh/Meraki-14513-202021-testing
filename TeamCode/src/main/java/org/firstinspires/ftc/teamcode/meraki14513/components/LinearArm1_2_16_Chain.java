package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="LinearArm1_2_16_Chain", group="Meraki 14513")
public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor linearChainMotor;
    private int armPosition;

    @Override
    public void init() {
        linearChainMotor = hardwareMap.get(DcMotor.class, "LinearArm1_Motor");
        linearChainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armPosition = MINIMUM;
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        expand((int)-gamepad2.right_stick_y * 30);
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expand(int increment) {
        int targetArmPosition = armPosition + increment;
        telemetry.addData("armPosition: ", armPosition);
        if (targetArmPosition < MINIMUM) {
            targetArmPosition = MINIMUM;
        } else if (targetArmPosition > MAXIMUM) {
            targetArmPosition = MAXIMUM;
        }
        int realIncrement = targetArmPosition - armPosition;

        if(realIncrement < 1 && realIncrement > -1) {
            linearChainMotor.setPower(0.0);
            telemetry.addData("set power 0.0", increment);
        } else {
            if (realIncrement > 0) {
                linearChainMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            } else if (realIncrement < 0){
                linearChainMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            }
            int targetMotorPosition = linearChainMotor.getCurrentPosition() + Math.abs(realIncrement);
            linearChainMotor.setTargetPosition(targetMotorPosition);
            linearChainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linearChainMotor.setPower(1.0);
            while (linearChainMotor.isBusy()) {
                telemetry.addData("currentPosition", linearChainMotor.getCurrentPosition());
            }
            linearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            armPosition = targetArmPosition;
        }
    }
}
