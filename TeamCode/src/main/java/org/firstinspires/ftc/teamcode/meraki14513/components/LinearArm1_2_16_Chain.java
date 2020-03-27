package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public final class LinearArm1_2_16_Chain extends BaseComponent {
    public static final long MINIMUM =3001;
    public static final long MAXIMUM = 8001;

    private DcMotor LinearChainMotor;
    private long currentPosition

    @Override
    public void init() {
        LinearChainMotor = hardwareMap.get(DcMotor.class, "LinearChain_Motor");
    }

    @Override
    public void loop() {

    }
    public void expand(long increment) {
        currentPosition = currentPosition + increment;
        if(currentPosition < MINIMUM) {
            currentPosition = MINIMUM;
        } else if(currentPosition > MAXIMUM) {
            currentPosition = MAXIMUM;
        }
        LinearChainMotor.setPower(0.5);
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LinearChainMotor.setPower(0)
    }
}
