package org.firstinspires.ftc.teamcode.opmode.autonomous.red;

import org.firstinspires.ftc.teamcode.modules.detection.Prop;
import org.firstinspires.ftc.teamcode.opmode.autonomous.template.AutonomousBase;
import org.firstinspires.ftc.teamcode.opmode.autonomous.template.AutonomousConstants;

public abstract class AutonomousRedImpl extends AutonomousBase {
    public static final String RED_AUTO_GROUP_NAME = "Red";

    /**
     * Called at the beginning of execution, when the robot is facing the wall opposite the backdrop and has one side
     * touching the wall.  When implemented, strafes directly to the U of spike marks.
     */
    @Override
    protected void driveToSpikeMarks() {
        getDriverToPosition().followTrajectory(
                getDriverToPosition().trajectoryBuilder(getDriverToPosition().getPoseEstimate())
                        .strafeRight(AutonomousConstants.DISTANCE_TO_SPIKE_MARKS_IN)
                    .build()
        );
    }

    /**
     * @return The ID of the AprilTag on the left of the backdrop
     * @see #driveToSpikeMarks()
     */
    @Override
    protected int getLeftAprilTagId() {
        return 4;
    }

    /**
     * @return The ID of the AprilTag in the middle of the backdrop
     */
    @Override
    protected int getCenterAprilTagId() {
        return 5;
    }

    /**
     * @return The ID of the AprilTag on the right of the backdrop
     * @see #driveToSpikeMarks()
     */
    @Override
    protected int getRightAprilTagId() {
        return 6;
    }

    /**
     * @return A {@link Prop} object representing the team prop for our alliance
     */
    @Override
    protected Prop getTeamProp() {
        return Prop.RED_TEAM_PROP;
    }

    /**
     * Called twice, both times in the middle of the U of spike marks, facing a spike mark.  When implemented, moves
     * outside the U (through the side without a spike mark), rotates 90 degrees to the next spike mark.
     */
    @Override
    protected void rotateToNextSpikeMark() {
        getDriverToPosition().turn(Math.toRadians(-90));
    }
}
