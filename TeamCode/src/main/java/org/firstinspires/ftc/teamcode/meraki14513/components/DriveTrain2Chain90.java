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
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status: ", "Initialized");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "LOOP : " + gamepad1.left_stick_y);

        int scale = 300;

        if (gamepad1.left_stick_y != 0.0) {
            moveByTime(100, -gamepad1.left_stick_y, gamepad1.left_stick_y);
        } else if (gamepad1.left_trigger > 0.0){
            moveByTime(100, 0.0, -gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0.0){
            moveByTime(100, gamepad1.right_trigger, 0.0);
        } else if (gamepad1.dpad_up) {
            moveByIncrement(scale, -scale, 1.0);
        } else if (gamepad1.dpad_down) {
            moveByIncrement(-scale, scale, 1.0);
        } else if (gamepad1.right_bumper) {
            moveByIncrement(2*scale, 0, 1.0);
        } else if (gamepad1.left_bumper) {
            moveByIncrement(0, -2*scale, 1.0);
        } else {
            leftDrive.setPower(0.0);
            rightDrive.setPower(0.0);
        }
    }

    public void moveByIncrement(int leftIncrement, int rightIncrement, double power) {
        telemetry.addData("leftIncrement", leftIncrement);
        telemetry.addData("rightIncrement", rightIncrement);
        telemetry.addData("power", power);

        double leftPower = Math.abs(power);
        double rightPower = Math.abs(power);
        if (leftIncrement < 0) {
            leftPower = -leftPower;
        } else if (leftIncrement == 0) {
            leftPower = 0.0;
        }
        if (rightIncrement < 0 ){
            rightPower = -rightPower;
        } else if(rightIncrement == 0) {
            rightPower = 0.0;
        }

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() + leftIncrement);
        rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + rightIncrement);

        telemetry.addData("LeftPosition", leftDrive.getCurrentPosition());
        telemetry.addData("RightPosition", rightDrive.getCurrentPosition());

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        while (leftDrive.isBusy() || rightDrive.isBusy()) {
        }
    }

    public void moveByTime(long milliseconds, double leftPower, double rightPower) {
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        ElapsedTime millitime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        while (millitime.milliseconds() < milliseconds) {
            telemetry.addData("millitime", millitime.milliseconds());
        }
    }
}