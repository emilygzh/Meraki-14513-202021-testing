package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final long MINIMUM=3001;
    public static final long MAXIMUM=8001;
    DcMotor LinearChainMotor;
    private int currentPosition;

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

    public void expand(long increment){
        currentPosition=currentPosition+increment;
        if(currentPosition) < MINIMUM {
            currentPosition = MINIMUM;

        } else if(currentPosition>MAXIMUM){
            currentPosition= MAXIMUM
        }

        LinearChainMotor.setPower(0.5);
    try{
        Thread.sleep(increment);
    } catch (InterruptedException e) {
        e.printStackTrace();
        }

        LinearChainMotor.setPower(0.0);
    }
}
