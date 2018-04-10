/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.DirectionalLight;
import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Cylinder;
import Geometries.OBJMesh;
import Geometries.OBJPolygon;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Parser.SceneDescriptor;
import Primitives.Coordinate;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import objParser.Parser;
import org.xml.sax.SAXException;

/**
 *
 * @author User
 */
public class SceneBuilder {

    public SceneDescriptor _sceneDesc;
    public Scene _scene;
    public ImageWriter _imageWriter;
    private String filePath;
    public Random r=new Random();
    public SceneBuilder() {
    this._sceneDesc = new SceneDescriptor();

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void loadSceneFromFile() throws SAXException, IOException {
        ArrayList<Double> variables = new ArrayList<>();
        _sceneDesc.InitializeFromXMLstring(filePath);
        String sceneName = _sceneDesc._sceneAttributes.get("scene-name");
        variables = splitStringToNumbers(_sceneDesc._sceneAttributes.get("background-color"));
        Color backgroundColor = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        variables.clear();
        variables = splitStringToNumbers(_sceneDesc._ambientLightAttributes.get("color"));
        AmbientLight ambientLight = new AmbientLight(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        variables.clear();
        variables = splitStringToNumbers(_sceneDesc._cameraAttributes.get("PO"));
        Point3D cameraPoint = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        variables = splitStringToNumbers(_sceneDesc._cameraAttributes.get("vUp"));
        Vector cameraVUp = new Vector(new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2))));
        variables.clear();

        variables = splitStringToNumbers(_sceneDesc._cameraAttributes.get("vTo"));
        Vector cameraVTo = new Vector(new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2))));

        Camera camera = new Camera(cameraPoint, cameraVUp, cameraVTo);
        double sceneDistance = Double.parseDouble(_sceneDesc._sceneAttributes.get("screen-distance"));
        _scene = new Scene(sceneName, backgroundColor, ambientLight, camera, sceneDistance);
    
        




//           addOBJMesh("./src/glass.obj", 3000, -1350,-5000, new Color(0,0,0), new Color(0,30,20),0,1,0,1,1,0,false);     
//           addOBJMesh("./src/glass.obj", -3000, -1350,-5000, new Color(0,0,0), new Color(0,30,20),0,1,0,1,1,0,false);     
//           addOBJMesh("./src/wine.obj", 3000, -1350,-5000, new Color(44,5,14), new Color(22,2,7),0,0.5,0,1,1.6,0,false);     
//           addOBJMesh("./src/wine.obj", -3000, -1350,-5000, new Color(44,5,14), new Color(22,2,7),0,0.5,0,1,1.6,0,false);     
//           addOBJMesh("./src/straw.obj", 3000, -1350,-5000, new Color(0,50,0), new Color(0,0,50),1,1,0,0,1,0,false);     
//           addOBJMesh("./src/straw2.obj", -3000, -1350,-5000, new Color(0,50,0), new Color(0,0,50),1,1,0,0,1,0,false);  
           
//          addOBJMesh("./src/fin.obj", 1000, -900,-2600, new Color(50,50,50), new Color(50,50,50),0.5,0.3,0,0,0,true);
//         addOBJMesh("./src/shell.obj", 200, -690,-2100, new Color(50,50,100), new Color(50,50,50),1,0.8,0,0,0,true);
//         addOBJMesh("./src/logo_blue1.obj", 150, -680,-2050, new Color(24,151,86), new Color(24,151,86),1,0.8,0,0,1,0,false);
//         addOBJMesh("./src/logo_blue2.obj", 150, -680,-2050, new Color(36,56,117), new Color(36,56,117),1,0.8,0,0,1,0,false);
//         addOBJMesh("./src/logo_pink1.obj", 150, -680,-2050, new Color(137,53,95), new Color(137,53,95),1,0.8,0,0,1,0,false);
//         addOBJMesh("./src/logo_pink2.obj", 150, -680,-2050, new Color(69,40,104), new Color(69,40,104),1,0.8,0,0,1,0,false);
//         addOBJMesh("./src/Coconut_tree.obj", 0, -760,-2200, new Color(115,78,36), new Color(83,50,33),1,0.8,0,0,0,true);
//         addOBJMesh("./src/leaves.obj", 0, -760,-2200, new Color(52,154,70), new Color(28,108,69),0.7,0.3,0,0,0,true);
//         addOBJMesh("./src/fruit.obj", 0, -760,-2200, new Color(50,25,12), new Color(50,25,12),1,0,0,0,0,true);
//         addOBJMesh("./src/Coconut_tree2.obj", 200, -760,-2200, new Color(115,78,36), new Color(83,50,33),1,0.8,0,0,0,true);
//         addOBJMesh("./src/leaves2.obj", 200, -760,-2200, new Color(52,154,70), new Color(28,108,69),0.7,0.3,0,0,0,true);
//         addOBJMesh("./src/fruit2.obj", 200, -760,-2200, new Color(50,25,12), new Color(50,25,12),1,0,0,0,0,true);
//         addOBJMesh("./src/bird1.obj", -5000, 5000,-50000, new Color(30,30,30), new Color(30,30,30),1,1,0,0,0,false);
//         addOBJMesh("./src/bird2.obj", -7000, 5000,-50000, new Color(30,30,30), new Color(30,30,30),1,1,0,0,0,false);
//         addOBJMesh("./src/bird3.obj", -9000, 5000,-50000, new Color(30,30,30), new Color(30,30,30),1,1,0,0,0,false);
//         addOBJMesh("./src/sun_rays.obj", -300 , 900 , -5000 , new Color(220,220,20), new Color(220,220,20),0.1,0,0,1,0,false);
//         addOBJMesh("./src/sand4.obj", 0, -743,-2200, new Color(217,198,155), new Color(150,126,81),0.4,0,0,0,0,true);       

        for (Map<String, String> _sphere : _sceneDesc._spheres) {
            _scene.addGeometry(getSphere(_sphere));
        }
        for (Map<String, String> _triangle : _sceneDesc._triangles) {
            _scene.addGeometry(getTriangle(_triangle));
        }
        for (Map<String, String> _cylinder : _sceneDesc._cylinders) {
            _scene.addGeometry(getCylinder(_cylinder));
        }

        for (Map<String, String> _plane : _sceneDesc._planes) {
            _scene.addGeometry(getPlane(_plane));
        }

        for (Map<String, String> directionalLight : _sceneDesc.directionalLights) {
            _scene.addLight(getDirectionalLight(directionalLight));
        }
        for (Map<String, String> pointLight : _sceneDesc.pointLights) {
            _scene.addLight(getPointLight(pointLight));
        }

        for (Map<String, String> spotLight : _sceneDesc.spotLights) {
            _scene.addLight(getSpotLight(spotLight));
        }

    }

    public ArrayList<Double> splitStringToNumbers(String str) {
        ArrayList<Double> numbers = new ArrayList<>();
        String[] list = str.split(" ");
        for (String string : list) {
            numbers.add(Double.parseDouble(string));
        }
        return numbers;
    }

    public Sphere getSphere(Map<String, String> map) {
        ArrayList<Double> variables = new ArrayList<>();
        variables = splitStringToNumbers(map.get("center"));
        Point3D center = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        double radius = Double.parseDouble(map.get("radius"));
        variables = splitStringToNumbers(map.get("material"));
        Material material = new Material(variables.get(0), variables.get(1), variables.get(2), variables.get(3), variables.get(4), variables.get(5));
        variables.clear();
        double nShininess = Double.parseDouble(map.get("nShininess"));
        variables = splitStringToNumbers(map.get("emmission"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Color emission = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        Sphere sphere = new Sphere(radius,center);
        sphere.setEmmission(new Color(emission.getRGB()));
        sphere.setShininess(nShininess);
        sphere.setMaterial(new Material(material));
        sphere.setShadow(shadow);
        return new Sphere(sphere);
    }
    public Cylinder getCylinder(Map<String, String> map) {
        ArrayList<Double> variables;
        variables = splitStringToNumbers(map.get("position"));
        Point3D position = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        variables = splitStringToNumbers(map.get("direction"));
        Point3D nHead = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        Vector direction = new Vector(nHead);
        variables.clear();
        double radius = Double.parseDouble(map.get("radius"));
        variables = splitStringToNumbers(map.get("material"));
        Material material = new Material(variables.get(0), variables.get(1), variables.get(2), variables.get(3), variables.get(4), variables.get(5));
        variables.clear();
        double nShininess = Double.parseDouble(map.get("nShininess"));
        variables = splitStringToNumbers(map.get("emmission"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Color emission = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        Cylinder cylinder = new Cylinder(position,direction,radius);
        cylinder.setEmmission(new Color(emission.getRGB()));
        cylinder.setShininess(nShininess);
        cylinder.setMaterial(new Material(material));
        cylinder.setShadow(shadow);
        return new Cylinder(cylinder);
    }

    public Triangle getTriangle(Map<String, String> map) {
        ArrayList<Double> variables;
        variables = splitStringToNumbers(map.get("P0"));
        Point3D P0 = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        variables = splitStringToNumbers(map.get("P1"));
        Point3D P1 = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        variables = splitStringToNumbers(map.get("P2"));
        Point3D P2 = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        variables = splitStringToNumbers(map.get("material"));
        Material material = new Material(variables.get(0), variables.get(1), variables.get(2), variables.get(3), variables.get(4), variables.get(5));
        variables.clear();
        double nShininess = Double.parseDouble(map.get("nShininess"));
        variables = splitStringToNumbers(map.get("emmission"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Color emission = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        Triangle triangle = new Triangle(P0, P1, P2);
        triangle.setEmmission(new Color(emission.getRGB()));
        triangle.setShininess(nShininess);
        triangle.setMaterial(new Material(material));
        triangle.setShadow(shadow);
        return new Triangle(triangle);
    }

    public Plane getPlane(Map<String, String> map) {
        ArrayList<Double> variables;
        variables = splitStringToNumbers(map.get("q"));
        Point3D q = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        variables = splitStringToNumbers(map.get("n"));
        Point3D nHead = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        Vector n = new Vector(nHead);
        variables.clear();
        variables = splitStringToNumbers(map.get("material"));
        Material material = new Material(variables.get(0), variables.get(1), variables.get(2), variables.get(3), variables.get(4), variables.get(5));
        variables.clear();
        double nShininess = Double.parseDouble(map.get("nShininess"));
        variables = splitStringToNumbers(map.get("emmission"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Color emission = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        Plane plane = new Plane(q,n);
        plane.setEmmission(new Color(emission.getRGB()));
        plane.setShininess(nShininess);
        plane.setMaterial(new Material(material));
        plane.setShadow(shadow);
        return new Plane(plane);
    }

    public SpotLight getSpotLight(Map<String, String> map) {
        ArrayList<Double> variables;
        variables = splitStringToNumbers(map.get("position"));
        Point3D position = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        double area = Double.parseDouble(map.get("area"));
        variables = splitStringToNumbers(map.get("Kc_Kl_Kq"));
        double kc = variables.get(0);
        double kl = variables.get(1);
        double kq = variables.get(2);
        variables.clear();
        variables = splitStringToNumbers(map.get("color"));
        Color color = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        variables = splitStringToNumbers(map.get("direction"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Point3D directionHead = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        Vector direction = new Vector(directionHead);
        variables.clear();
        return new SpotLight(color, position, area, direction, kc, kl, kq,shadow);
    }

    public PointLight getPointLight(Map<String, String> map) {
        ArrayList<Double> variables;
        variables = splitStringToNumbers(map.get("position"));
        Point3D position = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        variables.clear();
        double area = Double.parseDouble(map.get("area"));
        variables = splitStringToNumbers(map.get("Kc_Kl_Kq"));
        double kc = variables.get(0);
        double kl = variables.get(1);
        double kq = variables.get(2);
        variables.clear();
        variables = splitStringToNumbers(map.get("color"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Color color = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        return new PointLight(color, position, area, kc, kl, kq,shadow);

    }

    public DirectionalLight getDirectionalLight(Map<String, String> map) {
        ArrayList<Double> variables;
        variables = splitStringToNumbers(map.get("color"));
        Color color = new Color(variables.get(0).intValue(), variables.get(1).intValue(), variables.get(2).intValue());
        variables.clear();
        variables = splitStringToNumbers(map.get("direction"));
        boolean shadow=Boolean.parseBoolean(map.get("shadow"));
        Point3D directionHead = new Point3D(new Coordinate(variables.get(0)), new Coordinate(variables.get(1)), new Coordinate(variables.get(2)));
        Vector direction = new Vector(directionHead);
        variables.clear();
        return new DirectionalLight(color, direction,shadow);
    }
    public void addOBJMesh(String name, double moveX,double moveY,double moveZ, Color top,Color buttom, double diffuse, double specular, double reflection, double refraction,double indexOfRefraction, double glossy,boolean  shadow)
    {
         Parser obj = new Parser();
         obj.loadModel(name);
         OBJMesh mesh = new OBJMesh(obj.mesh); 
         mesh.move(moveX, moveY, moveZ);
         mesh.calcBoundingBox();
         for (OBJPolygon polygon : mesh.polygons) {
            polygon.setGradiant1(new Color(buttom.getRGB()));
            polygon.setGradiant2(new Color(top.getRGB()));
            polygon.setShininess(30);
            polygon.setKd(diffuse);
            polygon.setKs(specular);
            polygon.setKr(reflection);
            polygon.setKt(refraction);
            polygon.setKg(glossy);
            polygon.setShadow(shadow);
            polygon.setN(indexOfRefraction);
        }
        _scene.addGeometry(mesh);
    }
}
