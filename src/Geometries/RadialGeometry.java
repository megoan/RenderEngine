/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

/**
 *
 * @author User
 */
abstract public class RadialGeometry extends Geometry{

    protected double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public RadialGeometry() {
    }

    public RadialGeometry(double radius) {
        this.radius = radius;
    }

}
