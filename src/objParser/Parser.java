/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objParser;

import Geometries.OBJMesh;
import Geometries.OBJPolygon;
import Geometries.OBJTriangle;
import Geometries.OBJTriangleSmooth;
import Geometries.Triangle;
import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author User
 */
public class Parser {

    boolean smooth = false;
    public OBJMesh mesh=new OBJMesh();
    public ArrayList<OBJPolygon> polygons = null;
    public ArrayList<OBJTriangle> OBJTriangleList = null;
    public ArrayList<OBJTriangleSmooth> OBJTriangleSmoothList = null;

    public void loadModel(String fileName) {
        FileReader fr = null;
        try {
            fr = new FileReader(new File(fileName));
        } catch (FileNotFoundException e) {
        }

        BufferedReader reader = new BufferedReader(fr);
        String line;

        List<Point3D> pointList = new ArrayList<>();
        List<Vector> vectors = new ArrayList<>();
        polygons = new ArrayList<>();
        OBJTriangleList = new ArrayList<>();
        OBJTriangleSmoothList = new ArrayList<>();
        try {
            OBJTriangle tri;
            OBJTriangleSmooth triS;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                if (line.startsWith("v ")) {
                    Point3D v = new Point3D();
                    v.setX(new Coordinate(Float.parseFloat(split[1])));
                    v.setY(new Coordinate(Float.parseFloat(split[2])));
                    v.setZ(new Coordinate(Float.parseFloat(split[3])));
                    pointList.add(v);
                } else if (line.startsWith("vn ")) {
                    Point3D normalHead = new Point3D();
                    Vector normal = new Vector();
                    normalHead.setX(new Coordinate(Float.parseFloat(split[1])));
                    normalHead.setY(new Coordinate(Float.parseFloat(split[2])));
                    normalHead.setZ(new Coordinate(Float.parseFloat(split[3])));
                    normal.setHead(normalHead);
                    vectors.add(normal);
                } else if (line.startsWith("s ")) {
                    if (line.contains("1")) {
                        smooth = true;
                    }
                } else if (line.startsWith("f ")) {
                    ArrayList<String> doubleSplit = new ArrayList<>();
                    String[] spi;
                    for (String string : split) {
                        spi = (string.split("//"));
                        doubleSplit.addAll(Arrays.asList(spi));
                    }                    
                        if (smooth == true) {
                            triS = new OBJTriangleSmooth(vectors.get(Integer.parseInt(doubleSplit.get(2)) - 1), vectors.get(Integer.parseInt(doubleSplit.get(4)) - 1), vectors.get(Integer.parseInt(doubleSplit.get(6)) - 1), pointList.get(Integer.parseInt(doubleSplit.get(1)) - 1), pointList.get(Integer.parseInt(doubleSplit.get(3)) - 1), pointList.get(Integer.parseInt(doubleSplit.get(5)) - 1), new Vector());
                            OBJTriangleSmoothList.add(triS);
                        } else {
                            tri = new OBJTriangle(pointList.get(Integer.parseInt(doubleSplit.get(1)) - 1), pointList.get(Integer.parseInt(doubleSplit.get(3)) - 1), pointList.get(Integer.parseInt(doubleSplit.get(5)) - 1), vectors.get(Integer.parseInt(doubleSplit.get(6)) - 1));
                            OBJTriangleList.add(tri);
                        }                  
                }
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
        }
        
        polygons.addAll(OBJTriangleList);
        polygons.addAll(OBJTriangleSmoothList);
        mesh.polygons=polygons;
    }
}
