package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public final class LinearArm2_3_Chain extends BaseComponent {
    public static final int MINIMUM = 30; // rounds
    public static final int MAXIMUM = 80; // rounds

    private DcMotor motorOne;
    private int currentPosition;

    @Override
    public void init() {
        motorOne = hardwareMap.get(DcMotor.class, "Arm2_3_Chain.drive");
        motorOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorOne.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        expand((int)gamepad2.right_stick_y * 10);
    }

    public void expand(int increment) {
        currentPosition += increment;
        if(currentPosition < MINIMUM) {
            currentPosition = MINIMUM;
        } else if(currentPosition > MAXIMUM) {
            currentPosition = MAXIMUM;
        }
        motorOne.setPower(0.5);
        motorOne.setTargetPosition(currentPosition);
        motorOne.setPower(0.0);
    }
}
