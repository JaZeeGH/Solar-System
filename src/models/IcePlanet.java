/**
 * @author JaZeeGH
 * @version 1.0
 */
package models;

import utils.Utilities;


public class IcePlanet extends Planet {
    public String iceComposition = "(30 chars)"; //sample value mostly ice

    //constructor for the IcePlanet
    public IcePlanet(String name, double mass, double diameter, double averageTemperature, String surfaceType, boolean hasLiquidWater, String iceComposition) {
        super(name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater);

        this.iceComposition = Utilities.truncateString(iceComposition, 30);
    }

    public String getIceComposition() {
        return iceComposition;
    }

    public void setIceComposition(String iceComposition) {
        this.iceComposition = Utilities.truncateString(iceComposition, 30);
    }

    // return the String with the field information e.g. Ice Composition: Mostly Ice
    @Override
    public String displayInfo() {
        return ", Ice Composition: " + iceComposition;
    }

    // return the String with the text "Ice Planet" in it
    @Override
    public String classifyBody() {
        return ", Ice Planet";
    }

    public String toString() {
        return super.toString() + displayInfo() + classifyBody() + "\n";
    }


}
