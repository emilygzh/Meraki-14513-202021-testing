package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class LinearArm1_2_16_Chain extends BaseComponent {
    DcMotor LinearChainMotor;

    @Override
    public void init() {
        LinearChainMotor = hardwareMap.get(DcMotor.class, "LinearChain_Motor");
    }

    @Override
    public void loop() {

    }
    public void move(int x, int y, int z){

    }
}
