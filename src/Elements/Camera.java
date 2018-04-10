/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Vector;
import Primitives.Ray;

/**
 *
 * @author Shmuel & Elyasaf 
 * this class represents the scene's camera
 *
 * Variables included: 
 * PO (Point3D): the position of the camera 
 * vUp (Vector): the up direction of the camera 
 * vTo (Vector): the towards direction of the camera 
 * vRight (Vector): the right direction of the camera
 *
 * functions included: getNormal: return the planes normal Findintersections: return intersection of ray with plane
 */
public class Camera {

    private Point3D P0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

    // ***************** Constructors ********************** //
    public Camera() {
        this.P0 = new Point3D();
        this.vUp = new Vector(new Point3D(new Coordinate(), new Coordinate(1), new Coordinate()));
        this.vTo = new Vector(new Point3D(new Coordinate(), new Coordinate(), new Coordinate(-1)));
        this.vRight = new Vector(new Point3D(new Coordinate(1), new Coordinate(), new Coordinate()));
    }

    public Camera(Point3D P0, Vector vUp, Vector vTo) {
        this.P0 = new Point3D(P0);
        this.vUp = new Vector(vUp);
        this.vTo = new Vector(vTo);
        this.vRight = new Vector(vTo.crossProduct(vUp));
    }

    public Camera(Camera copy) {
        this.P0 = new Point3D(copy.P0);
        this.vUp = new Vector(copy.vUp);
        this.vTo = new Vector(copy.vTo);
        this.vRight = new Vector(vTo.crossProduct(vUp));
    }
    // ***************** Getters/Setters ********************** //

    public Point3D getP0() {
        return new Point3D(P0);
    }

    public void setP0(Point3D P0) {
        this.P0 = new Point3D(P0);
    }

    public Vector getvUp() {
        return new Vector(vUp);
    }

    public void setvUp(Vector vUp) {
        this.vUp = new Vector(vUp);
    }

    public Vector getvTo() {
        return new Vector(vTo);
    }

    public void setvTo(Vector vTo) {
        this.vTo = new Vector(vTo);
    }

    public Vector getvRight() {
        return new Vector(vRight);
    }

    public void setvRight(Vector vRight) {
        this.vRight = new Vector(vRight);
    }

    /**
     *
     * @param Nx - number of pixels from right to left
     * @param Ny - number of pixels from up to down
     * @param x - the index of pixel in the line
     * @param y - the index of pixel in column
     * @param screenDistance - the distance between the camera and the screen view
     * @param screenWidth - the screen width
     * @param screenHeight - the screen height
     * @return
     */
    public Ray constructRayThroughPixel(int Nx, int Ny, double x, double y, double screenDistance, double screenWidth, double screenHeight) {
        //if(Nx>0 && Ny>0){
        double Rx = screenWidth / Nx;
        double Ry = screenHeight / Ny;
        Point3D Pc = new Point3D(P0);
        Vector vRtemp = new Vector(vRight);
        Vector vU = new Vector(vUp);
        Vector vT = new Vector(vTo);
        vT.scale(screenDistance);
        Pc.add(vT);

        vRtemp.scale((x - Nx / 2.0) * Rx + Rx / 2);
        vU.scale((y - Ny / 2.0) * Ry + Ry / 2);
        vRtemp.subtract(vU);
        Pc.add(vRtemp);
        Vector temp = new Vector(P0);
        Point3D p11 = new Point3D(Pc);
        Pc.subtract(temp);
        Vector rayVector = new Vector(Pc);
        rayVector.normalize();
        return new Ray(new Point3D(p11), rayVector);
        //}
    }

    @Override
    public String toString() {
        return "camera origin: " + P0 + " up Vector: " + vUp + " right vector: " + vRight + " fowards vector: " + vTo;
    }

}
