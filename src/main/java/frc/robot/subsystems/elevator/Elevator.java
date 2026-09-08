package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Rotations;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorIO.ElevatorIOInputs;

public class Elevator extends SubsystemBase {
    private ElevatorIOInputs elevatorIOInputs;
    private ElevatorIO elevatorIO;

    private void moveToPostion(double inches){
        double Position = inches * ElevatorConstants.inchesPerRotation;
        elevatorIO.moveToPosition(Rotations.of(Position));
    }

    public Command moveToPositionCommand(double inches) {
        return new InstantCommand(() -> moveToPostion(inches));
    }
}
