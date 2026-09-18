package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Inches;

import edu.wpi.first.units.measure.Distance;

public enum ElevatorPostion {
    down(Inches.of(0)),
    up(Inches.of(10)),
    level2(Inches.of(4)),
    level3(Inches.of(8));


    private Distance height;

    private ElevatorPostion(Distance height){
        this.height = height;
    }

    public Distance getHeight(){
        return height;
    }
}
