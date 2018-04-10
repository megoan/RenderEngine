/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.LightSource;
import Geometries.Geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author shmuel
 */
public class Scene {

    private String sceneName;
    private Color background;
    private AmbientLight ambientLight;
    public List<LightSource> lights;
    public List<Geometry> geometries;
    private Camera camera;
    private double screenDistance;

    
    /*constructors*/
    public Scene(String sceneName, Color background, AmbientLight ambientLight, Camera camera, double screenDistance) {
        this.sceneName = sceneName;
        this.background = new Color(background.getRGB());
        this.ambientLight = new AmbientLight(ambientLight);
        this.camera = new Camera(camera);
        this.screenDistance = screenDistance;
        geometries = new ArrayList<>();
        lights = new ArrayList<>();
    }

    public Scene() {
        this.sceneName = "render";
        this.background = new Color(0,0,0);
        this.ambientLight = new AmbientLight(255,255,255);
        this.camera = new Camera();
        this.screenDistance = 100;
        geometries = new ArrayList<>();
        lights = new ArrayList<>();
    }

    public Scene(Scene scene) {
        this.sceneName = scene.sceneName;
        this.background = new Color(scene.background.getRGB());
        this.ambientLight = new AmbientLight(scene.ambientLight);
        this.camera = new Camera(scene.getCamera());
        this.screenDistance = scene.screenDistance;
        this.geometries = scene.geometries;
        this.lights = scene.lights;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Color getBackground() {
        return new Color(background.getRGB());
    }

    public void setBackground(Color background) {
        this.background = new Color(background.getRGB());
    }

    public AmbientLight getAmbientLight() {
        return new AmbientLight(ambientLight);
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = new AmbientLight(ambientLight);
    }

    public Camera getCamera() {
        return new Camera(camera);
    }

    public void setCamera(Camera camera) {
        this.camera = new Camera(camera);
    }

    public double getScreenDistance() {
        return screenDistance;
    }

    public void setScreenDistance(double screenDistance) {
        this.screenDistance = screenDistance;
    }

    public void addGeometry(Geometry geometry) {
        geometries.add(geometry);
    }

    public Iterator<Geometry> getGeometriesIterator() {
        return geometries.iterator();
    }
    public Iterator<LightSource> getLightSourceIterator() {
        return lights.iterator();
    }
    public void addLight(LightSource light) {
        lights.add(light);
    }
    
    
    //background effects
    
    //top is one color and the buttom another
    public Color gradiant(int i,int height, Color top, Color buttom)
    {
        int R;
        int G;
        int B;
        double factor=(double)i/height; 
        R=(int) Math.min(255,(factor*buttom.getRed()+(1-factor)*top.getRed()));
        G=(int) Math.min(255,(factor*buttom.getGreen()+(1-factor)*top.getGreen()));
        B=Math.min(255,(int) (factor*buttom.getBlue()+(1-factor)*top.getBlue()));
        return new Color(R,G,B);
    }
    
    //top and buttom is one color and the middle another
    public Color gradiantToMiddle(int i,int height, Color outer, Color middle)
    {
        int R;
        int G;
        int B;
        
        double factor;
        if(i<height)factor=(double)i/height; 
        else factor=(double)height/i; 
        R=(int) Math.min(255,(factor*middle.getRed()+(1-factor)*outer.getRed()));
        G=(int) Math.min(255,(factor*middle.getGreen()+(1-factor)*outer.getGreen()));
        B=Math.min(255,(int) (factor*middle.getBlue()+(1-factor)*outer.getBlue()));
        return new Color(R,G,B);
    }
    
    
    //middle of screen is one color and the out side is another
    public Color gradiantSphere(int i, int j, int width, int height, Color rim, Color center)
    {
        int middle_x=width/2;
        int middle_y=height/2;
        double largestDistance=distance(0, 0, middle_x, middle_y);
        double nowDistance=distance(i, j, middle_x, middle_y);       
        int R;
        int G;
        int B;
        double factor=(double)nowDistance/largestDistance; 
        R=(int) Math.min(255,(factor*rim.getRed()+(1-factor)*center.getRed()));
        G=(int) Math.min(255,(factor*rim.getGreen()+(1-factor)*center.getGreen()));
        B=Math.min(255,(int) (factor*rim.getBlue()+(1-factor)*center.getBlue()));
        return new Color(R,G,B);
    }
    
    //helper function to find the distance between 2 coordinates
    public double distance(int x, int y, int xx, int yy)
    {
        return Math.sqrt((Math.pow(x-xx,2))+(Math.pow(y-yy,2)));
    }
}
