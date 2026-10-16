package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

import com.ctre.phoenix6.StatusSignal;

import badgerutils.advantagekit.talonfx.LoggedTalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;

public interface ElevatorIO {
  @AutoLog
  public static class ElevatorIOInputs{
    public LoggedTalonFX rightMotor;
    public LoggedTalonFX leftMotor;
  }

  public default void updateInputs(ElevatorIOInputs inputs) {}

  public default void moveToPosition(Angle postion) {}

  public default void limitSwitchHit(){}

  public default void setMotorPostion(Double inches){}

  public default void homeElevator(){}

  public default void setDutyCycle(double speed){}

  public default void neutralOut(){}

  public default StatusSignal<Current> getMotorCurrent(){
      return null;}

  public default void zeroMotors(){}

}
