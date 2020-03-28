package org.firstinspires.ftc.teamcode.meraki14513.components;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Drive train with four independent wheel and 16" x 16" H layout
 */
@TeleOp(name="DriveTrain4Mecanum75", group="Meraki 14513")
public final class DriveTrain4Mecanum75 extends BaseComponent {
    private DcMotor leftFrontDrive;
    private DcMotor rightFrontDrive;
    private DcMotor leftRearDrive;
    private DcMotor rightRearDrive;

    /**
     * Setup hardware mappings
     */
    @Override
    public void init() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "Mecanum75.leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "Mecanum75.rightFrontDrive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "Mecanum75.leftRearDrive");
        rightRearDrive = hardwareMap.get(DcMotor.class, "Mecanum75.rightRearDrive");

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Capture gamepad inputs
     */
    @Override
    public void loop() {
        move((int)gamepad1.left_stick_x * 10, (int)gamepad1.left_stick_y * 10);
        turn((int)gamepad1.left_trigger * 10); // turn left
        turn((int)gamepad1.right_trigger * 10); // turn right
    }

    /**
     * Move liner by given vector
     * @param leftRightIncrement
     * @param forwardBackwardIncrement
     */
    public void move(int leftRightIncrement, int forwardBackwardIncrement) {
        leftFrontDrive.setTargetPosition(
                leftFrontDrive.getCurrentPosition() + forwardBackwardIncrement - leftRightIncrement);
        rightFrontDrive.setTargetPosition(
                rightFrontDrive.getTargetPosition() + forwardBackwardIncrement - leftRightIncrement);
        leftRearDrive.setTargetPosition(
                leftRearDrive.getCurrentPosition() + forwardBackwardIncrement + leftRightIncrement);
        rightFrontDrive.setTargetPosition(
                rightRearDrive.getCurrentPosition() + forwardBackwardIncrement + leftRightIncrement);
    }

    /**
     * Make turn
     * @param leftRightIncrement
     */
    public void turn(int leftRightIncrement) {
        leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + leftRightIncrement);
        rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() - leftRightIncrement);
        leftRearDrive.setTargetPosition(leftRearDrive.getCurrentPosition() + leftRightIncrement);
        rightRearDrive.setTargetPosition(rightRearDrive.getCurrentPosition() - leftRightIncrement);
    }
}
