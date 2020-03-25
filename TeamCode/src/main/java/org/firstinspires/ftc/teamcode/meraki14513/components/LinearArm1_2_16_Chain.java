package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class LinearArm1_2_16_Chain extends BaseComponent {
    DcMotor RightMotor;
    DcMotor LeftMotor;

    @Override
    public void init() {
        RightMotor = hardwareMap.get(DcMotor.class, "huhhhhh");
        LeftMotor = hardwareMap.get(DcMotor.class, "huhhhhhh");
    }

    @Override
    public void loop() {

    }
}
