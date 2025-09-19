/**
 * @author JaZeeGH
 * @version 1.0
 */
package models;

import utils.CoreCompositionUtility;
import utils.Utilities;


public class GasPlanet extends Planet { //GasPlanet class which belongs to the Planet super class
    private String gasComposition = "(20 chars)"; //max 20 chars
    private String coreComposition = "UNKNOWN"; //Must be one of Rocky and Metallic, Proportionally Small, Liquid Metallic Hydrogen Compressed Hydrogen or Ice Giant default UNKNOWN can use CoreCompositionUtility class
    private double radiationLevel = 0.9; //Higher in gas giant min .01 max 200.05 default .9

    //creating the GasPlanet constructor
    public GasPlanet(String name, double mass, double diameter, double averageTemperature, String surfaceType, boolean hasLiquidWater, String gasComposition, String coreComposition, double radiationLevel) {
        super(name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater);
        setGasComposition(gasComposition);
        setCoreComposition(coreComposition);
        setRadiationLevel(radiationLevel);
    }

    public String getGasComposition() {
        return gasComposition;
    }

    public void setGasComposition(String gasComposition) {
        this.gasComposition = Utilities.truncateString(gasComposition, 20); //validates whether the String is the correct length
    }

    public String getCoreComposition() {
        return coreComposition;
    }

    public void setCoreComposition(String coreComposition) {
        if (CoreCompositionUtility.isValidCoreType(coreComposition)) { //using the Core Composition Utility and the isValidCoreType boolean to set the Core Type
            this.coreComposition = coreComposition;
        }
    }

    public double getRadiationLevel() {
        return radiationLevel;
    }

    public void setRadiationLevel(double radiationLevel) { //using validRange method of utilities to set the range of the radiatonLevel
        if (Utilities.validRange(radiationLevel, 0.01, 200.05)) {
            this.radiationLevel = radiationLevel;
        } else {
            this.radiationLevel = 0.9;
        }
    }

    //displayInfo method

    @Override
    public String displayInfo() {

        return ", Gas Composition: " + gasComposition + ", Core Composition: " + coreComposition + ", Radiation Level: " + radiationLevel;
    }

    //classifyBody method this is to return a String "Gas Planet"

    @Override
    public String classifyBody() {
        return ", Gas Planet";
    }

    //toString method
    public String toString() {
        return super.toString() + displayInfo() + classifyBody() + "\n";
    }

}
