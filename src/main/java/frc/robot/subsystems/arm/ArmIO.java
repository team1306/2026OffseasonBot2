package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import badgerutils.advantagekit.talonfx.LoggedTalonFX;
import edu.wpi.first.units.measure.Angle;
import frc.robot.subsystems.elevator.ElevatorIO.ElevatorIOInputs;

public interface ArmIO {
    @AutoLog
    public static class ArmIOInput{
        LoggedTalonFX motor;
    }

     public default void updateInputs(ArmIOInput inputs) {}

    public default void rotateToPostion(Angle postion) {}
}
