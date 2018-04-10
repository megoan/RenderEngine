/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grid;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

/**
 *
 * @author User
 */
public class Grid {
    private BoundingBox grid;
    private Ray ray;
    private Vector rayDirection=new Vector();
    private Point3D rayOrigin=new Point3D();
    private BoundingBox[][][] gridresolution=new BoundingBox[50][50][50]; 
    private Point3D cellSize;
    
    public void calculatecellSize()
    {
        
    }
    
    
    
}
