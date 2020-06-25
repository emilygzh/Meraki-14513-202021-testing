package org.firstinspires.ftc.teamcode.meraki14513.components;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

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
     * Constructor for FTC container
     */
    public DriveTrain4Mecanum75() { }

    /**
     * Constructor for parent host
     * @param parentOpMode
     */
    public DriveTrain4Mecanum75(OpMode parentOpMode) {
        super.initByParent(parentOpMode);
        init();
    }

    /**
     * Setup hardware mappings
     */
    @Override
    public void init() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "Mecanum75.leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "Mecanum75.rightFrontDrive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "Mecanum75.leftRearDrive");
        rightRearDrive = hardwareMap.get(DcMotor.class, "Mecanum75.rightRearDrive");

        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftRearDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Capture gamepad inputs
     */
    @Override
    public void loop() {
        int scale = 300;
        if (gamepad1.left_stick_y != 0.0 || gamepad1.left_stick_x != 0.0) {
            moveByTime(100,
                    getPower(gamepad1.left_stick_x, gamepad1.left_stick_y),
                    getPower(gamepad1.left_stick_x, gamepad1.left_stick_y),
                    getPower(gamepad1.left_stick_x, gamepad1.left_stick_y),
                    getPower(gamepad1.left_stick_x, gamepad1.left_stick_y));
        } else if (gamepad1.left_trigger > 0.0) {
            moveByTime(100, -gamepad1.left_trigger, gamepad1.left_trigger, -gamepad1.left_trigger, gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0.0) {
            moveByTime(100, gamepad1.right_trigger, -gamepad1.right_trigger, gamepad1.right_trigger, -gamepad1.right_trigger);
        }
    }

    private double getPower(double x, double y) {
        double power = Math.sqrt(x*x + y*y);
        if ((x < 0.0 && y > 0.0) || (x > 0.0 && y < 0.0)) {
            return -power;
        }
        else {
            return power;
        }
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

    public void moveByTime(long milliseconds,
                           double leftFrontPower, double rightFrontPower, double leftRearPower, double rightRearPower) {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftRearDrive.setPower(leftRearPower);
        rightRearDrive.setPower(rightRearPower);
        ElapsedTime millitime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        while (millitime.milliseconds() < milliseconds) {
            telemetry.addData("millitime", millitime.milliseconds());
        }
    }
}
