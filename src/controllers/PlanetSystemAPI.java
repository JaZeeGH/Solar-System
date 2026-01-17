/**
 * @author JaZeeGH
 * @version 1.0
 */
package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.GasPlanet;
import models.IcePlanet;
import models.Planet;
import utils.ISerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static utils.Utilities.isValidIndex;


public class PlanetSystemAPI implements ISerializer {

    /**
     * As per the clarification in the Assignment Slack channel this is to create List and Array List for the planets
     * and create a private file field
     */
    //as per the clarification in the Assignment Slack channel this is to create List and Array List for the planets and create a private file field
    private List<Planet> planets;
    private File planetsFile;

    /**
     * This is the constructor of the Planet System.
     * The Planets from the Planet Array List are creating a new Array List and new planetsFile is getting created with
     * the file name of planets.xml
     */
    //The constructor will create our Planet System for the project in the Java List and in the File.
    public PlanetSystemAPI() {
        this.planets = new ArrayList<Planet>();
        this.planetsFile = new File("planets.xml");
    }

    /**
     * This method is needed to fill up the planet system with planets.
     *
     * Array List of Planet planet array list  The information printed to the console for the user to read
     * @return the new planet object is getting added to the array list
     */
    //The CRUD Methods
    //The new planet will be part of the array
    public boolean addPlanetObject(Planet planet) {
        return planets.add(planet);
    }

    /**
     * Read an int index from the user.  If the entered number is an existing index number for an existing planet
     * object the planet at the given index number will be removed.
     *
     * @param indexToDelete
     * @return removing the planet object from the Array List
     */
    //This method removes an Planet object at the location index, which is passed as a parameter.
    public Planet deletePlanetByIndex(int indexToDelete) {
        if (isValidIndex(planets, indexToDelete)) {
            return planets.remove(indexToDelete);
        }
        return null;
    }

    /**
     * This method removes a Planet object with the id which is passed as a parameter.
     * The isValidID process confirms that this is an existing planet ID and removes the planet from the array list
     *
     * @param planetId
     * @return deletes the given planet.
     */
    public Planet deletePlanetByID(int planetId) {
        if (isValidId(planetId)) {
            return planets.remove(planetId);
        }
        return null;
    }

    /**
     * This method returns a Planet object at the location index, which is passed as a parameter.
     * There is a validation process in place to review whether the index is used by a planet object at the time.
     *
     * @param indexToGet an int
     * @return the planet object which belongs to the given index.
     */
    //This method returns an Planet object at the location index, which is passed as a parameter.
    public Planet getPlanetByIndex(int indexToGet) {
        if (isValidIndex(planets, indexToGet)) {
            return planets.get(indexToGet);
        }
        return null;
    }

    /**
     * This Planet method returns a Planet object by a planet id, which is passed as a parameter.
     * There is a validation process in place to review whether the planet id is used by a planet object at the time.
     *
     * @param planetId int
     * @return the planet object which belongs to the given planetId.
     */
    //This method returns a Planet object with that exact id (ignoring case), which is passed as a parameter
    public Planet getPlanetByID(int planetId) {
        if (isValidId(planetId)) {
            return planets.get(planetId);
        }
        return null;
    }

    //Reporting Methods

    /**
     * This String method reviews the planet objects in the planet Array List.
     *
     * @return This method should return a String containing the details of all the Planet in planetList along with the
     * index number associated with each Planet. If no Planet exist yet, “No Planets” should be returned.
     */
    //This method should return a String containing the details of all the Planet in planetList along with the index number associated with each Planet. If no Planet exist yet, “No Planets” should be returned.
    public String listAllPlanets() {
        String str = "";
        for (Planet planet : planets) {
            str += planets.indexOf(planet) + ": " + planet.toString();
        }
        if (str.isEmpty()) {
            return "No Planets";
        } else {
            return str;
        }
    }

    /**
     * This String method reviews the planet objects in the planet Array List. If it finds planets which are within the
     * Gas Planet class (are instance of that class)
     *
     * @return This method should return a String containing the details of all the Gas Planet in planetList along with
     * the index number associated with each Gas Planet. If no Gas Planet exist yet, “No Gas Planets” should be returned.
     */
    //This method should return a String containing the details of all the Gas Planets in planetList along with the
    //index number associated with each gas planet. If no gas planets exist yet, “No Gas Planets” should be returned.
    public String listAllGasPlanets() {
        String str = "";
        for (Planet planet : planets) {
            if (planet instanceof GasPlanet) {
                str += planets.indexOf(planet) + ": " + ((GasPlanet) planet).toString();
            }
        }
        if (str.isEmpty()) {
            return "No Gas Planets";
        } else {
            return str;
        }
    }

    /**
     * This String method reviews the planet objects in the planet Array List. If it finds planets which are within the Ice
     * Planet class (are instance of that class)
     *
     * @return This method should return a String containing the details of all the Ice Planet in planetList along with
     * the index number associated with each Gas Planet. If no Ice Planet exist yet, “No Ice Planets” should be returned.
     */
    //This method should return a String containing the details of all the Ice Planets in planetList along with the
    // index number associated with each ice planet. If no ice planets exist yet, “No Ice Planets” should be returned.
    public String listAllIcePlanets() {
        String str = "";
        for (Planet planet : planets) {
            if (planet instanceof IcePlanet) {
                str += planets.indexOf(planet) + ": " + ((IcePlanet) planet).toString();
            }
        }
        if (str.isEmpty()) {
            return "No Ice Planets";
        } else {
            return str;
        }
    }

    /**
     * This String method reviews confirms whether there are any planets in the planet system if there are none it returns the
     * no planet information. If there are it will review each planet in the planet system and will compare the mass
     * of the planets with the value which was entered by the user. If the planets are heavier than that value they will
     * be returned in a String.
     *
     * @param mass (value entered by the user later)
     * @return This method should return the list of Planet equal or above the entered mass. If there are no planets
     * heavier than the given value it returns a sting with "No planet heavier than"
     */
    //This method should return the list of Planet equal or above the entered mass .
    public String listAllPlanetObjectsHeavierThan(double mass) {
        if (planets.isEmpty()) {
            return "No Planets";
        } else {
            String str = "";
            for (Planet planet : planets) { //I turned to Shop v9 listProductsAbovePrice into a for each loop here
                if (planet.getMass() > mass) {
                    str += planets.indexOf(planet) + ": " + ((Planet) planet).toString();
                }
            }
            if (str.isEmpty()) {
                return "No Planet heavier than " + mass;
            } else {
                return str;
            }
        }
    }

    /**
     * This String method reviews confirms whether there are any planets in the planet system if there are none it returns the
     * no planet information. If there are it will review each planet in the planet system and will compare the diameter
     * of the planets with the value which was entered by the user. If the planets are smaller than that value they will
     * be returned in a String.
     *
     * @param  diameter (value entered by the user later)
     * @return This method should return the list of Planet equal or below the entered diameter. If there are no planets
     * heavier than the given value it returns a sting with "No Planet smaller than "
     */
    //This method should return the list of Planet equal or below the entered diameter.
    public String listAllPlanetObjectsSmallerThan(double diameter) {
        if (planets.isEmpty()) {
            return "No Planets";
        } else {
            String str = "";
            for (Planet planet : planets) {
                if (planet.getDiameter() < diameter) {
                    str += planets.indexOf(planet) + ": " + ((Planet) planet).toString();
                }
            }
            if (str.isEmpty()) {
                return "No Planet smaller than " + diameter;
            } else {
                return str;
            }
        }
    }

    //Number methods

    /**
     * This int method returns the number of Planets in the system (in planetList).
     *
     * @return Number of planets
     */
    //This method returns the number of Planets in the system (in planetList).
    public int numberOfPlanets() {
        return planets.size();
    }

    /**
     * This int method reviews the Planets in the system (in planetList) then it reviews that how many Gas Planets are
     * within the planets and return the number of those
     *
     * @return Number of gas planets
     */
    //This method returns the number of gas planets in the system (in planetList).
    public int numberOfGasPlanets() {
        int number = 0;
        for (Planet planet : planets) {
            if (planet instanceof GasPlanet) {
                number++;
            }
        }
        return number;
    }

    /**
     * This int method reviews the Planets in the system (in planetList) then it reviews that how many Ice Planets are
     * within the planets and return the number of those
     *
     * @return Number of ice planets
     */
    //This method returns the number of ice planets in the system (in planetList).
    public int numberOfIcePlanets() {
        int number = 0;
        for (Planet planet : planets) {
            if (planet instanceof IcePlanet) {
                number++;
            }
        }
        return number;
    }

    /**
     * This boolean method takes in a id and replaces the corresponding object with the IcePlanet as input (updatedDetails).
     * - I used SocialNetwork V9.0 as an example
     * If there planets found and those planets are ice planets (instance of the ice planet class) we can set their
     * intake parameters, this will replace the existing ice planet
     *
     * @param  idToUpdate,  name,  mass,  diameter,  averageTemperature,  surfaceType,
     *             hasLiquidWater,  iceComposition
     * @return updated ice planet object to the array list
     */
    //Update Methods
    //This method takes in a id and replaces the corresponding object with the IcePlanet as input (updatedDetails). - I used SocialNetwork V9.0 as an example
    public boolean updateIcePlanet(int idToUpdate, String name, double mass, double diameter, double averageTemperature, String surfaceType, boolean hasLiquidWater, String iceComposition) {
        Planet foundPlanet = getPlanetByID(idToUpdate);

        if ((foundPlanet != null) && (foundPlanet instanceof IcePlanet)) {
            foundPlanet.setName(name);
            foundPlanet.setMass(mass);
            foundPlanet.setDiameter(diameter);
            foundPlanet.setAverageTemperature(averageTemperature);
            foundPlanet.setSurfaceType(surfaceType);
            foundPlanet.setHasLiquidWater(hasLiquidWater);
            ((IcePlanet) foundPlanet).setIceComposition(iceComposition);
        }
        return false;
    }

    /**
     * This boolean method takes in a id and replaces the corresponding object with the Gas Planet as input (updatedDetails).
     * - I used SocialNetwork V9.0 as an example
     * If there are planets found and those planets are gas planets (instance of the gas planet class) we can set their
     * intake parameters, this will replace the existing ice planet
     *
     * @param  idToUpdate, name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater, gasComposition, coreComposition,  radiationLevel
     * @return updated gas planet object to the array list
     */
    //This method takes in a id and replaces the corresponding object with the GasPlanet as input (updatedDetails).
    public boolean updateGasPlanet(int idToUpdate, String name, double mass, double diameter, double averageTemperature, String surfaceType, boolean hasLiquidWater, String gasComposition, String coreComposition, double radiationLevel) {
        //finding the planet by the id
        Planet foundPlanet = getPlanetByID(idToUpdate);

        if ((foundPlanet != null) && (foundPlanet instanceof GasPlanet)) {
            foundPlanet.setName(name);
            foundPlanet.setMass(mass);
            foundPlanet.setDiameter(diameter);
            foundPlanet.setAverageTemperature(averageTemperature);
            foundPlanet.setSurfaceType(surfaceType);
            foundPlanet.setHasLiquidWater(hasLiquidWater);
            ((GasPlanet) foundPlanet).setGasComposition(gasComposition);
            ((GasPlanet) foundPlanet).setCoreComposition(coreComposition);
            ((GasPlanet) foundPlanet).setRadiationLevel(radiationLevel);
        }
        return false;
    }

    //Validation Method

    /**
     * This boolean method reviews every planet objects in the planet system and checks whether there is a planet with the
     * given planet id in the system
     *
     * @param  planetId
     * @return either returns confirming that the id valid or it is false
     */
    //the following is isValidId I updated it with my naming
    public boolean isValidId(int planetId) {
        for (Planet Planet : planets) {
            if (Planet.getPlanetId() == (planetId)) { // I had to change the .equals to == because otherwise I received an error because int is primitive and cannot be dereferenced.
                return false;
            }
        }
        return true;
    }

    /**
     * Getter for Planet Array List
     *
     * @return planet objects
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Getter for the File to store the details on the hard drive (or other storage)
     *
     * @return the File for the planets
     */
    public File getPlanetsFile() {
        return planetsFile;
    }

    /**
     * Setter for Planet Array List
     */
    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Setter for the File
     */
    public void setPlanetsFile(File planetsFile) {
        this.planetsFile = planetsFile;
    }

    //Sort Methods

    /**
     * Selection sort algorithm for sorting the arraylist of planets by diameter size descending.
     */
    //This method should change the planetList object so that it is sorted by diameter in descending order.
    public void sortByDiameterDescending() {
        for (int i = planets.size() - 1; i >= 0; i--) {
            int biggestDiameter = 0;
            for (int j = 0; j <= i; j++) {
                if (planets.get(j).getDiameter() < planets.get(biggestDiameter).getDiameter()) {
                    biggestDiameter = j;
                }
            }
            swapPlanet(planets, i, biggestDiameter);
        }
    }

    /**
     * Selection sort algorithm for sorting the arraylist of planets by diameter size ascending.
     */
    //This method should change the planetList object so that it is sorted by diameter in descending order.
    public void sortByDiameterAscending() {
        for (int i = planets.size() - 1; i >= 0; i--) {
            int biggestDiameter = 0;
            ;
            for (int j = 0; j <= i; j++) {
                if (planets.get(j).getDiameter() > planets.get(biggestDiameter).getDiameter()) {
                    biggestDiameter = j;
                }
            }
            swapPlanet(planets, i, biggestDiameter);
        }
    }


    /**
     * A String method after the Selection sort algorithm for sorting the arraylist of planets by diameter size descending.
     * I added an extra for loop compared to the sort selection this for loop makes sure that only upto 5 elements
     * will be returned in the String
     */
    //returns a List of the top 5 biggest planets by diameter
    public String topFiveBiggestPlanet() {
        String str = "";
        for (int i = planets.size() - 1; i >= 0; i--) {
            int biggestDiameter = 0;
            for (int j = 0; j <= i; j++) {
                if (planets.get(j).getDiameter() < planets.get(biggestDiameter).getDiameter()) {
                    biggestDiameter = j;
                }
            }
            swapPlanet(planets, i, biggestDiameter);
        }
        for (int i = 0; i < 5 && i < planets.size(); i++) {
            str += planets.get(i);

        }
        return str;
    }


    /**
     * A String method after the Selection sort algorithm for sorting the arraylist of gas planets within the planets
     * (instance of) by radiation level size descending.
     * I attempted to create a new array list for the GasPlanets which are in the Planets Array list (instance of)
     * I was able to get the radiation level for the GasPlanets working.
     * I added an extra for loop compared to the sort selection this for loop makes sure that only upto 5 elements
     * will be returned in the String
     * The code has a bug because it returns 5 gas planets but not with the correct descending order of radiation level
     * I think this is caused by the swap as I was not able to use gasPlanets there but it might be something else.
     *
     * @return a String
     */
    public String topFiveHighestRadiationPlanet() {
        String str = "";
        List<GasPlanet> gasPlanets = new ArrayList<>(); //creating a new Array List for GasPlanets
        for (Planet planet : planets) {
            if (planet instanceof GasPlanet) { //planet in the Planet planet Array List if they are GasPlanets they will be added to the new Array List
                gasPlanets.add((GasPlanet) planet);
            }
        }
        for (int i = gasPlanets.size() - 1; i >= 0; i--) { // I reused the formula of the topFiveBiggestPlanet and replaced the planets with the gasPlanets elements
            int highestRadiationLevel = 0;  //as otherwise the code did not understood when I called for the radiationLevel.
            for (int j = 0; j <= i; j++) {
                if (gasPlanets.get(j).getRadiationLevel() < gasPlanets.get(highestRadiationLevel).getRadiationLevel()) {
                    highestRadiationLevel = j;
                }
            }
            swapPlanet(planets, i, highestRadiationLevel);
        }
        for (int i = 0; i < 5 && i < gasPlanets.size(); i++) { //this loops 5 times to provide only the top five radiation level planets
            str += gasPlanets.get(i);
        }
        return str; //it returns a string with 5 planets but the radiation levels are not correct so there is a bug in my code here, unfortunately.
    }


    /**
     * This method returns the swap of the planets in the Planet Array List. This is a helper method for sorting.
     *
     * @param planets, int i, int j
     * @return changes positions of the objects within the array list.
     */
    //This should be a private method that swaps the objects at positions i and j in the collection planetList. This method should be used in your sorting method.
    private void swapPlanet(List<Planet> planets, int i, int j) {
        Planet smaller = planets.get(i);
        Planet bigger = planets.get(j);

        planets.set(i, bigger);
        planets.set(j, smaller);
    }

    /**
     * This method is used to search for single planets using by name, it returns one planet.
     *
     * @return matchingPlanets
     * @paramname String name
     */
    public String searchByPlanetName(String name) {
        String matchingPlanets = "";
        for (Planet planet : planets) {
            if (planet.getName().toUpperCase().contains(name.toUpperCase())) {
                matchingPlanets += planets.indexOf(planet) + ": " + planet + "\n";
            }
        }

        if (matchingPlanets.equals("")) {
            return "No planet match your search";
        } else {
            return matchingPlanets;
        }
    }

    /**
     * This method is used to search for single planets using by Planet ID, it returns one planet.
     *
     * @param planetID
     * @return matchingPlanets
     */
    public int searchForPlanetID(int planetID) {
        String matchingProducts = "";
        for (int i = 0; i < planets.size(); i++) {
            if (planets.get(i).getPlanetId() == planetID) {
                return i;
            }
        }
        return -1;
    }


    //Persistence methods

    /**
     * The load method uses the XStream component to read all the product objects from the products.xml
     * file stored on the hard disk.  The read of products are loaded into the products ArrayList
     *
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    //To the load and save statements I looked up SocialNetwork V9 and adjusted the methods to the Assignment project
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{IcePlanet.class, GasPlanet.class, Planet.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("planets.xml"));
        planets = (List<Planet>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the product objects in the products ArrayList
     * to the products.xml file stored on the hard disk.
     *
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("planets.xml"));
        out.writeObject(planets);
        out.close();

    }

    /**
     * String method to create the filename to store the data on the hard disk
     *
     * @return the exact filename for the file system.
     */
    public String fileName() {
        return "planets.xml";
    }


}
