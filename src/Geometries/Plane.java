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
public class Plane extends Geometry implements FlatGeometry{

    private Point3D q;
    private Vector n;

    public Point3D getQ() {
        return new Point3D(q);
    }

    public void setQ(Point3D q) {
        this.q = new Point3D(q);
    }

    public Vector getN() {
        return new Vector(n);
    }

    public void setN(Vector n) {
        this.n = new Vector(n);
    }

    public Plane() {
        q = new Point3D();
        n = new Vector();
    }

    public Plane(Point3D q, Vector n) {
        this.q = new Point3D(q);
        this.n = new Vector(n);
    }

    public Plane(Plane copy) {       
        this.q = new Point3D(copy.q);
        this.n = new Vector(copy.n);
        super.setEmmission(new Color(copy.getEmmission().getRGB()));
        super.setShininess(copy.getShininess());
        super.setMaterial(new Material(copy.getMaterial()));
        super.setShadow(copy.isShadow());
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector normal = new Vector(n);
        normal.normalize();
        return normal;
    }
/**
 * 
 * @param ray
 * @return 
 */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList list = new ArrayList();
        Vector v = new Vector(ray.getPoo());
        Vector direction=new Vector(ray.getDirection());
        v.subtract(new Vector(q));
        double f = n.dotProduct(direction);
        if (f != 0) {
            double t = -(n.dotProduct(v) / f);
            if (t > 0) {
                direction.scale(t);
                Point3D p = new Point3D(ray.getPoo());
                p.add(direction);
                list.add(p);
            }
        }
        return list;
    }

}


