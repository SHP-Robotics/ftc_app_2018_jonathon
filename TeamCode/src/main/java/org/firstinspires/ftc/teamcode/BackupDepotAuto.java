//testing depot

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BackupDepotAuto extends Base {
    private int stage = 0;
    private GoldAlignDetector detector;

    public int direction;

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

        detector.enable();
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

                if(Math.abs(get_climb_enc()) > 4000){
                    climb(0);
                    stage++;
                }
                else{
                    climb(1);
                }

                break;

            case 1:

                if(detector.getAligned()){
                    direction = 0;
                    stage +=3;
                }
                else{
                    stage++;
                }

                break;

            case 2:

                if(detector.getXPosition() > 280){
                    if(detector.getAligned()){
                        direction = 1;
                        stage+=2;
                    }
                    else if(auto_turn(0.4, 5)){ //positive power = right
                        reset_drive_encoders();
                    }
                }
                else{
                    stage++;
                }

                break;

            case 3:

                if(detector.getXPosition() < 280){
                    if(detector.getAligned()){
                        direction = 2;
                        stage++;
                    }
                    else if(auto_turn(-0.4, 5)){ //negative power = left
                        reset_drive_encoders();
                    }
                }
                else{
                    stage++;
                }

                break;

            case 4:

                if(auto_drive(0.7, 35)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 5:

                if(direction == 0){
                    stage++;
                }
                else if(direction == 1){
                    stage +=3;
                }
                else if(direction == 2){
                    stage += 7;
                }

                break;

            case 6:

                if(auto_drive(0.4, 10)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 7:

                if(auto_turn(0.4, 90)){
                    reset_drive_encoders();
                    stage += 9;
                }

                break;

            //right - 35d - 10 - 90d - 10

            case 8:

                if(auto_turn(-0.4, 25)){ //negative power = left
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 9:

                if(auto_drive(0.8, 10)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 10:

                if(auto_turn(0.4, 90)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 11:

                if(auto_drive(-0.8, 10)){ //negative power = back
                    reset_drive_encoders();
                    stage += 5;
                }

                break;

            //left - 25d - 10 - 90d - 10

            case 12:

                if(auto_turn(0.4, 25)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 13:

                if(auto_drive(0.8, 10)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 14:

                if(auto_turn(0.4, 90)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 15:

                if(auto_drive(0.8, 10)){
                    reset_drive_encoders();
                    stage++; //to end
                }

                break;

            //end

            case 16:

                marker_servo.setPosition(drop_position);
                stage++;
                break;

            case 17:

                if(auto_drive(0.8, 10)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 18:

                if(auto_turn(0.4, 45)){
                    reset_drive_encoders();
                    stage++;
                }

                break;

            case 19:

                if(auto_drive(1, 100)){
                    reset_drive_encoders();
                    stage++;
                }

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
