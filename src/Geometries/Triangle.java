/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

import Primitives.Coordinate;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Triangle extends Geometry implements FlatGeometry{

    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    public Point3D getP1() {
        return new Point3D(p1);
    }

    public void setP1(Point3D p1) {
        this.p1 = new Point3D(p1);
    }

    public Point3D getP2() {
        return new Point3D(p2);
    }

    public void setP2(Point3D p2) {
        this.p2 = new Point3D(p2);
    }

    public Point3D getP3() {
        return new Point3D(p3);
    }

    public void setP3(Point3D p3) {
        this.p3 = new Point3D(p3);
    }

    public Triangle() {
        p1 = new Point3D();
        p2 = new Point3D();
        p3 = new Point3D();
    }
//בהנחה שהם לא על אותו ישר
//לקחים 2 זוגות משווים את השיפועים
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = new Point3D(p1);
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
    }

    public Triangle(Triangle copy) {
        this.p1 = new Point3D(copy.p1);
        this.p2 = new Point3D(copy.p2);
        this.p3 = new Point3D(copy.p3);
        
        super.setEmmission(new Color(copy.getEmmission().getRGB()));
        super.setShininess(copy.getShininess());
        super.setMaterial(new Material(copy.getMaterial()));
        super.setShadow(copy.isShadow());
    }

    //gets triangle normal
    @Override
    public Vector getNormal(Point3D p) {
        Vector v1 = new Vector(p1);
        v1.subtract(new Vector(p2));
        Vector normal = new Vector(p3);
        normal.subtract(new Vector(p2));
        normal = normal.crossProduct(v1);
        normal.normalize();
        //normal.scale(-1);
        return normal;
    }
    //gets triangle normal
    public Vector triangleNormal(Point3D p1, Point3D p2, Point3D cameraPoo) {
        Vector temp=new Vector(cameraPoo);
        p1.subtract(temp);
        p2.subtract(temp);
        Vector vp1 = new Vector(p1);
        Vector vp2 = new Vector(p2);
        vp1 = vp1.crossProduct(vp2);
        vp1.normalize();
        return vp1;
    }

    //findintersection with ray and triangle
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector planeNormal = new Vector(this.getNormal(p1));
        Plane planeOfTriangle = new Plane(p3, planeNormal);
        ArrayList<Point3D> list2 = new ArrayList<>(planeOfTriangle.findIntersections(ray));
        if (list2.isEmpty()) {
            return list2;
        }
        Point3D planeIntersectionPoint = list2.get(0);
        Vector normal1 = new Vector(triangleNormal(new Point3D(p1), new Point3D(p2), new Point3D(ray.getPoo())));
        Vector normal2 = new Vector(triangleNormal(new Point3D(p2), new Point3D(p3), new Point3D(ray.getPoo())));
        Vector normal3 = new Vector(triangleNormal(new Point3D(p3), new Point3D(p1), new Point3D(ray.getPoo())));

        Point3D testPoint = new Point3D(ray.getPoo());
        Vector temp=new Vector(planeIntersectionPoint);
        testPoint.subtract(temp);
        Vector testVector = new Vector(testPoint);

        double dotProduct1 = testVector.dotProduct(normal1);
        double dotProduct2 = testVector.dotProduct(normal2);
        double dotProduct3 = testVector.dotProduct(normal3);

        if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0) || (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
            return list2;          
        } else {
            list2.clear();
            return list2;
        }
    }

    @Override
    public String toString() {
        return "p1:"+p1.toString()+" "+"p2:"+p2.toString()+" "+"p3:"+p3.toString();
    }    
}


//צריך לודא שלא על ישר א'
