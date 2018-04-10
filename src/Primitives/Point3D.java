/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Primitives;

/**
 *
 * @author User
 */
public class Point3D extends Point2D {

    private Coordinate z;

    public Coordinate getZ() {
        return z;
    }

    public void setZ(Coordinate z) {
        this.z = z;
    }

    public Point3D() {
        this.z=new Coordinate();
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.z = new Coordinate(z);
    }
    public Point3D(double x, double y, double z) {
        super(new Coordinate(x),new Coordinate(y));
        this.z = new Coordinate(z);
    }

    public Point3D(Point3D copy) {
        super(copy);
        this.z = new Coordinate(copy.z);
    }

    @Override
    public void add(Vector other) {
        getX().add(other.getHead().getX());
        getY().add(other.getHead().getY());
        z.add(other.getHead().z);
    }

    
    @Override
    public void subtract(Vector other) {
        getX().subtract(other.getHead().getX());
        getY().subtract(other.getHead().getY());
        z.subtract(other.getHead().z);   
    }
    public double distance(Point3D point)
    {
        return Math.sqrt(Math.pow((this.getX().getCoordinate()-point.getX().getCoordinate()), 2)+Math.pow(this.getY().getCoordinate()-point.getY().getCoordinate(), 2)+Math.pow(this.getZ().getCoordinate()-point.getZ().getCoordinate(), 2));
    }
    @Override
    public String toString() {
        return super.toString() + " z:" + z;
    }

    @Override
    public int compareTo(Object other) {
        return super.compareTo(other)+((Point3D)other).z.compareTo(z);
    }

    
    
}
