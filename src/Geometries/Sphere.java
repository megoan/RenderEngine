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
public class Sphere extends RadialGeometry {

    private Point3D center;

    public Point3D getCenter() {
        return new Point3D(center);
    }

    public void setCenter(Point3D center) {
        this.center = new Point3D(center);
    }

    public Sphere() {
        this.center=new Point3D();
    }

    public Sphere( double radius, Point3D center) {
        super(radius);
        this.center = new Point3D(center);
    }

    public Sphere(Sphere copy) {
        this.radius=copy.radius;
        this.center = new Point3D(copy.getCenter());
        super.setEmmission(new Color(copy.getEmmission().getRGB()));
        super.setShininess(copy.getShininess());
        super.setMaterial(new Material(copy.getMaterial()));
        super.setShadow(copy.isShadow());
    }

    @Override
    public String toString() {
        return "radious: "+radius+" center: "+center;
    }

    
    //gets normal of sphere at point
    @Override
    public Vector getNormal(Point3D p) {
        Point3D po=new Point3D(p);
        Vector temp=new Vector(center);
        po.subtract(temp);
        Vector v=new Vector(po);     
        v.normalize();
        return new Vector(v);
    }
/**
 * 
 *
 * @param ray
 * @return 
 */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> list =new ArrayList<>();
        Vector L=new Vector(center);
        L.subtract(new Vector(ray.getPoo()));
        double tm=L.dotProduct(ray.getDirection());
        double d=Math.sqrt(L.dotProduct(L)-tm*tm);
        if(d>radius)return list;
        double th=Math.sqrt(radius*radius-d*d);
        double t1=tm-th;
        double t2=tm+th;//המצלמה עלולה להמצא בנקודה אחרת ולכן בודקים את החיתוך עם כל נקודות השפה
        Vector V=new Vector(ray.getDirection());
        if(t1>=0){
        Point3D p1=new Point3D(ray.getPoo());       
        V.scale(t1);
        p1.add(V);
        list.add(p1);}
        if(t2>=0){//למרות שאין טעם לבדוק את הנקודה הקודמת אם הנוכחיצ לא מתאימה כך הקוד יותר קריא
        Point3D p2=new Point3D(ray.getPoo());
        V=new Vector(ray.getDirection());
        V.scale(t2);
        p2.add(V);  
        list.add(p2);}
        return list;
    }

}
