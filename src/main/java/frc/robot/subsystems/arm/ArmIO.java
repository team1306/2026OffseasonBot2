package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;


import badgerutils.advantagekit.talonfx.LoggedTalonFX;
import edu.wpi.first.units.measure.Angle;

public interface ArmIO {
    @AutoLog
    public static class ArmIOInput{
        LoggedTalonFX motor;
    }

     public default void updateInputs(ArmIOInput inputs) {}

    public default void rotateToPostion(Angle postion) {}

    public default void rotate(double speed){}

    public default void holdPostion(){}
}
