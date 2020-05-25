package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="LinearArm1_2_16_Chain", group="Meraki 14513")
public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 1200;

    private DcMotor LinearChainMotor;
    private int currentPosition;

    @Override
    public void init() {
        LinearChainMotor = hardwareMap.get(DcMotor.class, "LinearArm1_Motor");
        LinearChainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LinearChainMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        currentPosition = LinearChainMotor.getCurrentPosition();
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        expand((int)gamepad2.right_stick_y * 30);
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expand(int increment) {
        currentPosition = currentPosition + increment;
        telemetry.addData("position: ", currentPosition);
        if(currentPosition < MINIMUM) {
            currentPosition = MINIMUM;
        } else if(currentPosition > MAXIMUM) {
            currentPosition = MAXIMUM;
        }

        LinearChainMotor.setTargetPosition(currentPosition);
        LinearChainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LinearChainMotor.setPower(1.0);
        while (LinearChainMotor.isBusy()) {
            telemetry.addData("currentposition", LinearChainMotor.getCurrentPosition());
        }
        LinearChainMotor.setPower(0.0);
        LinearChainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        if (increment > 0) {
//            LinearChainMotor.setPower(0.5);
//        } else if (increment < 0) {
//            LinearChainMotor.setPower(-0.5);
//        } else {
//            LinearChainMotor.setPower(0.0);
//        }
//        try{
//            Thread.sleep(Math.abs(increment));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            LinearChainMotor.setPower(0.0);
//        }
    }
}
