/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grid;

import Geometries.Geometry;
import Primitives.Point3D;
import Primitives.Ray;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class BoundingBox {
   private ArrayList<Point3D> boundingBox;
   private ArrayList<Geometry> geometries;

    public BoundingBox(ArrayList<Point3D> boundingBox) {
        this.boundingBox = boundingBox;
    }

    public BoundingBox() {
    }
    
    public BoundingBox(Point3D min,Point3D max) {
        boundingBox.clear();
        boundingBox.add(min);
        boundingBox.add(max);
    }
      

    public ArrayList<Geometry> getGeometries() {
        return geometries;
    }

    public void setGeometries(ArrayList<Geometry> geometries) {
        this.geometries = geometries;
    }

    public ArrayList getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(ArrayList boundingBox) {
        this.boundingBox = boundingBox;
    }
   
   public boolean rayIntersectsBox(Ray ray)
   {
       ArrayList<Point3D> list = boundingBox;

        double t1 = (list.get(0).getX().getCoordinate() - ray.getPoo().getX().getCoordinate()) * (1 / ray.getDirection().getHead().getX().getCoordinate());
        double t2 = (list.get(1).getX().getCoordinate() - ray.getPoo().getX().getCoordinate()) * (1 / ray.getDirection().getHead().getX().getCoordinate());
        double t3 = (list.get(0).getY().getCoordinate() - ray.getPoo().getY().getCoordinate()) * (1 / ray.getDirection().getHead().getY().getCoordinate());
        double t4 = (list.get(1).getY().getCoordinate() - ray.getPoo().getY().getCoordinate()) * (1 / ray.getDirection().getHead().getY().getCoordinate());
        double t5 = (list.get(0).getZ().getCoordinate() - ray.getPoo().getZ().getCoordinate()) * (1 / ray.getDirection().getHead().getZ().getCoordinate());
        double t6 = (list.get(1).getZ().getCoordinate() - ray.getPoo().getZ().getCoordinate()) * (1 / ray.getDirection().getHead().getZ().getCoordinate());

        double tmin = max(max(min(t1, t2), min(t3, t4)), min(t5, t6));
        double tmax = min(min(max(t1, t2), max(t3, t4)), max(t5, t6));

        // if tmax < 0, ray (line) is intersecting AABB, but whole AABB is behing us
        if (tmax < 0) {
            return false;
        }

// if tmin > tmax, ray doesn't intersect AABB
        if (tmin > tmax) {
            return false;
        }
        return true;
   }
   
   public boolean boxIntersectsBox(BoundingBox other)
   {
       return !(this.boundingBox.get(0).getX().getCoordinate()>other.boundingBox.get(1).getX().getCoordinate() 
               || 
               this.boundingBox.get(0).getY().getCoordinate()>other.boundingBox.get(1).getY().getCoordinate()
               ||
               this.boundingBox.get(0).getZ().getCoordinate()>other.boundingBox.get(1).getZ().getCoordinate()
               
               ||
               
               this.boundingBox.get(1).getX().getCoordinate()<other.boundingBox.get(0).getX().getCoordinate() 
               || 
               this.boundingBox.get(1).getY().getCoordinate()<other.boundingBox.get(0).getY().getCoordinate()
               ||
               this.boundingBox.get(1).getZ().getCoordinate()<other.boundingBox.get(0).getZ().getCoordinate()
               );
   }
   
   public void addGeometriesToBox(BoundingBox other)
   {
       if(boxIntersectsBox(other))
       {
           geometries.addAll(other.geometries);
       }
   }
    
}
