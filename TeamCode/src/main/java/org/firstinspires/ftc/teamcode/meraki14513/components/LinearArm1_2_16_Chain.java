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
        int LinearChainMotorPower = Range.clip(-1.0, 1.0);;
        LinearChainMotor.setPower(LinearChainMotorPower);
    }
    public void move(int x){

    }
}
