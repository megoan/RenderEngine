/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

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
public abstract class OBJPolygon extends Geometry{   
    
    public   ArrayList<Point3D> MainoundingBox;
    @Override
    public abstract List<Point3D> findIntersections(Ray ray); 
    
    @Override
    public abstract Vector getNormal(Point3D p); 
    
    public  ArrayList<Point3D> getMinMaxPoints(){return new ArrayList<>();}
    
    public abstract void move(double x,double y,double z);
    
    public abstract boolean intersectsBoundinBox(Ray ray);
    
    
    private Color gradiant1;
    private Color gradiant2;
    public Color getGradiant1() {
        return new Color(gradiant1.getRGB());
    }

    public void setGradiant1(Color gradiant1) {
        this.gradiant1 = new Color(gradiant1.getRGB());
    }

    public Color getGradiant2() {
        return new Color(gradiant2.getRGB());
    }

    public void setGradiant2(Color gradiant2) {
        this.gradiant2 = new Color(gradiant2.getRGB());
    }

      public Color gradiantEmission(Point3D point)
    {
        ArrayList<Point3D> list=new ArrayList<>(MainoundingBox);
        
        double top=list.get(1).getY().getCoordinate()-point.getY().getCoordinate();
        double buttom=-list.get(0).getY().getCoordinate()+point.getY().getCoordinate();
        double factor=top/(top+buttom);
        int R=(int) (gradiant1.getRed()*factor+gradiant2.getRed()*(1-factor));
        int G=(int) (gradiant1.getGreen()*factor+gradiant2.getGreen()*(1-factor));
        int B=(int) (gradiant1.getBlue()*factor+gradiant2.getBlue()*(1-factor));
        return new Color(R,G,B);       
    }  
}
