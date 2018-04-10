/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometries;

import Primitives.Material;
import java.awt.Color;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.util.List;

/**
 *
 * @author shmuel
 */
abstract public class Geometry {

    private Material _material = new Material();
    private double _nShininess = 1;
    private Color _emmission = new Color(0, 0, 0);
    private boolean shadow= true;

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public Geometry() {
    }

    public Material getMaterial() {
        return new Material(_material);
    }

    public void setMaterial(Material _material) {
        this._material = new Material(_material);
    }

    public double getShininess() {
        return _nShininess;
    }

    public void setShininess(double _nShininess) {
        this._nShininess = _nShininess;
    }

    public Color getEmmission() {
        return new Color(_emmission.getRGB());
    }

    public void setEmmission(Color _emmission) {
        this._emmission = new Color(_emmission.getRGB());
    }

    public void setKs(double ks) {
        _material.setKs(ks);
    }

    public void setKd(double kd) {
        _material.setKd(kd);
    }

    public void setKr(double Kr) {
        _material.setKr(Kr);
    }

    public void setKt(double Kt) {
        _material.setKt(Kt);
    }
    public void setKg(double Kg) {
        _material.setKg(Kg);
    }
    
    public void setN (double N)
    {
        _material.setN(N);
    }

    public abstract Vector getNormal(Point3D p);

    public abstract List<Point3D> findIntersections(Ray ray);

  
}
