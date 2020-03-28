package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Completed driver code for chain drive train
 */

@TeleOp(name="DriveTrain2Chain", group="Meraki 14513")
public class DriveTrain2Chain extends BaseComponent{
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    @Override
    public void init() {
        telemetry.addData("Status: ", "Initialized");

        leftDrive = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "RightDrive");
        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

        telemetry.addData("Status", "Initialized");

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        move((int) (gamepad1.left_stick_y * 10));
        turn((int) (gamepad1.left_trigger * 10));
        turn((int) (gamepad1.right_trigger * -10));
    }

    public void move(int forwardBackwardIncrement) {
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() + forwardBackwardIncrement);

        int rightCurrent = rightDrive.getCurrentPosition();
        int rightTarget = rightCurrent + forwardBackwardIncrement;
        rightDrive.setTargetPosition(rightTarget);
}

    public void turn(int LeftRightIncrement) {
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() - LeftRightIncrement);
        rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + LeftRightIncrement);
    }


}
