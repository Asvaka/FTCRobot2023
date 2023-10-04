package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class MovementController {
    /**
     * The motor that drives the front right mecanum wheel
     */
    private final DcMotor frontRightMecanumDriver;

    /**
     * The default name of the front right mecanum driver
     */
    public static final String FRONT_RIGHT_MECANUM_DRIVER_DEFAULT_NAME = "Front Right Mecanum Driver";


    /**
     * The motor that drives the front left mecanum wheel
     */
    private final DcMotor frontLeftMecanumDriver;


    /**
     * The default name of the front left mecanum driver
     */
    public static final String FRONT_LEFT_MECANUM_DRIVER_DEFAULT_NAME = "Front Left Mecanum Driver";


    /**
     * The motor that drives the back right mecanum wheel
     */
    private final DcMotor backRightMecanumDriver;


    /**
     * The default name of the back right mecanum driver
     */
    public static final String BACK_RIGHT_MECANUM_DRIVER_DEFAULT_NAME = "Back Right Mecanum Driver";

    /**
     * The motor that drives the back left mecanum wheel
     */
    private final DcMotor backLeftMecanumDriver;


    /**
     * The default name of the back left mecanum driver
     */
    public static final String BACK_LEFT_MECANUM_DRIVER_DEFAULT_NAME = "Back Left Mecanum Driver";

    /**
     * Initializes the module with the given motors
     * @param frontRight the motor connected to the front right mecanum wheel
     * @param frontLeft the motor connected to the front left mecanum wheel
     * @param backLeft the motor connected to the back right mecanum wheel
     * @param backRight the motor connected to the back left mecanum wheel
     * @exception InterruptedException The module was unable to access and/or configure the motors
     */
    public MovementController(DcMotor frontRight, DcMotor frontLeft, DcMotor backLeft, DcMotor backRight) throws InterruptedException {
        try {
            frontRightMecanumDriver = frontRight;
            frontLeftMecanumDriver = frontLeft;
            backLeftMecanumDriver = backRight;
            backRightMecanumDriver = backLeft;
        }
        catch (IllegalArgumentException e) {
            throw new InterruptedException(e.getMessage());
        }

        // motor config
        frontRightMecanumDriver.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMecanumDriver.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMecanumDriver.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMecanumDriver.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Attempts to initialize the module by getting motors with the default names from a hardware map
     * @param registrar the OpMode that will be using the module
     * @exception InterruptedException The module was unable to locate the necessary motors
     */
    public MovementController(@NonNull OpMode registrar) throws InterruptedException {
        this(
            registrar.hardwareMap.get(DcMotor.class, FRONT_RIGHT_MECANUM_DRIVER_DEFAULT_NAME),
            registrar.hardwareMap.get(DcMotor.class, FRONT_LEFT_MECANUM_DRIVER_DEFAULT_NAME),
            registrar.hardwareMap.get(DcMotor.class, BACK_RIGHT_MECANUM_DRIVER_DEFAULT_NAME),
            registrar.hardwareMap.get(DcMotor.class, BACK_LEFT_MECANUM_DRIVER_DEFAULT_NAME)
        );
    }

    /**
     * Moves and rotates the robot
     * @param distX The right velocity
     * @param distY The forward velocity
     * @param rotation The rotation
     */
    public void moveAndRotateRobot(double distX, double distY, double rotation) {
        // Combine the requests for each axis-motion to determine each wheel's power.
        // (formula was found on gm0)
        double leftFrontPower  = distY + distX + rotation;
        double leftBackPower = distY - distX + rotation;
        double rightFrontPower   = distY - distX - rotation;
        double rightBackPower  = distY + distX - rotation;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        // Send calculated power to wheels
        frontLeftMecanumDriver.setPower(leftFrontPower);
        frontRightMecanumDriver.setPower(rightFrontPower);
        backRightMecanumDriver.setPower(rightBackPower);
        backLeftMecanumDriver.setPower(leftBackPower);
    }
}
