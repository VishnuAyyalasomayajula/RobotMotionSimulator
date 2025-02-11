package TestingProject.COEN6761;

import java.util.Scanner;

public class source {
    private int[][] floor;
    private int x, y;
    private boolean penDown;
    private String direction;
    private String history = ""; // To store command history

    public source(int size) {
        floor = new int[size][size];
        x = 0;
        y = 0;
        penDown = false;
        direction = "N";
    }

    public void penUp() {
        penDown = false;
        history += "U "; // Add to history
    }

    public void penDown() {
        penDown = true;
        history += "D "; // Add to history
    }

    public boolean isPenDown() {
        return penDown;
    }

    public void turnRight() {
        switch (direction) {
            case "N": direction = "E"; break;
            case "E": direction = "S"; break;
            case "S": direction = "W"; break;
            case "W": direction = "N"; break;
        }
        history += "R "; // Add to history
    }

    public void turnLeft() {
        switch (direction) {
            case "N": direction = "W"; break;
            case "W": direction = "S"; break;
            case "S": direction = "E"; break;
            case "E": direction = "N"; break;
        }
        history += "L "; // Add to history
    }

    public String getDirection() {
        return direction;
    }

    public void moveForward(int steps) {
        // Mark the starting position if the pen is down
        if (penDown) {
            floor[x][y] = 1; // Mark the current position with '*'
        }

        for (int i = 0; i < steps; i++) {
            int newX = x, newY = y;
            switch (direction) {
                case "N": newY = Math.min(y + 1, floor.length - 1); break;
                case "E": newX = Math.min(x + 1, floor.length - 1); break;
                case "S": newY = Math.max(y - 1, 0); break;
                case "W": newX = Math.max(x - 1, 0); break;
            }
            x = newX;
            y = newY;
            if (penDown) {
                floor[x][y] = 1; // Mark the new position with '*'
            }
        }
        history += "M " + steps + " "; // Add to history
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void printFloor() {
        for (int i = floor.length - 1; i >= 0; i--) {
            for (int j = 0; j < floor.length; j++) {
                System.out.print(floor[j][i] == 1 ? "* " : "  ");
            }
            System.out.println();
        }
    }

    public void printStatus() {
        System.out.println("Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: " + direction);
    }

    // Redo functionality
    public String redoHistory() {
    	System.out.println(history);
    	return history;
        /*String[] commands = history.trim().split(" ");
        for (int i = 0; i < commands.length; i++) {
            switch (commands[i]) {
                case "U":
                    penUp();
                    break;
                case "D":
                    penDown();
                    break;
                case "R":
                    turnRight();
                    break;
                case "L":
                    turnLeft();
                    break;
                case "M":
                    if (i + 1 < commands.length && isStringInt(commands[i + 1])) {
                        moveForward(Integer.parseInt(commands[i + 1]));
                        i++; // Skip the steps value
                    }
                    break;
                case "I":
                    if (i + 1 < commands.length && isStringInt(commands[i + 1])) {
                        initializeArray(Integer.parseInt(commands[i + 1]));
                        i++; // Skip the size value
                    }
                    break;
                case "P":
                    printFloor();
                    break;
                case "C":
                    printStatus();
                    break;
            }
        }*/
    }

    // Initialize array with new size
    public void initializeArray(int size) {
        floor = new int[size][size];
        x = 0;
        y = 0;
        penDown = false;
        direction = "N";
        history += "I " + size + " "; // Add to history
    }

    // Check if a string is a valid integer
    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        source robot = new source(10); // Default size
        System.out.println("Robot Motion Simulator Started");

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().toLowerCase().trim();

            try {
                if (command.isEmpty()) {
                    System.out.println("Error: Command cannot be empty.");
                    continue;
                }

                if (command.startsWith("i")) {
                    // Initialize floor with a new size
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        System.out.println("Error: Missing size for initialization. Usage: i <size>");
                        continue;
                    }
                    int size = Integer.parseInt(parts[1]);
                    if (size <= 0) {
                        System.out.println("Error: Size must be a positive integer.");
                        continue;
                    }
                    robot.initializeArray(size);
                    System.out.println("Floor initialized with size " + size + "x" + size);

                } else if (command.equals("u")) {
                    // Pen up
                    robot.penUp();
                    System.out.println("Pen is up.");

                } else if (command.equals("d")) {
                    // Pen down
                    robot.penDown();
                    System.out.println("Pen is down.");

                } else if (command.equals("r")) {
                    // Turn right
                    robot.turnRight();
                    System.out.println("Turned right.");

                } else if (command.equals("l")) {
                    // Turn left
                    robot.turnLeft();
                    System.out.println("Turned left.");

                } else if (command.startsWith("m")) {
                    // Move forward
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        System.out.println("Error: Missing steps for move command. Usage: m <steps>");
                        continue;
                    }
                    int steps = Integer.parseInt(parts[1]);
                    if (steps < 0) {
                        System.out.println("Error: Steps must be a non-negative integer.");
                        continue;
                    }
                    robot.moveForward(steps);
                    System.out.println("Moved forward " + steps + " steps.");

                } else if (command.equals("p")) {
                    // Print floor
                    System.out.println("Floor:");
                    robot.printFloor();

                } else if (command.equals("c")) {
                    // Print status
                    robot.printStatus();

                } else if (command.equals("h")) {
                    // Redo history
                    robot.redoHistory();
                    System.out.println("Redid command history.");

                } else if (command.equals("q")) {
                    // Quit
                    System.out.println("Exiting program.");
                    break;

                } else {
                    System.out.println("Error: Invalid command. Valid commands are: i, u, d, r, l, m, p, c, h, q.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Please enter a valid integer.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Invalid command format. Check your input.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}