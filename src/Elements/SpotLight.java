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
public class SpotLight extends PointLight {
    
    private Vector _direction;

// ***************** Constructor ********************** // 
    public SpotLight(Color color, Point3D position, Vector direction, double kc, double kl, double kq) {
        super(color, position,1, kc, kl, kq,true);
        this._direction = new Vector(direction);
        _direction.normalize();
    }

    public SpotLight(Color color, Point3D position, double area, Vector direction, double kc, double kl, double kq,boolean shadow) {
        super(color, position, area, kc, kl, kq,shadow);
        this._direction = new Vector(direction);
        _direction.normalize();
    }

// ***************** Getters/Setters ********************** //
    @Override
    public Color getIntensity(Point3D point) {
        _direction.normalize();
        Color c=super.getIntensity(point);
        Vector D = new Vector(getL(point));
        D.normalize();      
        double dotP = D.dotProduct(_direction);

        dotP = Math.abs(dotP);       
        int r = Math.min(255,(int) ((c.getRed() * dotP) ));
        int g = Math.min(255,(int) ((c.getGreen() * dotP) ));
        int b = Math.min(255,(int) ((c.getBlue() * dotP) )); 
        return new Color(r, g, b);
    }

    

}
