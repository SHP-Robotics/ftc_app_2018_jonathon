package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//created by jonathon for 13981

public class Base extends OpMode {
    
    public DcMotor leftBack, rightBack, leftFront, rightFront, intakeMove, climber;
    public Servo servoTest;
    public ElapsedTime timer = new ElapsedTime();

    double left = 0;
    double right = 0;

    double slowPower = 0.4;
    double normalPower = 0.7;
    double fastPower = 1.0;

    public double slowSpeed = 0.4;
    public double normalSpeed = 0.8;
    public double fastSpeed = 1.0;

    public double drop_position = 1.0;
    public double up_position = 0.3;

    @Override

    public void init() {
        timer.reset();

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        intakeMove = hardwareMap.get(DcMotor.class, "intakeMove");

        climber = hardwareMap.get(DcMotor.class, "climber");

        servoTest = hardwareMap.get(Servo.class, "servoTest");
    }

    @Override
    public void start(){
        timer.reset();
        reset_climb_encoders();
        reset_drive_encoders();
    }

    @Override
    public void loop() {

    }

    //reset encoders

    public void reset_drive_encoders() {
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void reset_climb_encoders(){
        climber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void reset_intake_encoders(){
        intakeMove.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //drive in autonomous

    /**
     * @param power: power at which the motors run
     * @param inches: how far the robot will travel
     * @return if distance is reached
     */

    public boolean auto_drive(double power, double inches){
        double target_enc = ConstantVariables.K_PPIN_DRIVE * inches;
        telemetry.addData("Target: ", target_enc);
        double left_speed = power;
        double right_speed = -power;

        left_speed = Range.clip(left_speed, -1, 1);
        right_speed = Range.clip(right_speed, -1, 1);

        leftBack.setPower(left_speed);
        leftFront.setPower(left_speed);
        rightBack.setPower(right_speed);
        rightFront.setPower(right_speed);

        if(Math.abs(get_leftBack_motor_enc()) >= target_enc && Math.abs(get_rightBack_motor_enc()) >= target_enc
                && Math.abs(get_leftFront_motor_enc()) >= target_enc && Math.abs(get_rightFront_motor_enc()) >= target_enc){
            leftBack.setPower(0);
            leftFront.setPower(0);
            rightBack.setPower(0);
            rightFront.setPower(0);
            return true;

        }
        return false;

    }

    /**
     *
     * @param power
     * @param degrees
     * @return
     */

    //turn in autonomous

    public boolean auto_turn(double power, double degrees){
        double target_enc = Math.abs(ConstantVariables.K_PPDEG_DRIVE * degrees);
        telemetry.addData("Turning to: ", target_enc);

        if(Math.abs(get_leftBack_motor_enc()) >= target_enc && Math.abs(get_rightBack_motor_enc()) >= target_enc
         && Math.abs(get_leftFront_motor_enc()) >= target_enc && Math.abs(get_rightFront_motor_enc()) >= target_enc){
            leftBack.setPower(0);
            leftFront.setPower(0);
            rightBack.setPower(0);
            rightFront.setPower(0);
            return true;
        }

        else{
            leftBack.setPower(-power);
            leftFront.setPower(-power);
            rightBack.setPower(-power);
            rightFront.setPower(-power);
        }
        return false;
    }

    //get leftBack encoders

    public int get_leftBack_motor_enc(){
        if(leftBack.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return leftBack.getCurrentPosition();
    }

    //get leftFront encoders

    public int get_leftFront_motor_enc(){
        if(leftFront.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return leftFront.getCurrentPosition();
    }

    //get rightBack encoders

    public int get_rightBack_motor_enc(){
        if(rightBack.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return rightBack.getCurrentPosition();
    }

    //get rightFront encoders

    public int get_rightFront_motor_enc(){
        if(rightFront.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return rightFront.getCurrentPosition();
    }

    public int get_climb_enc(){
        if(climber.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){
            climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return climber.getCurrentPosition();
    }

    //reference methods

    public void driveForward(double leftPower, double rightPower) {
        leftBack.setPower(-leftPower);
        rightBack.setPower(rightPower);
        leftFront.setPower(-leftPower);
        rightFront.setPower(rightPower);

        Range.clip(leftPower, -1, 1);
        Range.clip(rightPower, -1, 1);
    }

    public void turnRight() {
        leftBack.setPower(-0.2); //0.3
        rightBack.setPower(-0.2); //0.3
        leftFront.setPower(-0.2);
        rightFront.setPower(-0.2);
    }

    public void turnLeft() {
        leftBack.setPower(0.2);
        rightBack.setPower(0.2);
        leftFront.setPower(0.2);
        rightFront.setPower(0.2);
    }

    public void goStraight() {
        leftBack.setPower(-1);
        rightBack.setPower(1);
        leftFront.setPower(-1);
        rightFront.setPower(1);
    }

    public void goBack(){
        leftBack.setPower(1);
        rightBack.setPower(-1);
        leftFront.setPower(1);
        rightFront.setPower(-1);
    }

    public void turn_degrees(){
        leftBack.setPower(0.5);
        rightBack.setPower(0.5);
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
    }

    //end

    public void moveClimber(double power){
        climber.setPower(power);
    }

    public void stopClimber(){
        climber.setPower(0);
    }

    public void stop_intake_move(){
        intakeMove.setPower(0);
    }

    public int seconds(int seconds) {
        return seconds * 1000;
    }
}
