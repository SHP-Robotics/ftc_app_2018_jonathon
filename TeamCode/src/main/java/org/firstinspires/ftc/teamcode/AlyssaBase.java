package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AlyssaBase extends OpMode {

    public DcMotor alyssaMotor, jonathonMotor;

    @Override
    public void init(){
        alyssaMotor = hardwareMap.get(DcMotor.class, "alyssaMotor");
        jonathonMotor = hardwareMap.get(DcMotor.class, "jonathonMotor");
    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){

    }

    @Override
    public void stop(){

    }
}

