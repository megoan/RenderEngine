/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderengine;
import Elements.DirectionalLight;
import Elements.SpotLight;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.Render;
import Renderer.ImageWriter;
import Scene.Scene;
import java.awt.Color;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author User
 */
public class RenderEngine {

    /**
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SAXException, IOException {
         Scene scene = new Scene();
        scene.setScreenDistance(200);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(
                new Point3D(-250, -150, -260),
                new Point3D(-250, -250, -260),new Point3D(-150, -250, -260));

        triangle.setEmmission(new Color(0, 0, 100));
        triangle.setShininess(4);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -200),
                new Vector(0, 0, -1), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("test6_spot_light_sphere_shadow", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
}
