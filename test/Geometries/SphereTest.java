/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

import Elements.Camera;
import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.util.ArrayList;
import java.util.List;
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
public class SphereTest {
    
    public SphereTest() {
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
     * Test of getNormal method, of class Sphere.
     */
    //@Test
    public void testGetNormal() {
        System.out.println("getNormal");
        Point3D p = null;
        Sphere instance = new Sphere();
        Vector expResult = null;
        Vector result = instance.getNormal(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of FindIntersections method, of class Sphere.
     */
    @Test
    public void testFindIntersections() {
        System.out.println("FindIntersections");
        int count=0;
        Camera c=new Camera();
        Sphere instance = new Sphere(1.0,new Point3D(new Coordinate(),new Coordinate(),new Coordinate(-3.0)));
        ArrayList<Ray> rayList=new ArrayList<Ray>();
        ArrayList<ArrayList<Point3D>> list=new ArrayList<ArrayList<Point3D>>();
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                rayList.add(c.constructRayThroughPixel(3, 3, i, j, 1, 10, 10));
            }
        }
        for (Ray ray : rayList) {
            ArrayList<Point3D> listOfPoints = new ArrayList<Point3D>(instance.findIntersections(ray));
            list.add(listOfPoints);
        }
        
        for (ArrayList<Point3D> arrayList : list) {
            count+=arrayList.size();
        }
        int result=count;
        int expResult = 2;
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testFindIntersections2() {
        System.out.println("FindIntersections");
        int count=0;
        Camera c=new Camera();
        Sphere instance = new Sphere(5.0,new Point3D(new Coordinate(),new Coordinate(),new Coordinate()));
        ArrayList<Ray> rayList=new ArrayList<Ray>();
        ArrayList<ArrayList<Point3D>> list=new ArrayList<ArrayList<Point3D>>();
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                rayList.add(c.constructRayThroughPixel(3, 3, i, j, 1, 2, 2));
            }
        }
        for (Ray ray : rayList) {
            ArrayList<Point3D> listOfPoints = new ArrayList<Point3D>(instance.findIntersections(ray));
            list.add(listOfPoints);
        }
        
        for (ArrayList<Point3D> arrayList : list) {
            count+=arrayList.size();
        }
        int result=count;
        int expResult = 9;
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
