package org.firstinspires.ftc.teamcode.meraki14513.components;

import com.qualcomm.robotcore.hardware.Servo;

public final class DoubleRollingHand extends BaseComponent {
    private Servo leftServo;
    private Servo rightServo;

    @Override
    public void init() {
        leftServo = hardwareMap.get(Servo.class, "DoubleRollingHand.leftServo")
        rightServo = hardwareMap.get(Servo.class, "DoubleRollingHand.rightServo")
    }

    @Override
    public void loop() {

    }

    public void open(int openPosition) {
        leftServo.setPosition(openPosition);
        rightServo.setPosition(-openPosition);

    }

}
