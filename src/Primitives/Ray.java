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
public class Ray implements Comparable<Ray> {

    private Point3D poo;
    private Vector direction;
    private double indexOfRefraction=1;

    public double getIndexOfRefraction() {
        return indexOfRefraction;
    }

    public void setIndexOfRefraction(double indexOfRefraction) {
        this.indexOfRefraction = indexOfRefraction;
    }

    public Point3D getPoo() {
        return new Point3D(poo);
    }

    public void setPoo(Point3D poo) {
        this.poo = new Point3D(poo);
    }

    public Vector getDirection() {
        return new Vector(direction);
    }

    public void setDirection(Vector direction) {
        this.direction = new Vector(direction);
    }

    public Ray() {
        this.poo=new Point3D();
        this.direction=new Vector();
    }

    public Ray(Point3D poo, Vector direction) {
        this.poo = new Point3D(poo);
        this.direction = new Vector(direction);
    }
    public Ray(Point3D poo, Vector direction, double index) {
        this.poo = new Point3D(poo);
        this.direction = new Vector(direction);
        this.indexOfRefraction=index;
    }

    public Ray(Ray copy) {
        this.direction = new Vector(copy.direction);
        this.poo = new Point3D(copy.poo);
        this.indexOfRefraction=copy.indexOfRefraction;
    }

    @Override
    public String toString() {
        return "point: " + poo + " direction: " + direction;
    }

    @Override
    public int compareTo(Ray other) {
        return other.poo.compareTo(poo)+other.direction.compareTo(direction);       
    }

}
