/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;
import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;

/**
 *
 * @author shmuel
 */
public class PointLight extends Light implements LightSource {

    double _Kc, _Kl, _Kq;

//only for soft shadows!!!  
    protected double area;
    protected Point3D _position;
    
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Point3D getPosition() {
        return new Point3D(_position);
    }

    public void setPosition(Point3D _position) {
        this._position = new Point3D(_position);
    }

    // ***************** Constructors ********************** //  
    public PointLight(Color color, Point3D position, double kc, double kl, double kq) {
        this.color = new Color(color.getRGB());
        this._position = new Point3D(position);
        this._Kc = kc;
        this._Kl = kl;
        this._Kq = kq;
        area=1;
        shadow=true;
    }
    public PointLight(Color color, Point3D position,double area, double kc, double kl, double kq,boolean  shadow) {
        this.color = new Color(color.getRGB());
        this._position = new Point3D(position);
        this.area=area;
        this._Kc = kc;
        this._Kl = kl;
        this._Kq = kq;
        this.shadow=shadow;
    }

// ***************** Getters/Setters ********************** // 
    @Override
    public Color getIntensity(Point3D point) {
        double distance = point.distance(_position);
        double factor=_Kc + _Kl * distance + _Kq * distance * distance;
        factor=Math.max(1, factor);
        
        int r = Math.min(255,(int) (color.getRed() / factor));
        int g = Math.min(255,(int) (color.getGreen() / factor));
        int b = Math.min(255,(int) (color.getBlue() / factor));       
        return new Color(r,g,b);
    }

    @Override
    public Vector getL(Point3D point) {
        Point3D point2=new Point3D(point);
        Vector temp=new Vector(_position);
        point2.subtract(temp);
        Vector v=new Vector(point2);
        v.normalize();
        return v;
    }
}
