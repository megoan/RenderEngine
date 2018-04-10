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
public class TriangleTest {
    
    public TriangleTest() {
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
     * Test of findIntersections method, of class Triangle.
     */
    @Test
    public void testfindIntersections() {
       System.out.println("findIntersections");
        int count=0;
        Camera c=new Camera();
        Coordinate c1=new Coordinate(-1);
        Coordinate c2=new Coordinate(-1);
        Coordinate c3=new Coordinate(-2);
        
        Coordinate c4=new Coordinate(1);
        Coordinate c5=new Coordinate(-1);
        Coordinate c6=new Coordinate(-2);
        
        Coordinate c7=new Coordinate(0);
        Coordinate c8=new Coordinate(10);
        Coordinate c9=new Coordinate(-2);
        
        Triangle instance2=new Triangle(new Point3D(c1,c2,c3),new Point3D(c4,c5,c6),new Point3D(c7,c8,c9));
        //Sphere instance = new Sphere(new Point3D(new Coordinate(),new Coordinate(),new Coordinate()),5.0);
        ArrayList<Ray> rayList=new ArrayList<Ray>();
        ArrayList<ArrayList<Point3D>> list=new ArrayList<ArrayList<Point3D>>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rayList.add(c.constructRayThroughPixel(3, 3, i, j, 1, 9, 9));
            }
        }
        for (Ray ray : rayList) {
            ArrayList<Point3D> listOfPoints = new ArrayList<Point3D>(instance2.findIntersections(ray));
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
     /**
     * Test of findIntersections method, of class Triangle.
     */
    @Test
    public void testfindIntersections2() {
       System.out.println("findIntersections");
        int count=0;
        Camera c=new Camera();
        Coordinate c1=new Coordinate(-1);
        Coordinate c2=new Coordinate(-1);
        Coordinate c3=new Coordinate(-2);
        
        Coordinate c4=new Coordinate(1);
        Coordinate c5=new Coordinate(-1);
        Coordinate c6=new Coordinate(-2);
        
        Coordinate c7=new Coordinate(0);
        Coordinate c8=new Coordinate(1);
        Coordinate c9=new Coordinate(-2);
        
        Triangle instance2=new Triangle(new Point3D(c1,c2,c3),new Point3D(c4,c5,c6),new Point3D(c7,c8,c9));
        //Sphere instance = new Sphere(new Point3D(new Coordinate(),new Coordinate(),new Coordinate()),5.0);
        ArrayList<Ray> rayList=new ArrayList<Ray>();
        ArrayList<ArrayList<Point3D>> list=new ArrayList<ArrayList<Point3D>>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rayList.add(c.constructRayThroughPixel(3, 3, i, j, 1, 9, 9));
            }
        }
        for (Ray ray : rayList) {
            ArrayList<Point3D> listOfPoints = new ArrayList<Point3D>(instance2.findIntersections(ray));
            list.add(listOfPoints);
        }
        
        for (ArrayList<Point3D> arrayList : list) {
            count+=arrayList.size();
        }
        int result=count;
        int expResult = 1;
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
     /**
     * Test of findIntersections method, of class Triangle.
     */
    @Test
    public void testfindIntersections4() {
       System.out.println("findIntersections");
        int count=0;
        Camera c=new Camera();
        Coordinate c1=new Coordinate(4);
        Coordinate c2=new Coordinate(-4);
        Coordinate c3=new Coordinate(4);
        
        Coordinate c4=new Coordinate(-3);
        Coordinate c5=new Coordinate(3);
        Coordinate c6=new Coordinate(5);
        
        Coordinate c7=new Coordinate(5);
        Coordinate c8=new Coordinate(2);
        Coordinate c9=new Coordinate(5);
        
        Triangle instance2=new Triangle(new Point3D(c1,c2,c3),new Point3D(c4,c5,c6),new Point3D(c7,c8,c9));
        
        Ray r=new Ray();
        Vector v=new Vector(new Point3D(new Coordinate(0.9),new Coordinate(1.9),new Coordinate(4.9)));
        v.normalize();
        r.setDirection(v);
        //Sphere instance = new Sphere(new Point3D(new Coordinate(),new Coordinate(),new Coordinate()),5.0);
        ArrayList<Ray> rayList=new ArrayList<Ray>();
        ArrayList<ArrayList<Point3D>> list=new ArrayList<ArrayList<Point3D>>();
        rayList.add(r);
        for (Ray ray : rayList) {
            ArrayList<Point3D> listOfPoints = new ArrayList<Point3D>(instance2.findIntersections(ray));
            list.add(listOfPoints);
        }
        
        for (ArrayList<Point3D> arrayList : list) {
            count+=arrayList.size();
        }
        int result=count;
        int expResult = 1;
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
