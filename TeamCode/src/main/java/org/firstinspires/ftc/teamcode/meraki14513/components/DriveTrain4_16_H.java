package org.firstinspires.ftc.teamcode.meraki14513.components;


import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Drive train with four independent wheel and 16" x 16" H layout
 */
public final class DriveTrain4_16_H extends BaseComponent {
    private DcMotor motorLeftFront;
    private DcMotor motorRightFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightRear;

    /**
     * Setup hardware mappings
     */
    @Override
    public void init() {
        motorLeftFront  = hardwareMap.get(DcMotor.class, "left_front_drive");
        motorRightFront = hardwareMap.get(DcMotor.class, "right_front_drive");
        motorLeftRear   = hardwareMap.get(DcMotor.class, "left_rear_drive");
        motorRightRear  = hardwareMap.get(DcMotor.class, "right_rear_drive");
    }

    /**
     * Capture gamepad inputs
     */
    @Override
    public void loop() {

    }

    /**
     * Move liner by given vector
     * @param x
     * @param y
     */
    public void move(int x, int y) {

    }

    /**
     * Make turn
     * @param t
     */
    public void turn(int t) {

    }
}
