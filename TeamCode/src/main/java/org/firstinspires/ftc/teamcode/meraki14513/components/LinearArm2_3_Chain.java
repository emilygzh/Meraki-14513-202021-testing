package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public final class LinearArm2_3_Chain extends BaseComponent {
    public static final long MINIMUM = 300l;
    public static final long MAXIMUM = 800l;

    private DcMotor motorOne;
    private long currentPosition;

    @Override
    public void init() {
        motorOne = hardwareMap.get(DcMotor.class, "left_drive");
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        int motorOne = Range.clip(-1.0, 1.0);
        motorOne.setPower(motorOnePower);
    }

    public void expand(long increment) {
        currentPosition = currentPosition + increment;
        if(currentPosition < MINIMUM) {
            currentPosition = MINIMUM;
        } else if(currentPosition > MAXIMUM) {
            currentPosition = MAXIMUM;
        }
        motorOne.setPower(0.5);
        try {
            Thread.sleep(increment);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorOne.setPower(0.0);

    }
}
