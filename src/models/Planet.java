/**
 * @author JaZeeGH
 * @version 1.0
 */
package models;

import utils.Utilities;


public abstract class Planet {


    private boolean hasLiquidWater = false; //this will be true or false whether there is water
    private String surfaceType = "";//rocky, metallic etc.
    private double averageTemperature = 0; //average temperature defaults to 0
    private double mass = 0.1; //the mass of the planet default to 0.1
    private String name = ""; //name of the planet
    private double diameter = 0.5;
    private int planetId = 1000;
    private static int nextID = 1000; //nextID static private field

    //Generating the Constructor
    public Planet(String name, double averageTemperature, double mass, double diameter, String surfaceType, boolean hasLiquidWater) {//constructing the planet, id is not part of the constructor
        this.name = Utilities.truncateString(name, 30); //name of the planet can be max 30 chars
        setAverageTemperature(averageTemperature);
        setMass(mass);
        setDiameter(diameter);
        this.surfaceType = Utilities.truncateString(surfaceType, 30);
        setHasLiquidWater(hasLiquidWater);
        this.planetId = nextID; //setting nextID as the planetID
        nextID++; //this is incrementing the nextID so there will be an ascending planetID
    }

    //Generating the getters
    public double getDiameter() {
        return diameter;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public boolean getHasLiquidWater() {
        return hasLiquidWater;
    }

    public int getPlanetId() {
        return planetId;
    }

    public static int getNextID() {
        return nextID;
    }

    //Generating the Setters
    public void setPlanetId(int planetId) {
        this.planetId = planetId;


    }

    public void setHasLiquidWater(boolean hasLiquidWater) {
        this.hasLiquidWater = hasLiquidWater;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = Utilities.truncateString(surfaceType, 20);
    }

    public void setAverageTemperature(double averageTemperature) {
        if (Utilities.validRange(averageTemperature, -400, 400)) {
            this.averageTemperature = averageTemperature;
        }
    }

    public void setMass(double mass) {
        if (Utilities.biggerThan(mass, 0.1)) {
            this.mass = mass;
        }
    }

    public void setName(String name) {
        this.name = Utilities.truncateString(name, 30);
    }

    public void setDiameter(double diameter) {
        if (Utilities.biggerThan(diameter, 0.5)) {
            this.diameter = diameter;
        }
    }

    public static void setNextID(int nextID) {
        Planet.nextID = nextID;
    }


    //  Calculate Gravity (mass multipled by 6.67430e-11) divided by (half the diameter to the power of 2)
    // I did not use this method in the Driver in the end.

    public double calculateGravity(double mass, double diameter) {
        double gravity = (mass * 6.67430e-11) / ((diameter / 2) * (diameter / 2));
        return gravity;
    }

    //super displayInfo
    public abstract String displayInfo();

    //super classifyBody
    public abstract String classifyBody();


    //String provides the information line about the Planet Information
    public String toString() {
        return "Planet ID: " + planetId
                + ", Name of Planet: " + name
                + ", Mass of Planet: " + mass
                + ", Diameter of Planet: " + diameter
                + ", The Planet has liquid water on its surface: " + utils.Utilities.booleanToYN(hasLiquidWater)
                + ", The surface of the Planet is: " + surfaceType
                + ", The average temperature on the Planet is: " + averageTemperature;

    }


}

