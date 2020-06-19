package org.firstinspires.ftc.teamcode.meraki14513.components;

        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.CRServo;
        import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Catapult", group="Meraki 14513")
public class LinearArm2_3_16_Chain_Servo extends BaseComponent{
    private CRServo leftServo;
    private CRServo rightServo;

    @Override
    public void init() {
        leftServo = hardwareMap.get(CRServo.class, "LeftArmServo");
        leftServo.setDirection(CRServo.Direction.FORWARD);
        leftServo.setPower(0.0);
        rightServo = hardwareMap.get(CRServo.class, "RightArmServo");
        rightServo.setDirection(CRServo.Direction.FORWARD);
        rightServo.setPower(0.0);
        // reverse?
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // change these controls to proper ones to avoid simultaneous moving of arms
        if(gamepad2.right_stick_x != 0.0) {
            int milliseconds = (int) Math.abs(gamepad2.left_stick_button * 100);
            double power = 1.0;
            if(gamepad2.right_stick_x < 0.0) {
                power = -1.0;
            }
            expandBy(power, milliseconds);
        }
        else if(gamepad2.b) {
            expandBy(1.0, 2000);
            // maximum positive power and time
        }
        else if(gamepad2.x) {
            expandBy(-1.0, 2000);
            // maximum negative power and time
        }
        else {
            stop();
        }
        telemetry.addData("gamepad2.right_stick_y", gamepad2.right_stick_y);
    }

    public void expandBy(double power, int milliseconds) {
        double target = power*milliseconds;
        if(target > MAXIMUM) {
            target = MAXIMUM;
        }
        else if(target < MINIMUM) {
            target = MINIMUM;
        }
        milliseconds = (int)(target/power);
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        leftServo.setPower(power);
        while(timer.milliseconds() < milliseconds) {
            telemetry.addData("timer", timer.milliseconds());
        }
        counter += power*milliseconds;
    }

    public void stop() {
        oneServo.setPower(0.0);
    }
}