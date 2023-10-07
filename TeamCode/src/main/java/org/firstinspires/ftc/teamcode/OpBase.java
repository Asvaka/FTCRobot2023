package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.modules.Arm;
import org.firstinspires.ftc.teamcode.modules.Grabber;
import org.firstinspires.ftc.teamcode.modules.MovementController;

import java.util.List;

public abstract class OpBase extends LinearOpMode {

    // Globally Declared Sensors

    // Module Classes
    protected MovementController mover;
    protected Arm arm;
    protected Grabber grabber;

    // Global Variables
    protected DcMotor armMotor;

    /**
     * Initializes global hardware and module classes
     * @throws InterruptedException The initialization was unable to complete
     */
    public void initHardware() throws InterruptedException {
        // Hubs
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        allHubs.forEach((hub) -> hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO));
        telemetry.addLine("Lynx modules configured");

        // Motors

        telemetry.addLine("Independent motors registered");
        
        // Init Module classes
        mover = new MovementController(this);
        grabber = new Grabber(this);
        arm = new Arm(this);
        grabber.grabberArm = arm;
        telemetry.addLine("Module classes created");

        telemetry.addLine("Successfully initialized hardware!");
        telemetry.update();
    }

    //Utility Functions

    // Allows you to connect opModes to the base class
    @Override public abstract void runOpMode() throws InterruptedException;

    // handle hardware setup under the hood
} 
