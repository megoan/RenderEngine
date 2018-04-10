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
public class DirectionalLight extends Light implements LightSource{
    private Vector direction;   

// ***************** Constructors ********************** //  
    public DirectionalLight(Color color, Vector direction)
    {
        this.color=new Color(color.getRGB());
        this.direction=new Vector(direction);
        this.direction.normalize();
        this.shadow=true;
    }     
// ***************** Getters/Setters ********************** //   

    public DirectionalLight(Color color, Vector direction,boolean shadow) {
        this.color=new Color(color.getRGB());
        this.direction=new Vector(direction);
        this.direction.normalize();
        this.shadow=shadow;
    }
    
    public Vector getDirection() {
        return new Vector(direction);
    }
// ***************** Getters/Setters ********************** //
    public void setDirection(Vector _direction) {
        this.direction = new Vector(direction);
        this.direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D point){return new Color(color.getRGB());}
    
    @Override
    public Vector getL(Point3D point){return new Vector(direction);}
    
    
}
