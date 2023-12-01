package org.firstinspires.ftc.teamcode.modules.location;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

public class BluePropLocator extends TeamPropLocator {

    /**
     * Name of the model used, stored in FtcRobotController/src/main/assets
     */
    private static final String TFOD_MODEL_ASSET = "BlueProp.tflite";

    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "BlueProp",
    };

    /**
     * Initializes the module and registers it with the specified OpMode
     *
     * @param registrar The OpMode initializing the module
     */
    public BluePropLocator(@NonNull OpMode registrar) throws InterruptedException {
        super(registrar);
    }

    public Recognition getStrongestRecognition() throws LocatorException{
        float highestConfidence = 0f;
        Recognition currentStrongest = null;
        for(Recognition recognition : currentRecognitions) {
            if(recognition.getLabel().equals("BlueProp")) {
                float confidence = recognition.getConfidence();
                if (confidence > highestConfidence) {
                    currentStrongest = recognition;
                }
            }
        }

        if(currentStrongest != null) {
            return currentStrongest;
        } else {
            throw new LocatorException(this, "No objects have been detected.");
        }
    }
}
