/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Elements.DirectionalLight;
import Elements.Light;
import Elements.LightSource;
import Elements.PointLight;
import Geometries.FlatGeometry;
import Geometries.Geometry;
import Geometries.OBJMesh;
import Geometries.OBJPolygon;
import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Scene.Scene;
import Scene.SceneBuilder;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.xml.sax.SAXException;

/**
 *
 * @author Shmuel & Elyasaf 
 * this class represents the render engine and the color calculations
 *
 * Variables included: 
 * sceneBuilder (SceneBuilder): builds scene from XML file
 * scene (Scene): the rendered scene 
 * imageWriter (ImageWriter): writes rendered scene to image 
 * RECURSION_LEVEL (final int): the amount of times a light ray can bounce 
 * antialiasing (int): the amount of rays sent through each pixel. 
 * random (Random): random factor
 *
 * functions included:
 *
 * renderImage: renders the image 
 * writeToImage: creates image from render 
 * printGrid: fills image with a white grid
 *
 * calcColor: calculates the color of a point on a geometry
 *
 * getClosestPoint: the function returns the closest point to the ray position if ray intersects many geometries 
 * getSceneRayIntersections: adds all intersection points of a ray to a list
 *
 * calcDifuseComp: calculates the diffuse light of a point on geometry
 * calcSpecularComp: calculates the specular light of a point on geometry 
 * occluded: calculated if point of geometry is shadowed
 *
 * constructRefractedRay: constructs a ray that passes through the geometry (transparency) 
 * constructReflectedRay: constructs a ray that bounces off the geometry (mirror)
 *
 */
public class Render {

    public SceneBuilder sceneBuilder;
    public ImageWriter imageWriter;
    private final int RECURSION_LEVEL = 5;
    private int antialiasing = 1;
    private String fileName = " ";
    Random random = new Random();

    // ***************** Constructors ********************** //
    public Render() {
        sceneBuilder = new SceneBuilder();
    }

    public Render(int antialiasing) {
        sceneBuilder = new SceneBuilder();
        this.antialiasing = antialiasing;
    }

    public Render(int antialiasing, String fileName) {
        sceneBuilder = new SceneBuilder();
        this.fileName = fileName;
        sceneBuilder.setFilePath(fileName);
        this.antialiasing = antialiasing;
    }

    public Render(ImageWriter imageWriter, Scene scene) {
        sceneBuilder = new SceneBuilder();
        this.imageWriter = new ImageWriter(imageWriter);
        this.sceneBuilder._scene = new Scene(scene);
    }

    // ***************** Getters/Setters ********************** //
    public int getAntialiasing() {
        return antialiasing;
    }

    public void setAntialiasing(int antialiasing) {
        if (antialiasing <= 0) {
            antialiasing = 1;
        }
        this.antialiasing = antialiasing;
    }

    // ***************** Operations ******************** //
    /**
     * function renders image
     *
     * @throws SAXException
     * @throws IOException
     */
    public void renderImage() throws SAXException, IOException {
        System.out.println("please wait while image is rendering!");
        if (this.fileName != " ") {
            sceneBuilder.loadSceneFromFile();//builds scene from XML
        }
        ArrayList<Color> pixelList = new ArrayList<>(); //list of colors for one pixel
        double numX = 0;//factor where to start ray from
        double numY = 0;//factor where to start ray from
        for (int i = 0; i < imageWriter.getWidth(); i++) {
            for (int j = 0; j < imageWriter.getHeight(); j++) {
                for (int k = 0; k < antialiasing; k++) {
                    if (antialiasing > 1) {
                        numX = random.nextDouble() - 0.5;//random factor [-0.5,0.5]
                        numY = random.nextDouble() - 0.5;//random factor [-0.5,0.5]
                    }
                    //constructs ray through pixel
                    Ray ray = new Ray(sceneBuilder._scene.getCamera().constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), i + numX, j + numY, sceneBuilder._scene.getScreenDistance(), imageWriter.getWidth(), imageWriter.getHeight()));
                    //gets ray's intersections with geometries
                    Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
                    if (intersectionPoints.isEmpty()) {
                        //fill the pixel with background color
                        //pixelList.add(sceneBuilder._scene.gradiant(j, imageWriter.getHeight(), new Color(0,0,50), new Color(0,0,0)));
                        //pixelList.add(sceneBuilder._scene.gradiantToMiddle(j, imageWriter.getHeight()/2, new Color(0,0,0), new Color(25,25,25)));
                        pixelList.add(sceneBuilder._scene.gradiantSphere(i, j, imageWriter.getWidth(), imageWriter.getHeight(), new Color(0, 0, 0), new Color(0, 0, 0)));
                    } else {
                        //get closest point to camera
                        Map<Geometry, Point3D> closestPoint = (getClosestPoint(intersectionPoints, sceneBuilder._scene.getCamera().getP0()));
                        Map.Entry<Geometry, Point3D> closestPointEntry = closestPoint.entrySet().iterator().next();
                        //calculate color of point
                        pixelList.add(calcColor(closestPointEntry.getKey(), closestPointEntry.getValue(), ray));
                    }

                }
                //calculate average of colors to put in one pixel
                int sumOfRed = 0;
                int sumOfGreen = 0;
                int sumOfBlue = 0;
                //sum of wheits

                for (int k = 0; k < antialiasing; k++) {
                    sumOfRed += pixelList.get(k).getRed();
                    sumOfGreen += pixelList.get(k).getGreen();
                    sumOfBlue += pixelList.get(k).getBlue();
                }
                //average of colors
                sumOfRed /= antialiasing;
                sumOfGreen /= antialiasing;
                sumOfBlue /= antialiasing;

                //write color to image pixel
                imageWriter.writePixel(i, j, new Color(sumOfRed, sumOfGreen, sumOfBlue));
                pixelList.clear();
            }
        }
    }

    //creates image
    public void writeToImage() {
        imageWriter.writeToimage();
    }

    //prints grid
    public void printGrid(int interval) {
        for (int i = 0; i < imageWriter.getWidth(); i++) {
            for (int j = 0; j < imageWriter.getHeight(); j++) {
                if (i % interval == 0 || j % interval == 0 || i == imageWriter.getWidth() - 1 || j == imageWriter.getHeight() - 1) {
                    imageWriter.writePixel(i, j, Color.WHITE);
                }
            }
        }

    }

    /**
     * the function calculates color of point
     *
     * @param geometry the geometrie's point
     * @param point the point
     * @param ray the ray
     * @return color
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray ray) {
        return calcColor(geometry, point, ray, 0);
    }

    /**
     *
     * the function calculates color of point
     *
     * @param geometry the geometrie's point
     * @param point the point
     * @param inRay the ray
     * @param level the level of recursion
     * @return
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {
        if (RECURSION_LEVEL == level) {
            return new Color(0, 0, 0);
        }

        Vector Vnormal;
        //ambiant light of scene
        Color ambiantLight = new Color(sceneBuilder._scene.getAmbientLight().getIntensity().getRGB());

        //emission light of geometry
        Color emissionLight = new Color(geometry.getEmmission().getRGB());
        if (geometry instanceof OBJPolygon) {
            emissionLight = ((OBJPolygon) geometry).gradiantEmission(point);
        }

        Vnormal = geometry.getNormal(point);

        int finalR,//final red color
                finalG,//final green color 
                finalB;//final blue color

        //gets light list
        Iterator<LightSource> lights = sceneBuilder._scene.getLightSourceIterator();

        int difuseR = 0, //diffuse red color 
                difuseG = 0, //diffuse green color  
                difuseB = 0, //diffuse blue color 
                spcularR = 0, //specular red color
                specularG = 0, //specular red color
                specularB = 0;  //specular red color

        //loops through lights of scene
        while (lights.hasNext()) {
            LightSource light = lights.next();//gets the next light
            double shadow;
            if (((Light) light).isShadow() == false || geometry.isShadow() == false) {
                shadow = 1;
            } else {
                shadow = occluded(light, point, geometry);//checks how much shadow the point has
            }
            if (shadow > 0) {//if point is not 100% shadowed
                //calculate the difuse light of point
                if (geometry.getMaterial().getKd() > 0) {
                    Color d = new Color(calcDifuseComp(geometry.getMaterial().getKd(), Vnormal, light.getL(point), light.getIntensity(point), geometry).getRGB());
                    difuseR += d.getRed() * shadow;
                    difuseG += d.getGreen() * shadow;
                    difuseB += d.getBlue() * shadow;
                }
                //calculate the specular light of point
                if (geometry.getMaterial().getKs() > 0) {
                    Color s = new Color(calcSpecularComp(geometry.getMaterial().getKs(), new Vector(point, sceneBuilder._scene.getCamera().getP0()), Vnormal, light.getL(point), geometry.getShininess(), light.getIntensity(point), geometry).getRGB());
                    spcularR += s.getRed() * shadow;
                    specularG += s.getGreen() * shadow;
                    specularB += s.getBlue() * shadow;
                }
            }
        }
        Color reflectedLight = new Color(0, 0, 0);//the reflected light color
        Color refractedLight = new Color(0, 0, 0);//the refracted light color

        // ***************** REFLECTION CALCULATION ********************** //
        int rays = 1;//the amount of glossy rays
        if (geometry.getMaterial().getKg() > 0 && geometry.getMaterial().getKr() != 0) {//if geometry is glossy and a little reflective
            rays = 20;
        }
        int reflectR = 0;//the reflected red color
        int reflectG = 0;//the reflected green color
        int reflectB = 0;//the reflected blue color

        //calculate reflected color
        if (geometry.getMaterial().getKr() != 0) {
            for (int i = 0; i < rays; i++) {
                //get reflected ray
                Ray reflectedRay = constructReflectedRay(Vnormal, point, inRay, geometry.getMaterial().getKg());
                //gets closest point of intersection
                Map<Geometry, Point3D> reflectedEntry = (getClosestPoint(getSceneRayIntersections(reflectedRay), reflectedRay.getPoo()));
                if (!reflectedEntry.isEmpty()) {
                    Map.Entry<Geometry, Point3D> reflectedEntry2 = reflectedEntry.entrySet().iterator().next();
                    //calculate color
                    Color reflectedColor = calcColor(reflectedEntry2.getKey(), reflectedEntry2.getValue(), reflectedRay, level + 1);
                    double kr = geometry.getMaterial().getKr();
                    reflectR += (int) (kr * reflectedColor.getRed());
                    reflectG += (int) (kr * reflectedColor.getGreen());
                    reflectB += (int) (kr * reflectedColor.getBlue());
                }
            }
            reflectR /= rays;
            reflectG /= rays;
            reflectB /= rays;
            reflectedLight = new Color(reflectR, reflectG, reflectB);
        }

        // ***************** REFRACTION CALCULATION ********************** //
        if (geometry.getMaterial().getKt() != 0) {
            //refraction ray
            Ray refractedRay = constructRefractedRay(geometry, Vnormal, point, inRay);
            //point to calculate
            Map<Geometry, Point3D> refractedEntry = (getClosestPoint(getSceneRayIntersections(refractedRay), sceneBuilder._scene.getCamera().getP0()));
            int refractR = 0;
            int refractG = 0;
            int refractB = 0;
            //only calculate if object is transparent
            if (!refractedEntry.isEmpty()) {
                Map.Entry<Geometry, Point3D> refractedEntry2 = refractedEntry.entrySet().iterator().next();
                //calculate color
                Color refractedColor = calcColor(refractedEntry2.getKey(), refractedEntry2.getValue(), refractedRay, level + 1);
                double kt = geometry.getMaterial().getKt();
                refractR = (int) (kt * refractedColor.getRed());
                refractG = (int) (kt * refractedColor.getGreen());
                refractB = (int) (kt * refractedColor.getBlue());

            }
            refractedLight = new Color(refractR, refractG, refractB);
        }

        //THE FINAL SUM OF PHONG MODEL
        finalR = Math.min(255, ambiantLight.getRed() + emissionLight.getRed() + difuseR + spcularR + reflectedLight.getRed() + refractedLight.getRed());
        finalG = Math.min(255, ambiantLight.getGreen() + emissionLight.getGreen() + difuseG + specularG + reflectedLight.getGreen() + refractedLight.getGreen());
        finalB = Math.min(255, ambiantLight.getBlue() + emissionLight.getBlue() + difuseB + specularB + reflectedLight.getBlue() + refractedLight.getBlue());
        return new Color(finalR, finalG, finalB);
    }

    /**
     *
     * @param intersectionPoint list of intersection points
     * @param ray get reflection ray
     * @return the closest point to ray
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoint, Point3D point2) {
        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> minDistancePoint = new HashMap<>();
        for (Entry<Geometry, List<Point3D>> entry : intersectionPoint.entrySet()) {
            for (Point3D point : entry.getValue()) {
                if (point2.distance(point) < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = point2.distance(point);
                }
            }
        }
        return minDistancePoint;
    }

    /**
     *
     * @param ray
     * @return a list of points the ray intersects in the scene
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
        List<Point3D> geometryIntersectionPoints;
        List<Point3D> OBJgeometryIntersectionPoints;
        Iterator<Geometry> geometries = sceneBuilder._scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<>();
        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            if (geometry instanceof OBJMesh) {
                if (((OBJMesh) geometry).intersectsBoundinBox(ray)) {
                    for (OBJPolygon polygon : ((OBJMesh) geometry).polygons) {

                        OBJgeometryIntersectionPoints = new ArrayList<>(polygon.findIntersections(ray));
                        if (!OBJgeometryIntersectionPoints.isEmpty()) {
                            intersectionPoints.put(polygon, OBJgeometryIntersectionPoints);
                        }
                    }
                }
            } else {
                geometryIntersectionPoints = new ArrayList<>(geometry.findIntersections(ray));

                if (!geometryIntersectionPoints.isEmpty()) {
                    intersectionPoints.put(geometry, geometryIntersectionPoints);
                }
            }
        }
        return intersectionPoints;
    }

    /**
     *
     * @param kd diffuse factor
     * @param normal of the geometry at that point
     * @param lightToPoint the vector from the light to the point
     * @param lightIntensity the intensity of the light
     * @return the diffuse color at the point
     */
    private Color calcDifuseComp(double kd, Vector normal, Vector lightToPoint, Color lightIntensity, Geometry geometry) {
        normal.normalize();
        lightToPoint.normalize();
        double difuseFactor = kd * normal.dotProduct(lightToPoint);

        //if we hit point behind object and object is not transparent then return black
        if (geometry.getMaterial().getKt() == 0 && difuseFactor > 0) {
            return new Color(0, 0, 0);
        }

        difuseFactor = Math.abs(difuseFactor);
        int r = Math.min(255, (int) (lightIntensity.getRed() * difuseFactor));
        int g = Math.min(255, (int) (lightIntensity.getGreen() * difuseFactor));
        int b = Math.min(255, (int) (lightIntensity.getBlue() * difuseFactor));
        return new Color(r, g, b);
    }

    /**
     *
     * @param ks specular factor
     * @param cameraToPoint vector from camera to point
     * @param normalOfPoint normal of geometry at point
     * @param lightToPoint vector from light to point
     * @param nShininess the amount of shininess
     * @param intensity the intensity of the light
     * @return the specular color at point
     */
    private Color calcSpecularComp(double ks, Vector cameraToPoint, Vector normalOfPoint, Vector lightToPoint, double nShininess, Color intensity, Geometry geometry) {
        lightToPoint.normalize();
        normalOfPoint.normalize();
        cameraToPoint.normalize();
        double scale = 2 * normalOfPoint.dotProduct(lightToPoint);
        Vector temp = new Vector(normalOfPoint);
        temp.scale(scale);
        Vector R = new Vector(lightToPoint);
        R.subtract(temp);
        double dotP = cameraToPoint.dotProduct(R);

        //if we hit point behind object and object is not transparent then return black
        if (dotP > 0/* && geometry.getMaterial().getKt() == 0*/) {
            return new Color(0, 0, 0);
        }
        double factor = Math.pow(dotP, nShininess);
        factor = Math.abs(factor) * ks;
        int r = Math.min(255, (int) (factor * intensity.getRed()));
        int g = Math.min(255, (int) (factor * intensity.getGreen()));
        int b = Math.min(255, (int) (factor * intensity.getBlue()));
        return new Color(r, g, b);
    }

    /**
     *
     * @param light the source light
     * @param point point to check
     * @param geometry of point
     * @return amount of shadow at point
     */
    private double occluded(LightSource light, Point3D point, Geometry geometry) {
            boolean transparent = true;
            boolean intersect = false;
            double atimut = 0;
        
            //moves the point fowards
            Vector lightDirection = light.getL(point);
            lightDirection.scale(-1);
            Point3D geometryPoint = new Point3D(point);
            Vector epsVector = new Vector(geometry.getNormal(point));
            epsVector.scale(0.1);
            geometryPoint.add(epsVector);
            
        if (antialiasing <= 1) {
            
            //creates ray to light
            Ray lightRay = new Ray(geometryPoint, lightDirection);
            
            //find intersections
            Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
            // Flat geometry cannot self intersect  
            if (geometry instanceof FlatGeometry) {
                intersectionPoints.remove(geometry);
            }
            //if there are no intersections 
            if (intersectionPoints.isEmpty()) {
                return 1;
            }
            //for each geometry
            for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
                transparent = true;
                if (entry.getKey().getMaterial().getKt() < 1) {//if geometry is not 100% transparent
                    transparent = false;
                    //for each point in geometry
                    for (Point3D point3D : entry.getValue()) {
                        //if the light is a directional light or the intersection point is closer then the light
                        if ((light instanceof DirectionalLight || (point.distance(point3D) < point.distance(((PointLight) light).getPosition())))) {
                            intersect = true;
                            atimut += (1 - entry.getKey().getMaterial().getKt());//add amount of atimut to parameter
                        }
                    }
                }
            }
            if (intersect == false || transparent == true) {//if there were no intersections or all geometries are transparent
                return 1;
            }
            //if amount of atimut is less then one
            if (atimut < 1) {
                return 1 - atimut;
            }
            //there in an intersection and no transparency
            return 0;

        }

        // soft shadows
        
        double hasLight = 0;
        double xx;//random x for vector
        double yy;//random y for vector
        double zz;//random z for vector
        double scaleVector;//the amount to scale the vector to get the right size
        Vector randomVector;//random vector from light position with size of area
        Vector randomGeometryToLightVector;//random vector from geometry to light
        Ray shadowRay;
        double amountOfShadowRays=antialiasing * 10;
        for (int i = 0; i < amountOfShadowRays; i++) {
            atimut = 0;
            transparent = true;
            intersect = false;
            xx = random.nextInt(101) - 50;
            yy = random.nextInt(101) - 50;
            zz = random.nextInt(101) - 50;
            randomVector = new Vector(new Point3D(new Coordinate(xx), new Coordinate(yy), new Coordinate(zz)));
            randomVector.normalize();
            xx = randomVector.getHead().getX().getCoordinate();
            yy = randomVector.getHead().getY().getCoordinate();
            zz = randomVector.getHead().getZ().getCoordinate();
            
            //scales the vector to the area of light
            scaleVector = ((PointLight) light).getArea() / (Math.sqrt(xx * xx + yy * yy + zz * zz));
            randomVector.scale(scaleVector);
            
            //create the random shadow ray
            randomGeometryToLightVector = new Vector(((PointLight) light).getPosition());
            randomGeometryToLightVector.add(randomVector);
            randomGeometryToLightVector.subtract(new Vector(point));
            randomGeometryToLightVector.normalize();
            shadowRay = new Ray(geometryPoint, randomGeometryToLightVector);
            
            Map<Geometry, List<Point3D>> intersectionPoints2 = getSceneRayIntersections(shadowRay);
            if ((geometry instanceof FlatGeometry)) {
                intersectionPoints2.remove(geometry);
            }
            //if there are no intersections
            if (intersectionPoints2.isEmpty()) {
                hasLight++;
                
            } else {
                for (Entry<Geometry, List<Point3D>> entry : intersectionPoints2.entrySet()) {
                    transparent=true;
                    if (entry.getKey().getMaterial().getKt() < 1) {//if geometry is not 100% transparent
                        transparent = false;
                        for (Point3D point3D : entry.getValue()) {//for each point
                            //if the light is a directional light or the intersection point is closer then the light
                            if ((light instanceof DirectionalLight || (point.distance(point3D) < point.distance(((PointLight) light).getPosition())))) {
                                intersect = true;
                                atimut += (1 - entry.getKey().getMaterial().getKt());
                            }
                        }
                    }
                }
                if (intersect == false || transparent == true) {//if there were no intersections or all geometries are transparent
                    hasLight++;
                }
                if (atimut< 1) {
                    hasLight += 1 - atimut;
                }
            }
        }
        return hasLight / amountOfShadowRays;
    }

    /**
     *
     * @param normal
     * @param point position of ray
     * @param inRay first ray
     * @return a new ray from point with same direction
     */
    private Ray constructRefractedRay(Geometry geometry, Vector normal, Point3D point, Ray inRay) {
        
        //occording to index of refraction algorithm
        if (normal.dotProduct(inRay.getDirection()) < 0) {
            normal.scale(-1);
        }
        Vector newVector2 = inRay.getDirection();
        newVector2.scale(-1);
        double n1_n2 = inRay.getIndexOfRefraction() / geometry.getMaterial().getN();
        double QiAngle = Math.acos(Math.abs(newVector2.dotProduct(normal)));
        double QrAngle = Math.asin(n1_n2 * Math.sin(QiAngle));
        if (Math.abs(QiAngle - QrAngle) < 0.00001) {
            QiAngle = QrAngle;
        }
        Vector NewNormal = new Vector(normal);
        NewNormal.scale((Math.cos(QiAngle) - Math.cos(QrAngle)) * (n1_n2));
        newVector2.scale(-1 * (n1_n2));
        Vector finalDirection = new Vector(NewNormal);
        finalDirection.subtract(newVector2);
        finalDirection.normalize();
        finalDirection.scale(-1);
        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(inRay.getDirection());
        epsVector.scale(0.00001);
        geometryPoint.add(epsVector);
        return new Ray(geometryPoint, finalDirection, geometry.getMaterial().getN());
    }

    /**
     *
     * @param normal
     * @param point position of ray
     * @param inRay original ray
     * @param blurry amount of glossiness
     * @return a new reflection ray from point
     */
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay, double blurry) {
        Vector newNormal=new Vector(normal);
            if (blurry > 0) {
            //random factor to move ray if blurry is 0 - ray is not moved
            double factorX = (random.nextDouble() * 2 - 1) * blurry;//generates random nuber between -1 and 1 times blurry
            double factorY = (random.nextDouble() * 2 - 1) * blurry;//generates random nuber between -1 and 1 times blurry
            double factorZ = (random.nextDouble() * 2 - 1) * blurry;//generates random nuber between -1 and 1 times blurry

            Vector vFact = new Vector(new Point3D(new Coordinate(factorX), new Coordinate(factorY), new Coordinate(factorZ)));

            newNormal.add(vFact);
            newNormal.normalize();
        }
        //create reflection vector
        double scale = 2 * inRay.getDirection().dotProduct(newNormal);
        Vector temp = new Vector(newNormal);
        temp.scale(scale);
        Vector R = new Vector(inRay.getDirection());
        R.subtract(temp);
        R.normalize();
        
        
        if (normal.dotProduct(R) < 0) {
            normal.scale(-1);
        }

        //move the ray position in the direction of normal so ray won't intersect with same geometry
        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(normal);
        epsVector.scale(0.0001);
        geometryPoint.add(epsVector);
        return new Ray(geometryPoint, R);
    }
}
