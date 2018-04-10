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
public class Coordinate implements Comparable<Coordinate> {

    private double coordinate;

    public double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate() {
        coordinate=0;
    }

    public Coordinate(double coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate(Coordinate copy) {
        this.coordinate = copy.getCoordinate();
    }

    @Override
    public String toString() {
        return Double.toString(coordinate);
    }

    @Override
    public int compareTo(Coordinate other) {
        if (this.coordinate == other.coordinate)
            return 0;
        return 1;
    }

    public void add(Coordinate other) {
        this.coordinate += other.coordinate;
    }

    public void subtract(Coordinate other) {
        this.coordinate -= other.coordinate;
    }
}
