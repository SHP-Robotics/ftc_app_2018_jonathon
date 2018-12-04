package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Creative extends AlyssaBase{

    double left;
    double right;

    @Override
    public void init(){
        super.init();
    }
    @Override
    public void start(){

    }
    @Override
    public void loop(){

        left = this.gamepad1.left_stick_y;
        right = this.gamepad1.right_stick_y;

        alyssaMotor.setPower(left);
        jonathonMotor.setPower(right);

        if(gamepad1.a){
            jonathonMotor.setPower(-1);
        }

    }
    @Override
    public void stop(){

    }
}
