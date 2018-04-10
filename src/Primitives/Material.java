/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Primitives;

/**
 *
 * @author Shmuel & Elyasaf 
 * this class represents a Material of a geometry 
 * 
 * Variables included:
 * _Kd (double): Diffusion attenuation coefficient  
 * _Ks (double): Specular attenuation coefficient
 * _Kr (double): Reflection coefficient (1 for mirror)
 * _Kg (double): Reflection glossy (1 for blurry)
 * _Kt (double): Refraction coefficient (1 for transparent)  
 * _n  (double): Refraction index   
 *
 * functions included: 
 * 
 */
public class Material {
    private double _Kd; // Diffusion attenuation coefficient  
    private double _Ks; // Specular attenuation coefficient
    private double _Kr; // Reflection coefficient (1 for mirror)   
    private double _Kt; // Refraction coefficient (1 for transparent)  
    private double _n;  // Refraction index   
    private double _Kg=0; // Reflection glossy (1 for blurry)
    
// ***************** Constructors ********************** //  
    public Material()  {   _Kd = 1;    _Ks = 1;   _Kr = 0;    _Kt = 0;   _n  = 1; _Kg=0; } 

    public Material(double _Kd, double _Ks, double _Kr, double _Kt, double _n ,double _Kg) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._Kr = _Kr;
        this._Kt = _Kt;
        this._n = _n;
        this._Kg=_Kg;
    }
    
    public Material(Material material){
        this._Kd=material._Kd;
        this._Ks=material._Ks;
        this._Kr=material._Kr;
        this._Kt=material._Kt;
        this._n=material._n;
        this._Kg=material._Kg;
    } 
    
// ***************** Getters/Setters ********************** //  

    public double getKd() {
        return _Kd;
    }

    public void setKd(double _Kd) {
        this._Kd = _Kd;
    }

    public double getKs() {
        return _Ks;
    }

    public void setKs(double _Ks) {
        this._Ks = _Ks;
    }

    public double getKr() {
        return _Kr;
    }

    public void setKr(double _Kr) {
        this._Kr = _Kr;
    }

    public double getKt() {
        return _Kt;
    }

    public void setKt(double _Kt) {
        this._Kt = _Kt;
    }

    public double getN() {
        return _n;
    }

    public void setN(double _n) {
        this._n = _n;
    }

    public double getKg() {
        return _Kg;
    }

    public void setKg(double _Kg) {
        this._Kg = _Kg;
    }
}
