/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import Primitives.Point3D;
import java.awt.Color;

/**
 *
 * @author shmuel
 */
public abstract class Light {

    protected Color color;
    protected boolean shadow;

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }
   

// ***************** Constructors ********************** // 
    public Light() {
       
    }
    
    public Light(Color color) {
        this.color = new Color(color.getRGB());
    }

// ***************** Getters/Setters ********************** // 
    public Color getIntensity() {
        return new Color(color.getRGB());
    } 
}
