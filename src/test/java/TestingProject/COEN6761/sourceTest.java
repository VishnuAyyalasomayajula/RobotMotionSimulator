package TestingProject.COEN6761;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SourceTest {
    private source robot;

    @BeforeEach
    void setUp() {
        // Initialize a robot with a floor size of 5x5 before each test
    	robot = new source(5);
        
    }

    @Test
    void testInitialize() {
        // Initialize a robot with a floor size of 5x5 before each test
        robot.initializeArray(10);
        robot.printStatus();
        assertEquals(0, robot.getY(), "Y position should be 0 initially");
        assertEquals(0, robot.getX(), "X position should be 0 initially");
        assertFalse(robot.isPenDown(), "By default after initialisation pen should be up");
        assertEquals("N", robot.getDirection(), "Initially robot direction should point North");

    }

    @Test
    void testPenUp() {
        robot.penUp(); // Pen is initially up
        assertFalse(robot.isPenDown(), "Pen should be up after calling penUp()");
    }

    @Test
    void testPenDown() {
        robot.penDown();
        assertTrue(robot.isPenDown(), "Pen should be down after calling penDown()");
    }

    @Test
    void testTurnRight() {
        robot.turnRight();
        assertEquals("E", robot.getDirection(), "Direction should be East after turning right from initial North");

        robot.turnRight();
        assertEquals("S", robot.getDirection(), "Direction should be South after turning right from East");

        robot.turnRight();
        assertEquals("W", robot.getDirection(), "Direction should be West after turning right from South");

        robot.turnRight();
        assertEquals("N", robot.getDirection(), "Direction should be North after turning right from West");
    }

    @Test
    void testTurnLeft() {
        robot.turnLeft();
        assertEquals("W", robot.getDirection(), "Direction should be West after turning left from initial North");

        robot.turnLeft();
        assertEquals("S", robot.getDirection(), "Direction should be South after turning left from West");

        robot.turnLeft();
        assertEquals("E", robot.getDirection(), "Direction should be East after turning left from South");

        robot.turnLeft();
        assertEquals("N", robot.getDirection(), "Direction should be North after turning left from East");
    }

    @Test
    void testMoveForwardNorth() {
        robot.penDown(); // Pen is down to mark the floor
        robot.moveForward(2); // Move 2 steps North
        assertEquals(2, robot.getY(), "Y position should be 2 after moving 2 steps North");
        assertTrue(robot.isPenDown(), "Pen should still be down after moving");
        robot.printStatus();
        robot.printFloor();
    }

    @Test
    void testMoveForwardEast() {
        robot.turnRight(); // Face East
        robot.penDown(); // Pen is down to mark the floor
        robot.moveForward(3); // Move 3 steps East
        assertEquals(3, robot.getX(), "X position should be 3 after moving 3 steps East");
        robot.printStatus();
        robot.printFloor();
    }

    @Test
    void testMoveForwardSouth() {
        robot.moveForward(2); // Move 2 steps North first
        robot.turnRight();
        robot.turnRight(); // Face South
        robot.penDown(); // Pen is down to mark the floor
        robot.moveForward(1); // Move 1 step South
        assertEquals(1, robot.getY(), "Y position should be 1 after moving 1 step South");
        robot.printStatus();
        robot.printFloor();
    }

    @Test
    void testMoveForwardWest() {
        robot.turnRight(); // Face East
        robot.moveForward(3); // Move 3 steps East first
        robot.turnRight();
        robot.turnRight(); // Face West
        robot.penDown(); // Pen is down to mark the floor
        robot.moveForward(2); // Move 2 steps West
        assertEquals(1, robot.getX(), "X position should be 1 after moving 2 steps West");
        robot.printStatus();
        robot.printFloor();
    }

    @Test
    void testMoveForwardOutOfBounds() {
        robot.moveForward(15); // Try to move beyond the floor size
        assertEquals(4, robot.getY(), "Y position should be 4 (maximum bound) after moving out of bounds");
    }

    @Test
    void testPrintFloor() {
        robot.penDown(); // Pen is down to mark the floor
        robot.moveForward(2); // Move 2 steps North
        robot.turnRight(); // Face East
        robot.moveForward(1); // Move 1 step East
        robot.printFloor(); // Print the floor 
    }

    @Test
    void testPrintStatus() {
        robot.penDown();
        robot.turnRight();
        robot.moveForward(1);
        robot.printStatus(); // Print status 
    }
    
}