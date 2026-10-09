package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Rotation;

import edu.wpi.first.units.measure.Angle;

public enum ArmPostion {
    down(Rotation.of(0)),
    up(Rotation.of(180)),
    level2(Rotation.of(120)),
    level3(Rotation.of(60));


    private Angle angle;

    private ArmPostion(Angle angle){
        this.angle = angle;
    }

    public Angle getHeight(){
        return angle;
    }
}
