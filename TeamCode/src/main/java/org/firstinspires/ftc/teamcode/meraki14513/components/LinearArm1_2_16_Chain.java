package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="LinearArm1_2_16_Chain", group="Meraki 14513")
public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final long MINIMUM=3001;
    public static final long MAXIMUM=8001;

    private DcMotor LinearChainMotor;
    private long currentPosition;

    @Override
    public void init() {
        LinearChainMotor = hardwareMap.get(DcMotor.class, "LinearArm1_Motor");
        // LinearChainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LinearChainMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        expand((int)gamepad2.right_stick_y * 10);
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expand(long increment) {
        currentPosition = currentPosition + increment;
        if(currentPosition < MINIMUM) {
            currentPosition = MINIMUM;
        } else if(currentPosition > MAXIMUM) {
            currentPosition = MAXIMUM;
        }

        if (increment > 0) {
            LinearChainMotor.setPower(0.5);
        } else if (increment < 0) {
            LinearChainMotor.setPower(-0.5);
        } else {
            LinearChainMotor.setPower(0.0);
        }
        try{
            Thread.sleep(Math.abs(increment));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LinearChainMotor.setPower(0.0);
        }
    }
}
