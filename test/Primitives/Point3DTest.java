/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Primitives;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shmuel
 */
public class Point3DTest {
    
    public Point3DTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
   
    /**
     * Test of add method, of class Point3D.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Point3D other = new Point3D(new Coordinate(-2),new Coordinate(-3),new Coordinate(-4));
        Point3D instance = new Point3D(new Coordinate(2),new Coordinate(3),new Coordinate(4));
        Point3D result=new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Vector temp=new Vector(other);
        instance.add(temp);
        assertEquals(instance.toString(), result.toString());
    }

    /**
     * Test of subtract method, of class Point3D.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Point3D other = new Point3D(new Coordinate(-2),new Coordinate(-3),new Coordinate(-4));
        Point3D instance = new Point3D(new Coordinate(2),new Coordinate(3),new Coordinate(4));
        Point3D result=new Point3D(new Coordinate(4),new Coordinate(6),new Coordinate(8));
        Vector temp=new Vector(other);
        instance.subtract(temp);
        assertEquals(instance.toString(), result.toString());
    }

    /**
     * Test of distance method, of class Point3D.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        Point3D point = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Point3D instance = new Point3D(new Coordinate(2),new Coordinate(2),new Coordinate(2));
        double expResult = Math.sqrt(12);
        double result = instance.distance(point);
        assertEquals(expResult, result, 0.0);     
    }
    
}
