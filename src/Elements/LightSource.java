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
public interface LightSource {
  public abstract Color getIntensity(Point3D point); 
  public abstract Vector getL(Point3D point); // light to point vector   
    
}
