package org.firstinspires.ftc.teamcode.meraki14513.components;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class vuforia {
    private static final VuforiaLocalizer.CameraDirection CAMERA_DIRECTION = VuforiaLocalizer.CameraDirection.BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;
    private static final String ftc_vuforia =
    "AfQEkHn/////AAABmRPtfWyiwUFigLgJXPeDuVZF/zRsghPLfSH16GCs72pDGJdhzy2jS6a4FhBczUhanYpE1r/nqFYEpFBp0cNfUCt2edRQx0InOdGmQviwJnVRhB0Ix6FJg8aDkUDTzwyOU+QOmpRLDV4Uzdw7leXU3b9RNztpQx8cujpvq/sPh9zFsYiMHyoIAcC74RUJ3iNgJzWGYknYYU7NAoJwp6tM+wwCB8JCmD3zK0X+/SQwxRLcvW6USCb6V9Cj2uaFuLXbXCODpdtXg+dowGsK48UXWYjqcLKdWvu2VdqvPAFhR9uwjzWTkTLBj7AsTf0yN3OWlqu2JyI+xCZ7ch25FgpmDG4BTjIUtUzhLIaMwHddyLTt";

    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHead = (6)*mmPerInch;
    private static final float stoneZ = 2.00f * mmPerInch;
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;
    private static final float bridgeRotZ = 100;

}
