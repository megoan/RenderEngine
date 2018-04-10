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
 * @author User
 */
public class VectorTest {
    
    public VectorTest() {
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
     * Test of add method, of class Vector.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Vector other = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector instance = new Vector(new Point3D(new Coordinate(3),new Coordinate(8),new Coordinate(5)));
        instance.add(other);
         Vector instance2 = new Vector(new Point3D(new Coordinate(4),new Coordinate(9),new Coordinate(6)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
/**
     * Test of add method, of class Vector.
     */
    @Test
    public void testAdd2() {
        System.out.println("add");
        Vector other = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector instance = new Vector(new Point3D(new Coordinate(-1),new Coordinate(-1),new Coordinate(-1)));
        instance.add(other);
         Vector instance2 = new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    /**
     * Test of add method, of class Vector.
     */
    @Test
    public void testAdd3() {
        System.out.println("add");
        Vector other = new Vector(new Point3D(new Coordinate(-1),new Coordinate(-1),new Coordinate(-1)));
        Vector instance = new Vector(new Point3D(new Coordinate(-1),new Coordinate(-1),new Coordinate(-1)));
        instance.add(other);
         Vector instance2 = new Vector(new Point3D(new Coordinate(-2),new Coordinate(-2),new Coordinate(-2)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    /**
     * Test of subtract method, of class Vector.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Vector other = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector instance = new Vector(new Point3D(new Coordinate(3),new Coordinate(8),new Coordinate(5)));
        instance.subtract(other);
        Vector instance2 = new Vector(new Point3D(new Coordinate(2),new Coordinate(7),new Coordinate(4)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    /**
     * Test of subtract method, of class Vector.
     */
    @Test
    public void testSubtract2() {
        System.out.println("subtract");
        Vector other = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector instance = new Vector(new Point3D(new Coordinate(-3),new Coordinate(-8),new Coordinate(-5)));
        instance.subtract(other);
        Vector instance2 = new Vector(new Point3D(new Coordinate(-4),new Coordinate(-9),new Coordinate(-6)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
/**
     * Test of subtract method, of class Vector.
     */
    @Test
    public void testSubtract3() {
        System.out.println("subtract");
        Vector other = new Vector(new Point3D(new Coordinate(-1),new Coordinate(-1),new Coordinate(-1)));
        Vector instance = new Vector(new Point3D(new Coordinate(3),new Coordinate(8),new Coordinate(5)));
        instance.subtract(other);
        Vector instance2 = new Vector(new Point3D(new Coordinate(4),new Coordinate(9),new Coordinate(6)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    /**
     * Test of scale method, of class Vector.
     */
    @Test
    public void testScale() {
        System.out.println("scale");
        double scale = 5;
        Vector instance = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        instance.scale(scale);
        Vector instance2 = new Vector(new Point3D(new Coordinate(5),new Coordinate(5),new Coordinate(5)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
/**
     * Test of scale method, of class Vector.
     */
    @Test
    public void testScale2() {
        System.out.println("scale");
        double scale = -0.5;
        Vector instance = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        instance.scale(scale);
        Vector instance2 = new Vector(new Point3D(new Coordinate(-0.5),new Coordinate(-0.5),new Coordinate(-0.5)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of dotProduct method, of class Vector.
     */
    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector other = new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        Vector instance = new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0)));
        double expResult = 0;
        double result = instance.dotProduct(other);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    /**
     * Test of dotProduct method, of class Vector.
     */
    @Test
    public void testDotProduct2() {
        System.out.println("dotProduct");
        Vector other = new Vector(new Point3D(new Coordinate(2),new Coordinate(2),new Coordinate(2)));
        Vector instance = new Vector(new Point3D(new Coordinate(-2),new Coordinate(-2),new Coordinate(-2)));
        double expResult = -12;
        double result = instance.dotProduct(other);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of length method, of class Vector.
     */
    @Test
    public void testLengh() {
        System.out.println("length");
        Vector instance = new Vector(new Point3D(new Coordinate(-2),new Coordinate(-2),new Coordinate(-2)));
        double expResult = Math.sqrt(12);
        double result = instance.length();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
/**
     * Test of length method, of class Vector.
     */
    @Test
    public void testLengh2() {
        System.out.println("length");
        Vector instance = new Vector(new Point3D(new Coordinate(2),new Coordinate(2),new Coordinate(2)));
        double expResult = Math.sqrt(12);
        double result = instance.length();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of normalize method, of class Vector.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        Vector instance = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        instance.normalize();
        Vector instance2 = new Vector(new Point3D(new Coordinate(1/Math.sqrt(3)),new Coordinate(1/Math.sqrt(3)),new Coordinate(1/Math.sqrt(3))));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
/**
     * Test of normalize method, of class Vector.
     */
    @Test
    public void testNormalize2() {
        System.out.println("normalize");
        Vector instance = new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0)));
        instance.normalize();
        Vector instance2 = new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0)));
        assertEquals(instance.toString(),instance2.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of crossProduct method, of class Vector.
     */
    @Test
    public void testCrossProduct() {        
        System.out.println("crossProduct");
        Vector other = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector instance = new Vector(new Point3D(new Coordinate(4),new Coordinate(2),new Coordinate(2)));
        Vector expResult = new Vector(new Point3D(new Coordinate(0.0),new Coordinate(-2.0),new Coordinate(2.0)));
        Vector result = instance.crossProduct(other);
        assertEquals(result.toString(),expResult.toString());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testCrossProduct2() {        
        System.out.println("crossProduct");
        Vector other = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector instance = new Vector(new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate(1)));
        Vector expResult = new Vector(new Point3D(new Coordinate(0.0),new Coordinate(-0.0),new Coordinate(0.0)));
        Vector result = instance.crossProduct(other);
        assertEquals(result.toString(),expResult.toString());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
