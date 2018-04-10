/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

import Primitives.Point3D;
import Primitives.Vector;

/**
 *
 * @author User
 */
public class OBJTriangleSmooth extends OBJTriangle{
    private Vector vertxVector1;
    private Vector vertxVector2;
    private Vector vertxVector3;
    private Vector faceNormal;

    public Vector getFaceNormal() {
        return new Vector(faceNormal);
    }

    public void setFaceNormal(Vector faceNormal) {
        this.faceNormal = new Vector(faceNormal);
    }

    public Vector getVertxVector1() {
        return new Vector(vertxVector1);
    }

    public void setVertxVector1(Vector vertxVector1) {
        this.vertxVector1 = new Vector(vertxVector1);
    }

    public Vector getVertxVector2() {
        return new Vector(vertxVector2);
    }

    public void setVertxVector2(Vector vertxVector2) {
        this.vertxVector2 = new Vector(vertxVector2);
    }

    public Vector getVertxVector3() {
        return new Vector(vertxVector3);
    }

    public void setVertxVector3(Vector vertxVector3) {
        this.vertxVector3 = new Vector(vertxVector3);
    }

    public OBJTriangleSmooth(Vector vertxVector1, Vector vertxVector2, Vector vertxVector3, Point3D p1, Point3D p2, Point3D p3, Vector normal) {
        super(p1, p2, p3, normal);
        this.vertxVector1 = new Vector(vertxVector1);
        this.vertxVector2 = new Vector(vertxVector2);
        this.vertxVector3 = new Vector(vertxVector3);
        this.faceNormal=new Vector(super.getFaceNormal(p3));

    }

    public OBJTriangleSmooth() {
    }

    public OBJTriangleSmooth(OBJTriangleSmooth copy) {
        super(copy);
        this.vertxVector1 = new Vector(copy.getVertxVector1());
        this.vertxVector2 = new Vector(copy.getVertxVector2());
        this.vertxVector3 = new Vector(copy.getVertxVector3());
         this.faceNormal=new Vector(super.getFaceNormal(copy.getP3()));
    }

    @Override
    public Vector getNormal(Point3D p) {
        double bla=0;
        Point3D p1=getP1();
        Point3D p2=getP2();
        Point3D p3=getP3();
                      
        Vector v1=new Vector(p1);
        Vector v2=new Vector(p2);
        Vector v3=new Vector(p3);
        v1.subtract(v2);
        v3.subtract(v2);
        Vector cross=new Vector(v3.crossProduct(v1));
        double totalArea=cross.length()/2;
        
        Vector midPoint=new Vector(p);
        v1=new Vector(p1);
        v2=new Vector(p2);
        v3=new Vector(p3);
               
        v1.subtract(midPoint);
        v2.subtract(midPoint);
        v3.subtract(midPoint);
      
        double Area1=(v2.crossProduct(v3).length()/2);
        double Area2=(v1.crossProduct(v3).length()/2);
        double Area3=(v1.crossProduct(v2).length()/2);
        
        Area1/=totalArea;
        Area2/=totalArea;
        Area3/=totalArea;
                         
        Vector vx1=new Vector(vertxVector1);
        Vector vx2=new Vector(vertxVector2);
        Vector vx3=new Vector(vertxVector3);
        vx1.scale(Area1);
        vx2.scale(Area2);
        vx3.scale(Area3);
        
        Vector normalVector=new Vector();
        normalVector.add(vx1);
        normalVector.add(vx2);
        normalVector.add(vx3);
        normalVector.normalize();
        if(normalVector.compareTo(new Vector())==0)
        {
            bla++;
        }
        return normalVector;
    }
    
   @Override
   public Vector getFaceNormal(Point3D p)
   {
       return new Vector(faceNormal);
   }
    
    
    
    
}
