package org.firstinspires.ftc.teamcode.meraki14513.components;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.basecomponent;

/**
 * Drive train with four independent wheel and 16" x 16" H layout
 */
@TeleOp(name="drivetrainmecanum", group="Meraki 14513")
public final class drivetrainmecanum extends BaseComponent {
    private DcMotor drive4_frontLeft;
    private DcMotor drive4_frontRight;
    private DcMotor drive4_rearLeft;
    private DcMotor drive4_rearRight;

    /**
     * Constructor for FTC container
     */
    public drivetrainmecanum() {}

    /**
     * Constructor for parent host
     // @param parentOpMode
     */
    public drivetrainmecanum(OpMode parentOpMode) {
        super.initByParent(parentOpMode);
        init();
    }

    /**
     * Setup hardware mappings
     */
    @Override
    public void init() {
        drive4_frontLeft = hardwareMap.get(DcMotor.class, "drive4_frontLeft");
        drive4_frontRight = hardwareMap.get(DcMotor.class, "drive4_frontRight");
        drive4_rearLeft = hardwareMap.get(DcMotor.class, "drive4_rearLeft");
        drive4_rearRight = hardwareMap.get(DcMotor.class, "drive4_rearRight");

        drive4_frontLeft.setPower(0.0);
        drive4_frontRight.setPower(0.0);
        drive4_rearLeft.setPower(0.0);
        drive4_rearRight.setPower(0.0);

        drive4_frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive4_frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive4_rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive4_rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        drive4_frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        drive4_frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        drive4_rearLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        drive4_rearRight.setDirection(DcMotorSimple.Direction.REVERSE);
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
     //* @param leftRightIncrement
     //* @param forwardBackwardIncrement
     */
    public void move(int leftRightIncrement, int forwardBackwardIncrement) {
        drive4_frontLeft.setTargetPosition(
                drive4_frontLeft.getCurrentPosition() + forwardBackwardIncrement - leftRightIncrement);
        drive4_frontRight.setTargetPosition(
                drive4_frontRight.getTargetPosition() + forwardBackwardIncrement - leftRightIncrement);
        drive4_rearLeft.setTargetPosition(
                drive4_rearLeft.getCurrentPosition() + forwardBackwardIncrement + leftRightIncrement);
        drive4_rearRight.setTargetPosition(
                drive4_rearRight.getCurrentPosition() + forwardBackwardIncrement + leftRightIncrement);
    }

    /**
     * Make turn
     //* @param leftRightIncrement
     */
    public void turn(int leftRightIncrement) {
        drive4_frontLeft.setTargetPosition(drive4_frontLeft.getCurrentPosition() + leftRightIncrement);
        drive4_frontRight.setTargetPosition(drive4_frontRight.getCurrentPosition() - leftRightIncrement);
        drive4_rearLeft.setTargetPosition(drive4_rearLeft.getCurrentPosition() + leftRightIncrement);
        drive4_rearRight.setTargetPosition(drive4_rearRight.getCurrentPosition() - leftRightIncrement);
    }

    public void moveByTime(long milliseconds, double leftFrontPower, double rightFrontPower, double leftRearPower, double rightRearPower) {
        drive4_frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive4_frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive4_rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive4_rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive4_frontLeft.setPower(leftFrontPower);
        drive4_frontRight.setPower(rightFrontPower);
        drive4_rearLeft.setPower(leftRearPower);
        drive4_rearRight.setPower(rightRearPower);
        ElapsedTime millitime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        while (millitime.milliseconds() < milliseconds) {
            telemetry.addData("millitime", millitime.milliseconds());
        }
    }
}

