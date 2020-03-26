package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="LinearChainCoreMotor", group="Iterative Opmode")
@Disabled
public class LinearChainCoreMotor {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor LinearChain = null;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        LinearC  = hardwareMap.get(DcMotor.class, "left_drive");

}
