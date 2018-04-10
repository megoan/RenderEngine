/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
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
public class CameraTest {
    
    public CameraTest() {
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
     * Test of constructRayThroughPixel method, of class Camera.
     */
    @Test
    public void testConstructRayThroughPixel() {
        System.out.println("constructRayThroughPixel");
        int Nx = 3;
        int Ny = 3;
        double x = 0;
        double y = 0;
        double screenDistance = 1;
        double screenWidth = 9;
        double screenHeight = 9;
        Point3D P0=new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Vector vUp=new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        Vector vTo=new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
        Camera instance = new Camera(P0, vUp, vTo);
        Point3D expResult = new Point3D(new Coordinate(-3),new Coordinate(3),new Coordinate(-1));
        Ray result = instance.constructRayThroughPixel(Nx, Ny, x, y, screenDistance, screenWidth, screenHeight);
        assertEquals(expResult.toString(), result.getPoo().toString());        
    }   
    /**
     * Test of constructRayThroughPixel method, of class Camera.
     */
    @Test
    public void testConstructRayThroughPixel2() {
        System.out.println("constructRayThroughPixel");
        int Nx = 3;
        int Ny = 3;
        double x = 1;
        double y = 1;
        double screenDistance = 1;
        double screenWidth = 9;
        double screenHeight = 9;
        Point3D P0=new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Vector vUp=new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        Vector vTo=new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
        Camera instance = new Camera(P0, vUp, vTo);
        Point3D expResult = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1));
        Ray result = instance.constructRayThroughPixel(Nx, Ny, x, y, screenDistance, screenWidth, screenHeight);
        assertEquals(expResult.toString(), result.getPoo().toString());        
    }   
    /**
     * Test of constructRayThroughPixel method, of class Camera.
     */
    @Test
    public void testConstructRayThroughPixel3() {
        System.out.println("constructRayThroughPixel");
        int Nx = 3;
        int Ny = 3;
        double x = 2;
        double y = 2;
        double screenDistance = 1;
        double screenWidth = 9;
        double screenHeight = 9;
        Point3D P0=new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Vector vUp=new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        Vector vTo=new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
        Camera instance = new Camera(P0, vUp, vTo);
        Point3D expResult = new Point3D(new Coordinate(3),new Coordinate(-3),new Coordinate(-1));
        Ray result = instance.constructRayThroughPixel(Nx, Ny, x, y, screenDistance, screenWidth, screenHeight);
        assertEquals(expResult.toString(), result.getPoo().toString());        
    }   
}
