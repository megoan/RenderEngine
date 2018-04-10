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
public class Vector implements Comparable<Vector> {

    private Point3D head;

    public Vector(Point3D point, Point3D po) {
        this.head = new Point3D(point);
        Vector temp=new Vector(po);
        this.head.subtract(temp);
        this.normalize();

    }

    public Point3D getHead() {
        return new Point3D(head);
    }

    public void setHead(Point3D head) {
        this.head = new Point3D(head);
    }

    public Vector() {
        this.head = new Point3D();
    }

    public Vector(Point3D head) {
        this.head = new Point3D(head);
    }
    public Vector(double x, double y, double z) {
        this.head = new Point3D(x,y,z);
    }

    public Vector(Vector copy) {
        this.head = new Point3D(copy.head);
    }

    @Override
    public String toString() {
        return head.toString();
    }

    @Override
    public int compareTo(Vector other) {
        return other.head.compareTo(head);
    }

    public void add(Vector other) {
        this.head.add(other);
    }

    public void subtract(Vector other) {
        this.head.subtract(other);
    }

    public void scale(double scale) {
        head.getX().setCoordinate(head.getX().getCoordinate() * scale);
        head.getY().setCoordinate(head.getY().getCoordinate() * scale);
        head.getZ().setCoordinate(head.getZ().getCoordinate() * scale);
    }

    public double dotProduct(Vector other) {
        return head.getX().getCoordinate() * other.head.getX().getCoordinate() + head.getY().getCoordinate() * other.head.getY().getCoordinate() + head.getZ().getCoordinate() * other.head.getZ().getCoordinate();
    }

    public double length() {
        return Math.sqrt(Math.pow(head.getX().getCoordinate(), 2) + Math.pow(head.getY().getCoordinate(), 2) + Math.pow(head.getZ().getCoordinate(), 2));
    }

    public void normalize() {
        double lengthT = this.length();
        if (lengthT == 0) {
            return;//can't divide by 0
        }
        this.head.getX().setCoordinate(this.head.getX().getCoordinate() / lengthT);
        this.head.getY().setCoordinate(this.head.getY().getCoordinate() / lengthT);
        this.head.getZ().setCoordinate(this.head.getZ().getCoordinate() / lengthT);
    }

    public Vector crossProduct(Vector other) {
        double x1, y1, z1, x2, y2, z2, newX, newY, newZ;
        x1 = this.head.getX().getCoordinate();
        x2 = other.head.getX().getCoordinate();
        y1 = this.head.getY().getCoordinate();
        y2 = other.head.getY().getCoordinate();
        z1 = this.head.getZ().getCoordinate();
        z2 = other.head.getZ().getCoordinate();
        newX = y1 * z2 - z1 * y2;
        newY = -(x1 * z2 - z1 * x2);
        newZ = x1 * y2 - y1 * x2;
        Vector newVector = new Vector(new Point3D(new Coordinate(newX), new Coordinate(newY), new Coordinate(newZ)));
        return newVector;
    }

}
