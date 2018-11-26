package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//created by jonathon for 13981

@TeleOp
public class BaseTeleOp extends Base {

    public static int pressed = 0;

    @Override
    public void init(){

        super.init();

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void start(){

        super.start();
    }

    @Override
    public void loop(){
        super.loop();

        /*climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        climber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);*/

        left = this.gamepad1.left_stick_y;
        right = -this.gamepad1.right_stick_y;

        leftBack.setPower(left);
        rightBack.setPower(right);
        leftFront.setPower(left);
        rightFront.setPower(right);

        if (gamepad1.left_stick_y > 0 && gamepad1.left_stick_y < 0.5) {

            leftBack.setPower(slowPower);
            leftFront.setPower(slowPower);

        }
        else if (gamepad1.left_stick_y > 0.5 && gamepad1.left_stick_y < 0.9) {

            leftBack.setPower(normalPower);
            leftFront.setPower(normalPower);

        }
        else if (gamepad1.left_stick_y > 0.9) {

            leftBack.setPower(normalPower);
            leftFront.setPower(normalPower);

        }
        else if (gamepad1.right_stick_y > 0 && gamepad1.right_stick_y < 0.5) {

            rightBack.setPower(-slowPower);
            rightFront.setPower(-slowPower);

        }
        else if (gamepad1.right_stick_y > 0.5 && gamepad1.right_stick_y < 0.9) {

            rightBack.setPower(-normalPower);
            rightFront.setPower(-normalPower);

        }
        else if (gamepad1.right_stick_y > 0.9) {

            rightBack.setPower(-fastPower);
            rightFront.setPower(-fastPower);

        }

        if (gamepad1.right_trigger > 0.7 && gamepad1.right_trigger < 0.9) {

            intakeMove.setPower(0.8);

        }
        else if (gamepad1.left_trigger > 0.7 && gamepad1.left_trigger < 0.9) {

            intakeMove.setPower(-0.8);

        }
        else if(gamepad1.right_trigger > 0.3 && gamepad1.right_trigger < 0.7){

            intakeMove.setPower(0.4);

        }
        else if(gamepad1.left_trigger > 0.3 && gamepad1.left_trigger < 0.7){

            intakeMove.setPower(-0.4);

        }
        else if(gamepad1.right_trigger > 0.1 && gamepad1.right_trigger < 0.3){

            intakeMove.setPower(0.2);

        }
        else if(gamepad1.left_trigger > 0.1 && gamepad1.left_trigger < 0.3){

            intakeMove.setPower(-0.2);

        }
        else {
            stop_intake_move();
        }
/*
        if (gamepad1.dpad_up && !gamepad1.dpad_down && top == false && bottom == true && max == false) {
            moveClimber(-4800);
            top = true;
            bottom = true;
            max = false;
        } else if (gamepad1.dpad_down && !gamepad1.dpad_up && top == true && bottom == true && max == false) {
            moveClimber(4800);
            top = false;
            bottom = true;
            max = false;
        } else if (gamepad1.dpad_right && top == true && max == false) {
            moveClimber(-1600);
            top = false;
            max = true;
        } else if (gamepad1.dpad_left && top == false && max == true) {
            moveClimber(1600);
            top = true;
            max = false;
        } else {
            climbStop();
        }

*/
        /*if(gamepad1.dpad_up){

            moveClimber(1);

        }
        else if(gamepad1.dpad_down){

            moveClimber(-1);

        }
        else{

            moveClimber(0);

        }*/

        /*if (gamepad1.right_bumper) {
            servoTest.setPosition(1.0);
        } else if (gamepad1.left_bumper) {
            servoTest.setPosition(0.3);
        }*/

        if(gamepad1.left_bumper){
            climber.setPower(1);
        }else if(gamepad1.right_bumper){
            climber.setPower(-1);
        }else{
            climber.setPower(0);
        }
    }
}
