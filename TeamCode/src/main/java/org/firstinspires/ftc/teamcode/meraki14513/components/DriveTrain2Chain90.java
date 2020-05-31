package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Completed driver code for chain drive train
 */

@TeleOp(name="DriveTrain2Chain90", group="Meraki 14513")
public class DriveTrain2Chain90 extends BaseComponent{
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "Chain90_LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "Chain90_RightDrive");
        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status: ", "Initialized");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "LOOP : " + gamepad1.left_stick_y);

        move((int) (gamepad1.left_stick_y * 10));
        turn((int) (gamepad1.left_trigger * 10));
        turn((int) (gamepad1.right_trigger * -10));
    }

    public void move(int forwardBackwardIncrement) {
        telemetry.addData("Status", "MOVE : " + forwardBackwardIncrement);

        leftDrive.setPower(((float)forwardBackwardIncrement)/10.0);
        rightDrive.setPower(((float)forwardBackwardIncrement)/10.0);
//        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() + forwardBackwardIncrement);
//
//        int rightCurrent = rightDrive.getCurrentPosition();
//        int rightTarget = rightCurrent + forwardBackwardIncrement;
//        rightDrive.setTargetPosition(rightTarget);
}

    public void turn(int LeftRightIncrement) {
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() - LeftRightIncrement);
        rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + LeftRightIncrement);
    }


}
