package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

import badgerutils.advantagekit.talonfx.LoggedTalonFX;
import edu.wpi.first.units.measure.Angle;

public interface ElevatorIO {
  @AutoLog
  public static class ElevatorIOInputs{
    public LoggedTalonFX rightMotor;
    public LoggedTalonFX leftMotor;
  }

  
  public default void lift(Angle postion) {}

  public default void motorHoldPostion(){}
}
