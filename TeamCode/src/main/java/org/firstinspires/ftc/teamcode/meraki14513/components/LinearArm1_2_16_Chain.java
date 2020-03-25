package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class LinearArm1_2_16_Chain extends BaseComponent {
    DcMotor RightMotor;
    DcMotor LeftMotor;

    @Override
    public void init() {
        RightMotor = hardwareMap.get(DcMotor.class, "Right_drive");
        LeftMotor = hardwareMap.get(DcMotor.class, "Left_drive");
    }

    @Override
    public void loop() {

    }
    public void move(int x, int y, int z){

    }
}
