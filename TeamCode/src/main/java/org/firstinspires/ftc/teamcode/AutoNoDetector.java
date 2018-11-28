//use to test encoders, etc.

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/*
 * Created by chun on 8/8/18 for robotics boot camp 2018.
 */

/*
   Edited by jonathon for OpenCV Autonomous testing
 */

@Autonomous

public class AutoNoDetector extends Base {
    private int stage = 0;

    @Override
    public void init() {
        super.init();

        servoTest.setPosition(up_position);

        //detector.enable(); use when needed only
    }

    @Override
    public void start() {

        super.start();
        reset_drive_encoders();
        reset_climb_encoders();
    }

    @Override
    public void loop() {
        super.loop();

        switch (stage) {
            case 0:
                if(Math.abs(get_climb_enc())> 4000){
                    moveClimber(0);
                    stage++;
                }
                else{
                    moveClimber(1);
                }

                break;

            case 1:

                if(auto_drive(0.6, 5)){
                    reset_drive_encoders();
                    timer.reset();
                    stage++;
                }

                break;

            case 2:

                if(timer.seconds() > 2){
                    servoTest.setPosition(drop_position);
                }
                else{
                    timer.reset();
                    stage++;
                }

                break;

            default:

                break;
        }
    }
}


