/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author User
 */
public class SceneDescriptor extends DefaultHandler {

    public Map<String, String> _sceneAttributes;
    public Map<String, String> _cameraAttributes;
    public Map<String, String> _ambientLightAttributes;
    public List<Map<String, String>> _planes;
    public List<Map<String, String>> _spheres;
    public List< Map<String, String>> _triangles;
    public List< Map<String, String>> _lights;
    public List< Map<String, String>> pointLights;
    public List< Map<String, String>> spotLights;
    public List< Map<String, String>> directionalLights;
    public List< Map<String, String>> _cylinders;

    public SceneDescriptor() {
        _sceneAttributes = new HashMap<>();
        _cameraAttributes = new HashMap<>();
        _ambientLightAttributes = new HashMap<>();
        _planes = new ArrayList<>();
        _spheres = new ArrayList<>();
        _triangles = new ArrayList<>();
        pointLights = new ArrayList<>();
        spotLights = new ArrayList<>();
        directionalLights = new ArrayList<>();
        _lights = new ArrayList<>();
        _cylinders = new ArrayList<>();
    }

    String xmlString = "";

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (qName) {
            case "scene":
                _sceneAttributes.put("background-color", attributes.getValue("background-color"));
                _sceneAttributes.put("screen-width", attributes.getValue("screen-width"));
                _sceneAttributes.put("screen-height", attributes.getValue("screen-height"));
                _sceneAttributes.put("screen-distance", attributes.getValue("screen-distance"));
                _sceneAttributes.put("scene-name", attributes.getValue("scene-name"));
                break;
            case "ambiant-light":
                _ambientLightAttributes.put("color", attributes.getValue("color"));
                break;
            case "camera":
                _cameraAttributes.put("PO", attributes.getValue("PO"));
                _cameraAttributes.put("vTo", attributes.getValue("vTo"));
                _cameraAttributes.put("vUp", attributes.getValue("vUp"));
                break;
            case "plane":
                Map<String, String> p = new HashMap<>();
                p.put("q", attributes.getValue("q"));
                p.put("n", attributes.getValue("n"));
                p.put("material", attributes.getValue("material"));
                p.put("nShininess", attributes.getValue("nShininess"));
                p.put("emmission", attributes.getValue("emmission"));
                p.put("shadow", attributes.getValue("shadow"));
                _planes.add(p);
                break;
            case "sphere":
                Map<String, String> sp = new HashMap<>();
                sp.put("center", attributes.getValue("center"));
                sp.put("radius", attributes.getValue("radius"));
                sp.put("material", attributes.getValue("material"));
                sp.put("nShininess", attributes.getValue("nShininess"));
                sp.put("emmission", attributes.getValue("emmission"));
                sp.put("shadow", attributes.getValue("shadow"));
                _spheres.add(sp);
                break;
            case "triangle":
                Map<String, String> tr = new HashMap<>();
                tr.put("P0", attributes.getValue("P0"));
                tr.put("P1", attributes.getValue("P1"));
                tr.put("P2", attributes.getValue("P2"));
                tr.put("material", attributes.getValue("material"));
                tr.put("nShininess", attributes.getValue("nShininess"));
                tr.put("emmission", attributes.getValue("emmission"));
                tr.put("shadow", attributes.getValue("emmission"));
                _triangles.add(tr);
                break;
            case "directional-light":
                Map<String, String> ld = new HashMap<>();
                ld.put("color", attributes.getValue("color"));
                ld.put("position", attributes.getValue("position"));
                ld.put("area", attributes.getValue("area"));
                ld.put("direction", attributes.getValue("direction"));
                ld.put("shadow", attributes.getValue("shadow"));
                directionalLights.add(ld);
                break;
            case "point-light":
                Map<String, String> lp = new HashMap<>();
                lp.put("color", attributes.getValue("color"));
                lp.put("area", attributes.getValue("area"));
                lp.put("position", attributes.getValue("position"));
                lp.put("Kc_Kl_Kq", attributes.getValue("Kc_Kl_Kq"));
                lp.put("shadow", attributes.getValue("shadow"));
                pointLights.add(lp);
                break;
            case "spot-light":
                Map<String, String> ls = new HashMap<>();
                ls.put("color", attributes.getValue("color"));
                ls.put("direction", attributes.getValue("direction"));
                ls.put("area", attributes.getValue("area"));
                ls.put("position", attributes.getValue("position"));
                ls.put("Kc_Kl_Kq", attributes.getValue("Kc_Kl_Kq"));
                ls.put("shadow", attributes.getValue("shadow"));
                spotLights.add(ls);
                break;
            case "cylinder":
                Map<String, String> cy = new HashMap<>();
                cy.put("position", attributes.getValue("position"));
                cy.put("direction", attributes.getValue("direction"));
                cy.put("radius", attributes.getValue("radius"));
                cy.put("material", attributes.getValue("material"));
                cy.put("nShininess", attributes.getValue("nShininess"));
                cy.put("emmission", attributes.getValue("emmission"));
                cy.put("shadow", attributes.getValue("shadow"));
                _cylinders.add(cy);
                break;

            default:
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        for (int i = start; i < (start + length); i++) {
        }
    }

    public void InitializeFromXMLstring(String xmlText) throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(this);
        p.parse(xmlText);
    }

}
