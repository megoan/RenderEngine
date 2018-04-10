/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.awt.Color;
import java.util.Map;

/**
 *
 * @author shmuel
 */
public class AmbientLight extends Light{
    private final double _Ka = 0.1;
// ***************** Constructors ********************** // 
    public AmbientLight(){}
    public AmbientLight(AmbientLight aLight)
    {
        color=new Color(aLight.color.getRGB());
    }
    public AmbientLight(int r, int g, int b)
    {
     color=new Color(r,g,b);   
    }
    public AmbientLight(Map<String, String> attributes){}; 
    
    
 
// ***************** Getters/Setters ********************** //
    @Override
    public Color getIntensity() {
        int r=color.getRed();
        int g=color.getGreen();
        int b=color.getBlue();
        r*=_Ka;
        g*=_Ka;
        b*=_Ka;
        return new Color(r,g,b); //To change body of generated methods, choose Tools | Templates.
    }
}
