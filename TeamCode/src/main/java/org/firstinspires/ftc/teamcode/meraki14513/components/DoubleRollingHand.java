package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class DoubleRollingHand extends BaseComponent {
    private DcMotor motorOne;
    private DcMotor motorTwo;

    @Override
    public void init() {
        motorOne = hardwareMap.get(DcMotor.class, "left_drive");
        motorTwo = hardwareMap.get(DcMotor.class, "right_drive");
    }

    @Override
    public void loop() {

    }

    public void move(int x, int y, int z) {

    }
}


/**
 * lift
}
