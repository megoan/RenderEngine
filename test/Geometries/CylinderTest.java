/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.util.List;
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
public class CylinderTest {
    
    public CylinderTest() {
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
     * Test of getNormal method, of class Cylinder.
     */
    @Test
    public void testGetNormal() {
        System.out.println("getNormal");
        Point3D p = new Point3D(new Coordinate(1),new Coordinate(1),new Coordinate());
        Cylinder instance = new Cylinder(new Point3D(),new Vector(new Point3D(new Coordinate(),new Coordinate(1),new Coordinate())),1);
        Vector expResult = new Vector(new Point3D(new Coordinate(1),new Coordinate(),new Coordinate()));
        Vector result = instance.getNormal(p);
        assertEquals(expResult.toString(), result.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of FindIntersections method, of class Cylinder.
     */
    //@Test
    public void testFindIntersections() {
        System.out.println("FindIntersections");
        Ray ray = null;
        Cylinder instance = new Cylinder();
        List<Point3D> expResult = null;
        List<Point3D> result = instance.findIntersections(ray);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
