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
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;
/**
 *
 * @author User
 */
public class OBJTriangle extends OBJPolygon implements FlatGeometry{
    ArrayList<Point3D> boundingBox;
    private Vector normal;
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

    public Vector getNormal() {
        return new Vector(normal);
    }

    public void setNormal(Vector normal) {
        this.normal = new Vector(normal);
    }

    public OBJTriangle() {
        super();
    }
//בהנחה שהם לא על אותו ישר
//לקחים 2 זוגות משווים את השיפועים

    public OBJTriangle(Point3D p1, Point3D p2, Point3D p3, Vector normal) {
        this.p1 = new Point3D(p1);
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
        this.normal = new Vector(normal);
    }

    public OBJTriangle(OBJTriangle copy) {

        this.p1 = new Point3D(copy.getP1());
        this.p2 = new Point3D(copy.getP2());
        this.p3 = new Point3D(copy.getP3());
        this.normal = new Vector(copy.normal);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return new Vector(normal);
    }
    public Vector getFaceNormal(Point3D p) {
        Vector v1 = new Vector(this.getP1());
        v1.subtract(new Vector(this.getP2()));
        Vector normal1 = new Vector(this.getP3());
        normal1.subtract(new Vector(this.getP2()));
        normal1 = normal1.crossProduct(v1);
        normal1.normalize();
        return normal1;
    }

    @Override
    public String toString() {
        return p1 + " " + p2 + " " + p3 + " " + "normal:" + normal.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector triangleNormal(Point3D p1, Point3D p2, Point3D cameraPoo) {
        Vector temp = new Vector(cameraPoo);
        p1.subtract(temp);
        p2.subtract(temp);
        Vector vp1 = new Vector(p1);
        Vector vp2 = new Vector(p2);
        vp1 = vp1.crossProduct(vp2);
        vp1.normalize();
        return vp1;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector planeNormal = new Vector(this.getFaceNormal(p1));
        Plane planeOfTriangle = new Plane(p3, planeNormal);
        ArrayList<Point3D> list2 = new ArrayList<>(planeOfTriangle.findIntersections(ray));
        if (list2.isEmpty()) {
            return list2;
        }
        else if (!intersectsBoundinBox(ray)) {
            list2.clear();
            return list2;
        }
        Point3D planeIntersectionPoint = list2.get(0);
        Vector normal1 = new Vector(triangleNormal(new Point3D(p1), new Point3D(p2), new Point3D(ray.getPoo())));
        Vector normal2 = new Vector(triangleNormal(new Point3D(p2), new Point3D(p3), new Point3D(ray.getPoo())));
        Vector normal3 = new Vector(triangleNormal(new Point3D(p3), new Point3D(p1), new Point3D(ray.getPoo())));

        Point3D testPoint = new Point3D(ray.getPoo());
        Vector temp = new Vector(planeIntersectionPoint);
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
    public ArrayList<Point3D> getMinMaxPoints() {  
        boundingBox=new ArrayList<>();
        double x1 = getP1().getX().getCoordinate();
        double y1 = getP1().getY().getCoordinate();
        double z1 = getP1().getZ().getCoordinate();
        double x2 = getP2().getX().getCoordinate();
        double y2 = getP2().getY().getCoordinate();
        double z2 = getP2().getZ().getCoordinate();
        double x3 = getP3().getX().getCoordinate();
        double y3 = getP3().getY().getCoordinate();
        double z3 = getP3().getZ().getCoordinate();
        
        double minX = min(min(x1, x2),x3);
        double minY = min(min(y1, y2),y3);
        double minZ = min(min(z1, z2),z3);
        
        double maxX = max(max(x1, x2),x3);
        double maxY = max(max(y1, y2),y3);
        double maxZ = max(max(z1, z2),z3);               

        Point3D min = new Point3D(new Coordinate(minX), new Coordinate(minY), new Coordinate(minZ));
        Point3D max = new Point3D(new Coordinate(maxX), new Coordinate(maxY), new Coordinate(maxZ));

       // minMax.add(min);
        //minMax.add(max);
        boundingBox.add(min);
        boundingBox.add(max);
        return boundingBox;
    }

    @Override
    public void move(double x, double y, double z) {
            this.setP1(new Point3D(new Coordinate(this.getP1().getX().getCoordinate() + x), new Coordinate(this.getP1().getY().getCoordinate() + y), new Coordinate(this.getP1().getZ().getCoordinate() + z)));
            this.setP2(new Point3D(new Coordinate(this.getP2().getX().getCoordinate() + x), new Coordinate(this.getP2().getY().getCoordinate() + y), new Coordinate(this.getP2().getZ().getCoordinate() + z)));
            this.setP3(new Point3D(new Coordinate(this.getP3().getX().getCoordinate() + x), new Coordinate(this.getP3().getY().getCoordinate() + y), new Coordinate(this.getP3().getZ().getCoordinate() + z)));       
    }

    @Override
    public boolean intersectsBoundinBox(Ray ray) {      
        ArrayList<Point3D> list = boundingBox;

        double t1 = (list.get(0).getX().getCoordinate() - ray.getPoo().getX().getCoordinate()) * (1 / ray.getDirection().getHead().getX().getCoordinate());
        double t2 = (list.get(1).getX().getCoordinate() - ray.getPoo().getX().getCoordinate()) * (1 / ray.getDirection().getHead().getX().getCoordinate());
        double t3 = (list.get(0).getY().getCoordinate() - ray.getPoo().getY().getCoordinate()) * (1 / ray.getDirection().getHead().getY().getCoordinate());
        double t4 = (list.get(1).getY().getCoordinate() - ray.getPoo().getY().getCoordinate()) * (1 / ray.getDirection().getHead().getY().getCoordinate());
        double t5 = (list.get(0).getZ().getCoordinate() - ray.getPoo().getZ().getCoordinate()) * (1 / ray.getDirection().getHead().getZ().getCoordinate());
        double t6 = (list.get(1).getZ().getCoordinate() - ray.getPoo().getZ().getCoordinate()) * (1 / ray.getDirection().getHead().getZ().getCoordinate());

        double tmin = max(max(min(t1, t2), min(t3, t4)), min(t5, t6));
        double tmax = min(min(max(t1, t2), max(t3, t4)), max(t5, t6));

        // if tmax < 0, ray (line) is intersecting AABB, but whole AABB is behing us
        if (tmax < 0) {
            return false;
        }

// if tmin > tmax, ray doesn't intersect AABB
        if (tmin > tmax) {
            return false;
        }
        return true;
    }

}
