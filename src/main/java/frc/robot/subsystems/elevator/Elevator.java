package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Rotations;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorIO.ElevatorIOInputs;

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

    private void moveToPostion(double inches){
        double Position = inches * ElevatorConstants.inchesPerRotation;
        elevatorIO.moveToPosition(Rotations.of(Position));
        Logger.recordOutput("Elevator/elevator postion", inches);
    }

    public Command moveToPositionCommand(double inches) {
        return new InstantCommand(() -> moveToPostion(inches));
    }

    public void elevatorHoldPostion(){
        
    }
}
