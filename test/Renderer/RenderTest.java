/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Cylinder;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Scene.Scene;
import java.awt.Color;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author shmuel
 */
public class RenderTest {

    public RenderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//   
    /**
     * ambiant_light+emission-Test
     */
    @Test
    public void test1_emission() throws Exception {
        Render render = new Render(1, "./src/sceneTest2.xml");
        render.imageWriter = new ImageWriter("test1_emission", 500, 500, 500, 500);
        render.renderImage();
        render.printGrid(50);
        render.writeToImage();
    }

    /**
     * sceneTestPoint_Tri
     *
     * @throws java.lang.Exception
     */
    @Test
    public void test2_point_light_triangle() throws Exception {
        Render render = new Render(1, "./src/sceneTestPoint_Tri.xml");
        render.imageWriter = new ImageWriter("test2_point_light_triangle", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * sceneTestSpot_Tri_1
     */
    @Test
    public void test3_spot_light_triangle() throws Exception {
        Render render = new Render(1, "./src/sceneTestSpot_Tri_1.xml");
        render.imageWriter = new ImageWriter("test3_spot_light_triangle", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * pointlight
     */
    @Test
    public void test4_point_light_sphere() throws Exception {
        Render render = new Render(1, "./src/sceneTestPoint.xml");
        render.imageWriter = new ImageWriter("test4_point_light_sphere", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * sceneTestSpot1
     */
    @Test
    public void test5_spot_light_sphere_shadow() throws Exception {
        Render render = new Render(1, "./src/sceneTestSpot.xml");
        render.imageWriter = new ImageWriter("test5_spot_light_sphere", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void spotLightTest2() throws SAXException, IOException {

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

    /**
     * sceneTest_Shadow
     */
    @Test
    public void test7_sphere_triangles_shadow() throws Exception {
        Render render = new Render(1, "./src/sceneTest_Shadow.xml");
        render.imageWriter = new ImageWriter("test7_sphere_triangles_shadow", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
        /**
     * sceneTest_Shadow
     */
    @Test
    public void test8_sphere_triangles_shadow() throws Exception {
        Render render = new Render(1, "./src/sceneTest_Shadow_1.xml");
        render.imageWriter = new ImageWriter("test8_sphere_triangles_shadow", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
    
    /**
     * sceneTest_Shadow
     *
     * @throws java.lang.Exception
     */
    @Test
    public void test9_sphere_triangles_shadow() throws Exception {
        Render render = new Render(1, "./src/sceneTest_Shadow_2.xml");
        render.imageWriter = new ImageWriter("test9_sphere_triangles_shadow", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
    /**
     * sceneTestShadow
     */
    @Test
    public void test10_sphere_plane_shadow() throws Exception {
        Render render = new Render(1, "./src/sceneTestShadow.xml");
        render.imageWriter = new ImageWriter("test10_sphere_plane_shadow", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
    
        @Test
    public void test11_sphere_in_sphere_transparent() throws SAXException, IOException {
        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        sphere.setKt(0.8);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
        sphere2.setShininess(20);
        sphere2.setEmmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("test11_sphere_in_sphere_transparent", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    
        @Test
    public void test12_spheres_triangles_reflection() throws SAXException, IOException {

        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
        sphere2.setShininess(20);
        sphere2.setEmmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(200, 200, -375));

        Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(-1500, -1500, -1500));

        triangle.setEmmission(new Color(20, 20, 20));
        triangle2.setEmmission(new Color(20, 20, 20));
        triangle.setKr(1);
        triangle2.setKr(0.5);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("test12_spheres_triangles_reflection", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
     /**
     * sceneTestSpot_Tri_1
     */
    @Test
    public void tes13_supersampling_off() throws Exception {
        Render render = new Render(1, "./src/sceneTest_sampling.xml");
        render.imageWriter = new ImageWriter("test13_supersampling_off", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
     /**
     * sceneTestSpot_Tri_1
     */
    @Test
    public void tes14_sampling_on() throws Exception {
        Render render = new Render(5, "./src/sceneTest_sampling.xml");
        render.imageWriter = new ImageWriter("test13_supersampling_on", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

     /**
     * sceneTest_soft_Shadows
     */
    @Test
    public void test15_soft_shadows_none() throws Exception {
        Render render = new Render(1, "./src/sceneTest_soft_Shadows.xml");
        render.imageWriter = new ImageWriter("test15_soft_shadows_none", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
     /**
     * sceneTest_soft_Shadows
     */
    @Test
    public void test16_soft_shadows_a_little() throws Exception {
        Render render = new Render(5, "./src/sceneTest_soft_Shadows_3.xml");
        render.imageWriter = new ImageWriter("test16_soft_shadows_a_little", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
    /**
     * sceneTest_soft_Shadows
     */
    @Test
    public void test17_soft_shadows_a_lot() throws Exception {
        Render render = new Render(5, "./src/sceneTest_soft_Shadows.xml");
        render.imageWriter = new ImageWriter("test17_soft_shadows_a_lot", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
 /**
     * sceneTest_Reflection_transparent
     */
    @Test
    public void test18_reflection_glossy_none() throws Exception {
        Render render = new Render(1, "./src/sceneTest_Reflection_transparent.xml");
        render.imageWriter = new ImageWriter("test18_reflection_glossy_none", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * sceneTest_Reflection_transparent_glossy
     */
    @Test
    public void test19_reflection_glossy_a_little() throws Exception {
        Render render = new Render(1, "./src/sceneTest_Reflection_transparent_glossy.xml");
        render.imageWriter = new ImageWriter("test19_reflection_glossy_a_little", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * sceneTest_Reflection_transparent_glossy
     */
    @Test
    public void test20_reflection_glossy_a_lot() throws Exception {
        Render render = new Render(1, "./src/sceneTest_Reflection_transparent_glossy_very.xml");
        render.imageWriter = new ImageWriter("test20_reflection_glossy_a_lot", 500, 500, 500, 500);
        render.renderImage();
        render.writeToImage();
    }
 @Test
    public void test21_index_of_refraction_none() throws SAXException, IOException {
        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(250, new Point3D(400, 0.0, -1000));
        sphere2.setShininess(20);
        sphere2.setEmmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("test21_index_of_refraction_none", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

       @Test
    public void test22_index_of_refraction() throws SAXException, IOException {
        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        sphere.setN(1.3333);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(250, new Point3D(400, 0.0, -1000));
        sphere2.setShininess(20);
        sphere2.setEmmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("test22_index_of_refraction", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
   
}