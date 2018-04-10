/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TriangularMesh extends Geometry {

    public ArrayList<OBJTriangle> mesh;

    public TriangularMesh(ArrayList<OBJTriangle> mesh) {
        this.mesh = mesh;
    }

    public TriangularMesh() {
    }

    @Override
    public Vector getNormal(Point3D p) {
        if(((int)(p.getX().getCoordinate()-0.0000000001)!=(int)(p.getX().getCoordinate()))||
                (((int)(p.getX().getCoordinate()+0.0000000001)!=(int)(p.getX().getCoordinate()))))
        {
            p.getX().setCoordinate(Math.round(p.getX().getCoordinate()));
        }
        if(((int)(p.getY().getCoordinate()-0.0000000001)!=(int)(p.getY().getCoordinate()))||
                (((int)(p.getY().getCoordinate()+0.0000000001)!=(int)(p.getY().getCoordinate()))))
        {
            p.getY().setCoordinate(Math.round(p.getY().getCoordinate()));
        }
        if(((int)(p.getZ().getCoordinate()-0.0000000001)!=(int)(p.getZ().getCoordinate()))||
                (((int)(p.getZ().getCoordinate()+0.0000000001)!=(int)(p.getZ().getCoordinate()))))
        {
            p.getZ().setCoordinate(Math.round(p.getZ().getCoordinate()));
        }
       
        for (OBJTriangle triangle : mesh) {
            double pointx = p.getX().getCoordinate();
            double triP1x = triangle.getP1().getX().getCoordinate();
            double triP2x = triangle.getP2().getX().getCoordinate();
            double triP3x = triangle.getP3().getX().getCoordinate();
            double pointy = p.getY().getCoordinate();
            double triP1y = triangle.getP1().getY().getCoordinate();
            double triP2y = triangle.getP2().getY().getCoordinate();
            double triP3y = triangle.getP3().getY().getCoordinate();
            double pointz = p.getZ().getCoordinate();
            double triP1z = triangle.getP1().getZ().getCoordinate();
            double triP2z = triangle.getP2().getZ().getCoordinate();
            double triP3z = triangle.getP3().getZ().getCoordinate();
            if ((pointx > triP1x && pointx > triP2x && pointx > triP3x || pointx < triP1x && pointx < triP2x && pointx < triP3x)
                    || (pointy > triP1y && pointy > triP2y && pointy > triP3y || pointy < triP1y && pointy < triP2y && pointy < triP3y)
                    || (pointz > triP1z && pointz > triP2z && pointz > triP3z || pointz < triP1z && pointz < triP2z && pointz < triP3z)) {
                {                   
                    continue;
                }
            }
            Vector panswer = new Vector(p);
            Vector p1 = new Vector(triangle.getP1());
            Vector p2 = new Vector(triangle.getP2());
            Vector p3 = new Vector(triangle.getP3());

            panswer.subtract(p1);
            p2.subtract(p1);
            p3.subtract(p1);

            double dot00 = p3.dotProduct(p3);
            double dot01 = p3.dotProduct(p2);
            double dot02 = p3.dotProduct(panswer);
            double dot11 = p2.dotProduct(p2);
            double dot12 = p2.dotProduct(panswer);
            double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
            double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
            double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
            if (u >= 0 && v >= 0 && u + v <= 1) {
                return triangle.getNormal(p);
            }           
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> list = new ArrayList<>();
        for (OBJTriangle triangle : mesh) {
            list.addAll(triangle.findIntersections(ray));
        }
        return list;
    }
}
