// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIO;
import frc.robot.subsystems.elevator.ElevatorIOReal;
import frc.robot.subsystems.elevator.elevatorPostion;

import static edu.wpi.first.units.Units.Inches;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final Elevator elevator;

  private final CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer(){

    switch (Constants.currentMode) {
      case REAL:
        elevator = new Elevator(new ElevatorIOReal());
        break;
    
      case SIM:
        elevator = new Elevator(new ElevatorIOReal());
        break;
      default:
      elevator = new Elevator(new ElevatorIO() {});
      break;
    }

    configureBindings();
  }


  private void configureBindings(){
    controller.a().onTrue(elevator.moveToPositionCommand(elevatorPostion.down.getHeight().in(Inches)));
    controller.b().onTrue(elevator.moveToPositionCommand(elevatorPostion.up.getHeight().in(Inches)));
    controller.y().onTrue(elevator.moveToPositionCommand(elevatorPostion.level2.getHeight().in(Inches)));
    controller.x().onTrue(elevator.moveToPositionCommand(elevatorPostion.level3.getHeight().in(Inches)));
  }

  public Command getAutonomousCommand(){
    return Commands.none();
  }
}
