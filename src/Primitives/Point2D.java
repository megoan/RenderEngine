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
public class Point2D implements Comparable {

    private Coordinate x;
    private Coordinate y;

    public Coordinate getX() {
        return x;
    }

    public void setX(Coordinate x) {
        this.x = x;
    }

    public Coordinate getY() {
        return y;
    }

    public void setY(Coordinate y) {
        this.y = y;
    }

    public Point2D() {
        this.x = new Coordinate();
        this.y = new Coordinate();
    }

    public Point2D(Coordinate x, Coordinate y) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
    }

    public Point2D(Point2D copy) {
        this.x = new Coordinate(copy.x.getCoordinate());
        this.y = new Coordinate(copy.y.getCoordinate());
    }

    public void add(Vector other) {
        x.add(other.getHead().getX());
        y.add(other.getHead().getY());
    }

    public void subtract(Vector other) {
        x.subtract(other.getHead().getX());
        y.subtract(other.getHead().getY());
    }

    @Override
    public String toString() {
        return "x:" + x + " y:" + y;
    }

    @Override
    public int compareTo(Object other) {
        return ((Point2D) other).x.compareTo(x) + ((Point2D) other).y.compareTo(y);
    }

}
