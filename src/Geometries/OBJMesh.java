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
import java.awt.Color;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class OBJMesh extends Geometry {

    
    
    
    public ArrayList<OBJPolygon> polygons;
    public ArrayList<Point3D> boundingBox;

    public OBJMesh() {
        polygons = new ArrayList<>();
        boundingBox = new ArrayList<>();
    }
    public OBJMesh(OBJMesh copy) {
        polygons = new ArrayList<>(copy.polygons);
        boundingBox = new ArrayList<>(copy.boundingBox);
        
    }
    
    
    
    public void calcBoundingBox() {
        ArrayList<Point3D> box = new ArrayList<>();
        ArrayList<Point3D> minmax = polygons.get(0).getMinMaxPoints();
        ArrayList<Point3D> tempMinMax;
        double minX = minmax.get(0).getX().getCoordinate();
        double minY = minmax.get(0).getY().getCoordinate();
        double minZ = minmax.get(0).getZ().getCoordinate();
        double maxX = minmax.get(1).getX().getCoordinate();
        double maxY = minmax.get(1).getY().getCoordinate();
        double maxZ = minmax.get(1).getZ().getCoordinate();
        for (OBJPolygon polygon : polygons) {
            tempMinMax=new ArrayList<>(polygon.getMinMaxPoints());
            double x = tempMinMax.get(0).getX().getCoordinate();
            double y = tempMinMax.get(0).getY().getCoordinate();
            double z = tempMinMax.get(0).getZ().getCoordinate();
            double mx = tempMinMax.get(1).getX().getCoordinate();
            double my = tempMinMax.get(1).getY().getCoordinate();
            double mz = tempMinMax.get(1).getZ().getCoordinate();
            if (x < minX) {
                minX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (z < minZ) {
                minZ = z;
            }
            if (mx > maxX) {
                maxX = mx;
            }
            if (my > maxY) {
                maxY = my;
            }
            if (mz > maxZ) {
                maxZ = mz;
            }
            tempMinMax.clear();
        }

        Point3D min = new Point3D(new Coordinate(minX), new Coordinate(minY), new Coordinate(minZ));
        Point3D max = new Point3D(new Coordinate(maxX), new Coordinate(maxY), new Coordinate(maxZ));
        box.add(min);
        box.add(max);
        boundingBox = box;
        for (OBJPolygon polygon : polygons) {
            polygon.MainoundingBox=boundingBox;
        }
    }

    @Override
    public Vector getNormal(Point3D p) {
//        if (((int) (p.getX().getCoordinate() - 0.0000000001) != (int) (p.getX().getCoordinate()))
//                || (((int) (p.getX().getCoordinate() + 0.0000000001) != (int) (p.getX().getCoordinate())))) {
//            p.getX().setCoordinate(Math.round(p.getX().getCoordinate()));
//        }
//        if (((int) (p.getY().getCoordinate() - 0.0000000001) != (int) (p.getY().getCoordinate()))
//                || (((int) (p.getY().getCoordinate() + 0.0000000001) != (int) (p.getY().getCoordinate())))) {
//            p.getY().setCoordinate(Math.round(p.getY().getCoordinate()));
//        }
//        if (((int) (p.getZ().getCoordinate() - 0.0000000001) != (int) (p.getZ().getCoordinate()))
//                || (((int) (p.getZ().getCoordinate() + 0.0000000001) != (int) (p.getZ().getCoordinate())))) {
//            p.getZ().setCoordinate(Math.round(p.getZ().getCoordinate()));
//        }
//
//        for (OBJPolygon triangle1 : polygons) {
//            if (triangle1 instanceof OBJTriangle) {
//                OBJTriangle triangle = (OBJTriangle) triangle1;
//                double pointx = p.getX().getCoordinate();
//                double triP1x = triangle.getP1().getX().getCoordinate();
//                double triP2x = triangle.getP2().getX().getCoordinate();
//                double triP3x = triangle.getP3().getX().getCoordinate();
//                double pointy = p.getY().getCoordinate();
//                double triP1y = triangle.getP1().getY().getCoordinate();
//                double triP2y = triangle.getP2().getY().getCoordinate();
//                double triP3y = triangle.getP3().getY().getCoordinate();
//                double pointz = p.getZ().getCoordinate();
//                double triP1z = triangle.getP1().getZ().getCoordinate();
//                double triP2z = triangle.getP2().getZ().getCoordinate();
//                double triP3z = triangle.getP3().getZ().getCoordinate();
//                if ((pointx > triP1x && pointx > triP2x && pointx > triP3x || pointx < triP1x && pointx < triP2x && pointx < triP3x)
//                        || (pointy > triP1y && pointy > triP2y && pointy > triP3y || pointy < triP1y && pointy < triP2y && pointy < triP3y)
//                        || (pointz > triP1z && pointz > triP2z && pointz > triP3z || pointz < triP1z && pointz < triP2z && pointz < triP3z)) {
//                    {
//                        continue;
//                    }
//                }
//                Vector panswer = new Vector(p);
//                Vector p1 = new Vector(triangle.getP1());
//                Vector p2 = new Vector(triangle.getP2());
//                Vector p3 = new Vector(triangle.getP3());
//
//                panswer.subtract(p1);
//                p2.subtract(p1);
//                p3.subtract(p1);
//
//                double dot00 = p3.dotProduct(p3);
//                double dot01 = p3.dotProduct(p2);
//                double dot02 = p3.dotProduct(panswer);
//                double dot11 = p2.dotProduct(p2);
//                double dot12 = p2.dotProduct(panswer);
//                double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
//                double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
//                double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
//                if (u >= 0 && v >= 0 && u + v <= 1) {
//                    return triangle.getNormal(p);
//                }
//            } 
//        }
        return new Vector();
//      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> intersections = new ArrayList<>();
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
            return intersections;
        }

// if tmin > tmax, ray doesn't intersect AABB
        if (tmin > tmax) {
            return intersections;
        }

        for (OBJPolygon polygon : polygons) {
            {
                intersections.addAll(polygon.findIntersections(ray));
            }
        }
        return intersections;
    }

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

    public void move(double x, double y, double z) {
        for (OBJPolygon polygon : polygons) {
            polygon.move(x, y, z);
        }
    }

    
//    public OBJPolygon getTriangle(Point3D p)
//    {
//                if (((int) (p.getX().getCoordinate() - 0.0000000001) != (int) (p.getX().getCoordinate()))
//                || (((int) (p.getX().getCoordinate() + 0.0000000001) != (int) (p.getX().getCoordinate())))) {
//            p.getX().setCoordinate(Math.round(p.getX().getCoordinate()));
//        }
//        if (((int) (p.getY().getCoordinate() - 0.0000000001) != (int) (p.getY().getCoordinate()))
//                || (((int) (p.getY().getCoordinate() + 0.0000000001) != (int) (p.getY().getCoordinate())))) {
//            p.getY().setCoordinate(Math.round(p.getY().getCoordinate()));
//        }
//        if (((int) (p.getZ().getCoordinate() - 0.0000000001) != (int) (p.getZ().getCoordinate()))
//                || (((int) (p.getZ().getCoordinate() + 0.0000000001) != (int) (p.getZ().getCoordinate())))) {
//            p.getZ().setCoordinate(Math.round(p.getZ().getCoordinate()));
//        }
//
//        for (OBJPolygon triangle1 : polygons) {
//            if (triangle1 instanceof OBJTriangle) {
//                OBJTriangle triangle = (OBJTriangle) triangle1;
//                double pointx = p.getX().getCoordinate();
//                double triP1x = triangle.getP1().getX().getCoordinate();
//                double triP2x = triangle.getP2().getX().getCoordinate();
//                double triP3x = triangle.getP3().getX().getCoordinate();
//                double pointy = p.getY().getCoordinate();
//                double triP1y = triangle.getP1().getY().getCoordinate();
//                double triP2y = triangle.getP2().getY().getCoordinate();
//                double triP3y = triangle.getP3().getY().getCoordinate();
//                double pointz = p.getZ().getCoordinate();
//                double triP1z = triangle.getP1().getZ().getCoordinate();
//                double triP2z = triangle.getP2().getZ().getCoordinate();
//                double triP3z = triangle.getP3().getZ().getCoordinate();
//                if ((pointx > triP1x && pointx > triP2x && pointx > triP3x || pointx < triP1x && pointx < triP2x && pointx < triP3x)
//                        || (pointy > triP1y && pointy > triP2y && pointy > triP3y || pointy < triP1y && pointy < triP2y && pointy < triP3y)
//                        || (pointz > triP1z && pointz > triP2z && pointz > triP3z || pointz < triP1z && pointz < triP2z && pointz < triP3z)) {
//                    {
//                        continue;
//                    }
//                }
//                Vector panswer = new Vector(p);
//                Vector p1 = new Vector(triangle.getP1());
//                Vector p2 = new Vector(triangle.getP2());
//                Vector p3 = new Vector(triangle.getP3());
//
//                panswer.subtract(p1);
//                p2.subtract(p1);
//                p3.subtract(p1);
//
//                double dot00 = p3.dotProduct(p3);
//                double dot01 = p3.dotProduct(p2);
//                double dot02 = p3.dotProduct(panswer);
//                double dot11 = p2.dotProduct(p2);
//                double dot12 = p2.dotProduct(panswer);
//                double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
//                double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
//                double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
//                if (u >= 0 && v >= 0 && u + v <= 1) {
//                    return triangle;
//                }
//            } 
//        }
//        return new OBJTriangle();
//    }
}
