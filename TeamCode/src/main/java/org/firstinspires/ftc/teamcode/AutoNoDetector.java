package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutoNoDetector extends Base {
    private int stage = 0;

    @Override
    public void init(){
        super.init();
    }

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void loop(){
        super.loop();

        switch(stage){

            case 0:
                if(Math.abs(get_climb_enc())>4000){
                    moveClimber(0);
                    stage++;
                }
                else{
                    moveClimber(1);
                }

                break;

            case 1:
                if(auto_drive(0.8, 5)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            default:

                break;

        }

    }

}
