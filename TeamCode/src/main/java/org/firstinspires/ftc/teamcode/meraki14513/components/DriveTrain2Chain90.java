package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Completed driver code for chain drive train
 */

@TeleOp(name="DriveTrain2Chain90", group="Meraki 14513")
public class DriveTrain2Chain90 extends BaseComponent {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "Chain90_LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "Chain90_RightDrive");
        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status: ", "Initialized");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "LOOP : " + gamepad1.left_stick_y);

        if (gamepad1.left_stick_y != 0.0) {
            moveByTime(100, gamepad1.left_stick_y);
        } else if (gamepad1.left_trigger > 0.0){
            int leftIncrement = (int) (gamepad1.left_trigger * 10);
            moveByIncrement(leftIncrement, -leftIncrement, gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0.0){
            int rightIncrement = (int) (gamepad1.right_trigger * 10);
            moveByIncrement(-rightIncrement, rightIncrement, gamepad1.right_trigger);
        } else if (gamepad1.dpad_up) {
            moveByIncrement(10, 10, 0.5);
        } else if (gamepad1.dpad_down) {
            moveByIncrement(-10, -10, -0.5);
        } else if (gamepad1.left_bumper) {
            moveByIncrement(10, -10, 0.5);
        } else if (gamepad1.right_bumper) {
            moveByIncrement(-10, 10, 0.5);
        } else {
            leftDrive.setPower(0.0);
            rightDrive.setPower(0.0);
        }
    }



    public void moveByIncrement(int leftIncrement, int rightIncrement, double power) {
        double leftPower = Math.abs(power);
        double rightPower = Math.abs(power);
        if (leftIncrement < 0) {
            leftPower = -leftPower;
        }
        if (rightIncrement < 0 ){
            rightPower = -rightPower;
        }

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() + leftIncrement);
        rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + rightIncrement);

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDrive.isBusy() || rightDrive.isBusy()) {
            telemetry.addData("LeftPosition", leftDrive.getCurrentPosition());
            telemetry.addData("RightPosition", rightDrive.getCurrentPosition());
        }
    }

    public void moveByTime(long milliseconds, double power) {
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        ElapsedTime millitime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        while (millitime.milliseconds() < milliseconds) {
            telemetry.addData("millitime", millitime.milliseconds());
        }
    }
}