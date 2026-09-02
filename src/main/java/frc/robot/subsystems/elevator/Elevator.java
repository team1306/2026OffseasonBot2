package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Rotations;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorIO.ElevatorIOInputs;

public class Elevator extends SubsystemBase {
    ElevatorIOInputs elevatorIOInputs;
    ElevatorIO elevatorIO;

    private void holdPostion(){

    }

    private void moveToPostion(double inches){
        double Position = inches * ElevatorConstants.inchesPerRotation;
        elevatorIO.lift(Rotations.of(Position));
    }

    public Command liftCommand() {
        return new InstantCommand(() -> moveToPostion(0));
    }

    public Command lowerCommand() {
        return new InstantCommand(() -> moveToPostion(0));
    }

    public Command manuelControlUp(){
        return Commands.startEnd(() -> moveToPostion(0), () -> holdPostion());
    }
}
