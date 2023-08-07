package com.github.DaBrogrammer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import MajorProject.RobotMotion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
//import com.github.DaBrogrammer.RobotMotion.floor;

public class RobotTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    
    public void testCurrentPositionForInvalidCommand() {

    	outputStream.reset();
    	ByteArrayInputStream is = new ByteArrayInputStream("i3\nc7\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());  }

    
   
    @Test
    public void testinputElseLoops() {
    	
    	outputStream.reset();
    	ByteArrayInputStream is = new ByteArrayInputStream("i3\nd7\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());

    	outputStream.reset();
   is = new ByteArrayInputStream("i3\nu7\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());  
        outputStream.reset();
    	 is = new ByteArrayInputStream("i3\nr7\nq\n".getBytes());
        System.setIn(is);


        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());
        outputStream.reset();
    	is = new ByteArrayInputStream("i3\nl7\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());  
        outputStream.reset();
    	is = new ByteArrayInputStream("i3\nma\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid arguments for 'm' command! Please enter an integer with the command."
        		+System.lineSeparator()+ "Enter command: ", outputStream.toString());  
        outputStream.reset();
    	is = new ByteArrayInputStream("i3\np7\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString()); 
        outputStream.reset();
    	is = new ByteArrayInputStream("i3\nq7\nq\n".getBytes());
        System.setIn(is);
        
        
        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());
        
      
        is = new ByteArrayInputStream("m\nq\n".getBytes());
        System.setIn(is);
        
        
        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Invalid command!" +System.lineSeparator()+
        		"Enter command: Enter command: Invalid arguments for 'm' command! Please enter an integer with the command."+System.lineSeparator()+
        		 "Enter command: ", outputStream.toString());
        
        
    }
    
        
    
    
    
    
    @Test
    public void testFloorInitialization() {
   
        ByteArrayInputStream is1 = new ByteArrayInputStream("i7\nq\n".getBytes());
        System.setIn(is1);

        RobotMotion.run();
        int[][] expectedFloor = {{0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0},
        		};
        Assertions.assertArrayEquals(expectedFloor, RobotMotion.getFloor());
        outputStream.reset();
        
    }
    
    @Test
    public void testFloorInitializationForInvalidCommand() {
       
    	ByteArrayInputStream is = new ByteArrayInputStream("i\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();
    
        Assertions.assertEquals("Enter command: Invalid arguments for 'i' command! Please enter an integer with the command."+System.lineSeparator()+"Enter command: ",outputStream.toString());
        Assertions.assertEquals(0, RobotMotion.getPosX());
        Assertions.assertEquals(0, RobotMotion.getPosY());

        Assertions.assertFalse(RobotMotion.isPenDown());

        Assertions.assertEquals(RobotMotion.Direction.NORTH, RobotMotion.getDirection());
        outputStream.reset();
    }

    
    @Test
    public void testPenIsUp() {
      
        ByteArrayInputStream is = new ByteArrayInputStream("i10\nu\nq\n".getBytes());
        System.setIn(is);


        RobotMotion.run();

        Assertions.assertFalse(RobotMotion.isPenDown());
        outputStream.reset();
    }
    

  
    @Test
    public void testPenUpNoDraw() {
     
    	RobotMotion.initializeSystem(3);

        RobotMotion.setPen(false); 
        RobotMotion.move(2);
        RobotMotion.turnRight();
        RobotMotion.move(1);

        outputStream.reset();
        RobotMotion.printFloor();
        RobotMotion a=new RobotMotion();
        ByteArrayInputStream is = new ByteArrayInputStream("q\n".getBytes());
        
 
        String expectedOutput = "  +------+" + System.lineSeparator() +
                "2 |      |" + System.lineSeparator() +
                "1 |      |" + System.lineSeparator() +
                "0 |      |" + System.lineSeparator() +
                "  +------+" + System.lineSeparator() +
                "   0 1 2 " + System.lineSeparator();

        Assertions.assertEquals(expectedOutput, outputStream.toString()); 
        is = new ByteArrayInputStream("q\n".getBytes());
        System.setIn(is);
        RobotMotion.run();
        outputStream.reset();
    }
    
    
    @Test
    public void testPenIsDown() {
       
        ByteArrayInputStream is = new ByteArrayInputStream("i10\nd\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertTrue(RobotMotion.isPenDown());
        outputStream.reset();
    }
    

    
    @Test
    public void testRightCommand() {
       //Test all Directions by turning robot right and checking direction
        ByteArrayInputStream is = new ByteArrayInputStream("i10\nr\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.EAST, RobotMotion.getDirection());
        
        outputStream.reset();
        is.reset();
        
        is = new ByteArrayInputStream("i10\nr\nr\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.SOUTH, RobotMotion.getDirection());
        
        outputStream.reset();
        is.reset();
        
        is = new ByteArrayInputStream("i10\nr\nr\nr\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();


        Assertions.assertEquals(RobotMotion.Direction.WEST, RobotMotion.getDirection());
        
        outputStream.reset();
        is.reset();
        
        is = new ByteArrayInputStream("i10\nr\nr\nr\nr\nq\n".getBytes());
        System.setIn(is);


        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.NORTH, RobotMotion.getDirection());
        outputStream.reset();
    }


  
    
    @Test
    public void testLeftCommand() {
    	//Test all Directions by turning robot left and checking direction
    	
        ByteArrayInputStream is = new ByteArrayInputStream("i10\nl\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.WEST, RobotMotion.getDirection());
        
        outputStream.reset();
        is.reset();
        
        is = new ByteArrayInputStream("i10\nl\nl\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.SOUTH, RobotMotion.getDirection());
        
        outputStream.reset();
        is.reset();
        
        is = new ByteArrayInputStream("i10\nl\nl\nl\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.EAST, RobotMotion.getDirection());
        
        outputStream.reset();
        is.reset();
        
        is = new ByteArrayInputStream("i10\nl\nl\nl\nl\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertEquals(RobotMotion.Direction.NORTH, RobotMotion.getDirection());
        outputStream.reset();
    }


    
    

    @Test
    public void testCommandPrintFloor() {
   
        RobotMotion.initializeSystem(3);
        RobotMotion.setPen(true); 
        RobotMotion.move(2);
        RobotMotion.turnRight();
        RobotMotion.move(1);
        RobotMotion.printFloor();

        String floor = "  +------+" + System.lineSeparator() +
                "2 |* *   |" + System.lineSeparator() +
                "1 |*     |" + System.lineSeparator() +
                "0 |*     |" + System.lineSeparator() +
                "  +------+" + System.lineSeparator() +
                "   0 1 2 " + System.lineSeparator();
        
        Assertions.assertEquals(floor, outputStream.toString());
        outputStream.reset();
    }
    @Test
    public void testPrintFloor1() {
   
        ByteArrayInputStream is = new ByteArrayInputStream("i3\np\nq\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        
        String e="Enter command: "
        		+ "Enter command: "
        		+ "  +------+\n"
        		+ "2 |      |\n"
        		+ "1 |      |\n"
        		+ "0 |      |\n"
        		+ "  +------+\n"
        		+ "   0 1 2 \n"
        		+ "Enter command: ";

        Assertions.assertNotEquals(e, outputStream.toString());
        outputStream.reset();

    }
    
    @Test
    public void testPrintPosition() {
    	ByteArrayInputStream is = new ByteArrayInputStream("i3\nr\nm2\nc\nq\n".getBytes());
        System.setIn(is);
        RobotMotion.run();

        Assertions.assertEquals("Enter command: Enter command: Enter command: Enter command: Position: 2, 0 - Pen: up - Facing: east\n"
        		+ "Enter command: ", outputStream.toString());
        outputStream.reset();
        }
    
    
//    @Test
//    public void testPrintCurrentPosition() {
//        RobotMotion.initializeSystem(3);
//        // test case 1: print current position after initialization
//        RobotMotion.printCurrentPosition();
//        Assertions.assertEquals("Position: 0, 0 - Pen: up - Facing: north\n", outputStream.toString());
//
//        // test case 2: same system, movement is performed, but pen is up
//        outputStream.reset();
//        RobotMotion.setPen(false);
//        RobotMotion.turnRight();
//        RobotMotion.move(2);
//        RobotMotion.printCurrentPosition();
//        Assertions.assertEquals("Position: 2, 0 - Pen: up - Facing: east\n", outputStream.toString());
//
//        // test case 3: new system, movement is performed, with pen down
//        outputStream.reset();
//        RobotMotion.initializeSystem(3);
//        RobotMotion.setPen(true);
//        RobotMotion.turnRight();
//        RobotMotion.move(1);
//        RobotMotion.printCurrentPosition();
//        Assertions.assertEquals("Position: 1, 0 - Pen: down - Facing: east\n", outputStream.toString());
//        
//        // test case 4: larger system, larger movements performed, with pen down
//        outputStream.reset();
//        RobotMotion.initializeSystem(10);
//        RobotMotion.setPen(true);
//        RobotMotion.move(4);
//        RobotMotion.turnRight();
//        RobotMotion.move(7);
//        RobotMotion.turnRight();
//        RobotMotion.printCurrentPosition();
//        Assertions.assertEquals("Position: 7, 4 - Pen: down - Facing: south\n", outputStream.toString());
//        outputStream.reset();
//    }

    
    
   
    @Test
    public void testQuitCommand() {
        
        ByteArrayInputStream is = new ByteArrayInputStream("q\n".getBytes());
        System.setIn(is);

        RobotMotion.run();

        Assertions.assertTrue(true);
        outputStream.reset();
    }

    
    @Test
    public void  testInvalidCommand() {
     
        ByteArrayInputStream is = new ByteArrayInputStream("v\nq\n".getBytes());
        System.setIn(is);
        RobotMotion.run();
        Assertions.assertEquals("Enter command: Invalid command!" +System.lineSeparator()+ "Enter command: ", outputStream.toString());
        outputStream.reset();
    }
    
    @Test
    public void testMove() {
        RobotMotion.initializeSystem(9);
        // To check pen Up {1-3}
        RobotMotion.setPen(false);
        // to test direction= NORTH
        RobotMotion.move(6);
        Assertions.assertEquals(0, RobotMotion.getPosX());
        Assertions.assertEquals(6, RobotMotion.getPosY());
        // to test direction= EAST
        RobotMotion.turnRight();
        RobotMotion.move(2);
        Assertions.assertEquals(2, RobotMotion.getPosX());
        Assertions.assertEquals(6, RobotMotion.getPosY());
        // to test direction= SOUTH
        RobotMotion.turnRight();
        RobotMotion.move(3);
        RobotMotion.move(1);
        Assertions.assertEquals(2, RobotMotion.getPosX());
        Assertions.assertEquals(2, RobotMotion.getPosY());
        // to test direction= WEST
        RobotMotion.turnRight();
        RobotMotion.move(1);
        Assertions.assertEquals(1, RobotMotion.getPosX());
        Assertions.assertEquals(2, RobotMotion.getPosY());       
 
        RobotMotion.initializeSystem(5);
     // To check pen down {1-2}
        RobotMotion.setPen(true);
        RobotMotion.turnRight();
        RobotMotion.move(3);
        RobotMotion.turnLeft();
        RobotMotion.move(2);
        RobotMotion.turnLeft();
        RobotMotion.move(1);
        Assertions.assertEquals(2, RobotMotion.getPosX());
        Assertions.assertEquals(2, RobotMotion.getPosY());
        RobotMotion.turnLeft();
        RobotMotion.move(1);
        Assertions.assertEquals(2, RobotMotion.getPosX());
        Assertions.assertEquals(1, RobotMotion.getPosY());
        
        
        //To cover out of bound conditions of move function
        RobotMotion.initializeSystem(9);
        RobotMotion.setPen(false);
        RobotMotion.move(10);
        Assertions.assertEquals(0, RobotMotion.getPosX());
        Assertions.assertEquals(8, RobotMotion.getPosY());
        RobotMotion.turnRight();
        RobotMotion.move(10);
        Assertions.assertEquals(8, RobotMotion.getPosX());
        Assertions.assertEquals(8, RobotMotion.getPosY());
        RobotMotion.turnRight();
        RobotMotion.move(10);
        Assertions.assertEquals(8, RobotMotion.getPosX());
        Assertions.assertEquals(0, RobotMotion.getPosY());
        RobotMotion.turnRight();
        RobotMotion.move(10);
        Assertions.assertEquals(0, RobotMotion.getPosX());
        Assertions.assertEquals(0, RobotMotion.getPosY());
    
        
        outputStream.reset();
    }
    
    
   
}
