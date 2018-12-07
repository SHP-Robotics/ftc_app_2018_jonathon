//pre-test Auto Crater

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous
public class ReferenceCrater extends Base {
    private int stage = 0;
    private GoldAlignDetector detector;

    private int direction = 0;

    @Override
    public void init(){
        super.init();

        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        //set_marker_servo(ConstantVariables.K_MARKER_SERVO_UP);

        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 50; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        marker_servo.setPosition(up_position);

        //detector.enable();
    }

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void loop(){
        super.loop();

        telemetry.addData("IsAligned", detector.getAligned());
        telemetry.addData("XPos", detector.getXPosition());

        switch(stage){

            case 0:

                if(Math.abs(get_climb_enc())> 4250){
                    detector.enable();
                    climb(0);
                    timer.reset();
                    stage += 2;
                }else{
                    climb(1);
                }

                break;

            case 1:

                if(timer.seconds() < 3){
                    stop_all();
                }
                else{
                    stage++;
                }

                break;

            case 2:

                if(detector.getAligned()){
                    stage +=3;
                }
                else{
                    stage++;
                }

                break;

                //turning

            case 3:

                if(detector.getXPosition() > 280){

                    direction = 1;

                    if(detector.getAligned()){
                        stage+=2;
                    }
                    else if(auto_turn(0.4, 5)){ //right
                        reset_drive_encoders();
                    }
                }
                else{
                    stage++;
                }

                break;

            case 4:

                if(detector.getXPosition() < 280) {

                    direction = 2;

                    if(detector.getAligned()){
                        stage++;
                    }
                    else if (auto_turn(-0.4, -5)) { //left
                        reset_drive_encoders();
                    }
                }
                else{
                    stage++;
                }

                break;

                //drive sequence setup

            case 5:

                if(auto_drive(0.7, 23)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

                //check direction

            case 6:

                if(direction == 0){
                    stage++;
                }
                else if(direction == 1){
                    stage +=5;
                }
                else if(direction == 2){
                    stage += 8;
                }
                else{
                    stage++;
                }

                break;

                //default

            case 7:

                if(auto_drive(-0.4, 5)){ //back
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 8:

                if(auto_turn(-0.4, 90)){ //left
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 9:

                if(auto_drive(0.8, 48)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 10:

                if(auto_turn(-0.4, 45)){ //left
                    reset_drive_encoders();
                    stage+=7;//to end
                }

                break;

            //right - 95 - 35d - 80

            case 11:

                if(auto_turn(-0.4, 95)){ //left
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 12:

                if(auto_drive(0.8, 35)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 13:

                if(auto_turn(-0.4, 80)){ //left
                    reset_drive_encoders();
                    stage+=4; //to end
                }

                break;

            //left - 20d - 20 - 90d

            case 14:

                if(auto_turn(-0.4, 20)){ //left
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 15:

                if(auto_drive(0.8, 20)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 16:

                if(auto_turn(-0.4, 90)){ //left
                    reset_drive_encoders();
                    stage++;
                }

                break;

            //end

            case 17:

                if(auto_drive(0.6, 50)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 18:

                if(auto_turn(0.4, 90)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 19:

                marker_servo.setPosition(drop_position);
                stage++;
                break;

            default:

                break;

        }

    }

    @Override
    public void stop(){
        detector.disable();
    }

}
