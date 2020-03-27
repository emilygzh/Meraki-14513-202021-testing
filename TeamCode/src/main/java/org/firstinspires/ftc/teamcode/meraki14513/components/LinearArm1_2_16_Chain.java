package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public final class LinearArm1_2_16_Chain extends BaseComponent {
    DcMotor LinearChainMotor;

    @Override
    public void init() {
        LinearChainMotor = hardwareMap.get(DcMotor.class, "LinearChain_Motor");
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        long incrementInput = (long) (gamepad2.right_stick_y * 50.0);
        expand(incrementInput);
    }

    public void expand(long increment){


    }
}
