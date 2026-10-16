package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Rotations;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    public final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();
    private ElevatorIO elevatorIO;

    public Elevator(ElevatorIO elevatorIO) {
        this.elevatorIO = elevatorIO;
    }

    @Override
    public void periodic(){
        elevatorIO.updateInputs(inputs);
        Logger.processInputs("Elevator", inputs);
    }

    private void moveToPostion(Distance height){
        double Position = height.in(Inches) * ElevatorConstants.inchesPerRotation;
        elevatorIO.moveToPosition(Rotations.of(Position));
        Logger.recordOutput("Elevator/elevator postion", height);
        elevatorIO.setMotorPostion(height.in(Inches));
    }

    public Command moveToPositionCommand(Distance height) {
        return new InstantCommand(() -> moveToPostion(height));
    }

    public void elevatorLimitSwitchHit(){
        elevatorIO.limitSwitchHit();
    }

    public Command homeElevatorCommand(){
        return Commands.run(() -> elevatorIO.setDutyCycle(-0.05)).until((() -> elevatorIO.getMotorCurrent().getValue().lt(Amps.of(25)))).andThen(() -> elevatorIO.neutralOut()).andThen(() -> elevatorIO.zeroMotors());
    }
}
