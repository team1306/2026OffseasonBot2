// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmIO;
import frc.robot.subsystems.arm.ArmIOReal;
import frc.robot.subsystems.arm.ArmPostion;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIO;
import frc.robot.subsystems.elevator.ElevatorIOReal;
import frc.robot.subsystems.elevator.ElevatorPostion;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Radian;
import static edu.wpi.first.units.Units.Rotation;
import static edu.wpi.first.units.Units.Rotations;

import java.security.spec.PSSParameterSpec;
import java.util.function.Supplier;
import java.util.logging.Logger;

import edu.wpi.first.units.AngleUnit;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  final Elevator elevator;
  public final Arm arm;
  private int postion = 0;

  private final CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {

    switch (Constants.currentMode) {
      case REAL:
        elevator = new Elevator(new ElevatorIOReal());
        arm = new Arm(new ArmIOReal());
        break;

      case SIM:
        elevator = new Elevator(new ElevatorIOReal());
        arm = new Arm(new ArmIOReal());
        break;
      default:
        elevator = new Elevator(new ElevatorIO() {
        });
        arm = new Arm(new ArmIO() {
        });
        break;
    }

    configureBindings();
  }

  private void configureBindings() {

    controller.a().onTrue(new InstantCommand(() -> setPosition(2)));
    controller.b().onTrue(new InstantCommand(() -> setPosition(1)));
    controller.y().onTrue(new InstantCommand(() -> setPosition(4)));
    controller.x().onTrue(new InstantCommand(() -> setPosition(3)));

    controller.rightBumper().onTrue(new InstantCommand(() -> goToPostion()));
  }

  private void setPosition(int tempPostion) {
    postion = tempPostion;
  }

  private void goToPostion() {
    if (postion == 1) {
      CommandScheduler.getInstance().schedule(arm.roatateToPostionCommad(ArmPostion.down.getHeight()));
      CommandScheduler.getInstance().schedule(elevator.moveToPositionCommand(ElevatorPostion.down.getHeight().in(Inches)));
    }
    else if (postion == 2){
      CommandScheduler.getInstance().schedule(arm.roatateToPostionCommad(ArmPostion.level2.getHeight()));
      CommandScheduler.getInstance().schedule(elevator.moveToPositionCommand(ElevatorPostion.level2.getHeight().in(Inches)));
    }
    else if (postion == 3){
      CommandScheduler.getInstance().schedule(arm.roatateToPostionCommad(ArmPostion.level3.getHeight()));
      CommandScheduler.getInstance().schedule(elevator.moveToPositionCommand(ElevatorPostion.level3.getHeight().in(Inches)));
    }
    else if (postion == 4){
      CommandScheduler.getInstance().schedule(arm.roatateToPostionCommad(ArmPostion.up.getHeight()));
      CommandScheduler.getInstance().schedule(elevator.moveToPositionCommand(ElevatorPostion.up.getHeight().in(Inches)));
    }
    else{
      return;
    }
  }

  public Command getAutonomousCommand() {
    return Commands.none();
  }

}
