/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

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
public class Cylinder extends RadialGeometry {

    private Point3D axisPoint;
    private Vector axisDirection;

    public Point3D getAxisPoint() {
        return new Point3D(axisPoint);
    }

    public void setAxisPoint(Point3D axisPoint) {
        this.axisPoint = new Point3D(axisPoint);
    }

    public Vector getAxisDirection() {
        return new Vector(axisDirection);
    }

    public void setAxisDirection(Vector axisDirection) {
        this.axisDirection = new Vector(axisDirection);
    }

    public Cylinder() {
      this.axisPoint =new Point3D();
      this.axisDirection=new Vector();
    }

    public Cylinder(Point3D axisPoint, Vector axisDirection, double radius) {
        super(radius);
        this.axisPoint = new Point3D(axisPoint);
        this.axisDirection = new Vector(axisDirection);
    }

    public Cylinder(Cylinder copy) {
        this.radius = copy.radius;
        this.axisPoint = new Point3D(copy.axisPoint);
        this.axisDirection = new Vector(copy.axisDirection);
        super.setEmmission(new Color(copy.getEmmission().getRGB()));
        super.setShininess(copy.getShininess());
        super.setMaterial(new Material(copy.getMaterial()));
        super.setShadow(copy.isShadow());
    }

    @Override
    public Vector getNormal(Point3D p) {
        double a=p.getX().getCoordinate();
        double b=p.getY().getCoordinate();
        double c=p.getZ().getCoordinate();
        double x=axisDirection.getHead().getX().getCoordinate();
        double y=axisDirection.getHead().getY().getCoordinate();
        double z=axisDirection.getHead().getZ().getCoordinate();
        double t=(a*x+b*y+c*z)/(x*x+y*y+z*z);
        Vector temp=new Vector(axisDirection);
        temp.scale(t);
        Vector normal=new Vector(p);
        normal.subtract(temp);
        normal.normalize();
        return normal;
        
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> list =new ArrayList<>();
        //v1
        double scalarProduct=ray.getDirection().dotProduct(axisDirection);
        Vector vc=new Vector(axisDirection);
        vc.scale(scalarProduct);
        Vector V1=new Vector(ray.getDirection());
        V1.subtract(vc);
        
        //v2 
        Vector V2=new Vector(ray.getPoo());
        Vector pc=new Vector(axisPoint);
        V2.subtract(pc);
        scalarProduct=V2.dotProduct(axisDirection);
        Vector temp=new Vector(axisDirection);
        temp.scale(scalarProduct);
        V2.subtract(temp);
        
        double A=V1.dotProduct(V1);
        
        double B=2*V1.dotProduct(V2);
        
        double C=V2.dotProduct(V2)-radius*radius;
        
        double delta=B*B-4*A*C;
        //if there no intersections
        if(delta<0)return list;
        
        //there is one intersection
        else if(delta==0)
        {
            Vector amount=new Vector(ray.getDirection());
            amount.scale(-B);
            Vector point=new Vector(ray.getPoo());
            point.add(amount);
            list.add(point.getHead());
            return list;
        }
        
        //there are 2 intersecftions
        double t1=-B+Math.sqrt(B*B-4*A*C)/2*A;
        
        double t2=-B-Math.sqrt(B*B-4*A*C)/2*A;
        
        
            Vector amount=new Vector(ray.getDirection());
            amount.scale(t1);
            Vector point=new Vector(ray.getPoo());
            point.add(amount);
            list.add(point.getHead());
           
            Vector amount2=new Vector(ray.getDirection());
            amount2.scale(t2);
            Vector point2=new Vector(ray.getPoo());
            point2.add(amount2);
            list.add(point2.getHead());
            
            return list;
      
    }
}
