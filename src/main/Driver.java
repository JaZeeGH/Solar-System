/**
 * @author JaZeeGH
 * @version 1.0
 */
package main;

import controllers.PlanetSystemAPI;
import models.GasPlanet;
import models.IcePlanet;
import models.Planet;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {


    private PlanetSystemAPI PlanetSystemAPI = new PlanetSystemAPI();


    public static void main(String[] args) throws Exception {
        new Driver().start();
    }

    //This method is calling the Main Menu to start the Program.
    public void start() {

        runMainMenu();
    }
//Constructing Menus - Main Menu, CRUD Menu and Report Menu are running under different methods.

    private int mainMenu() {

        //TODO write menu that user will see
        return ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |                       Solar System Menu                        |
                ------------------------------------------------------------------
                ---------------------------Space Place----------------------------
                |   1) Planets CRUD Menu                                         |
                |                                                                |
                |   2) Reports MENU                                              |
                ------------------------------------------------------------------
                |   3) Search Planets by name                                    |
                    4) Search Planets by ID                                      |
                |   5) Sort Planets by Diameter (Ascending)                      |
                |   6) Sort Planets by Diameter (Descending)                     |
                ------------------------------------------------------------------
                |   10) Save all                                                 |
                |   11) Load all                                                 |
                ------------------------------------------------------------------
                |   0)  Exit                                                     |
                ------------------------------------------------------------------
                ==>>  """);
    }


    private void runMainMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> runCrudMenu();
                case 2 -> runReportMenu();
                case 3 -> searchPlanetsByName();
                case 4 -> searchPlanetsById();
                case 5 -> sortPlanetsDiameterAscending();
                case 6 -> sortPlanetsDiameterDescending();

                case 10 -> savePlanets();
                case 11 -> loadPlanets();
                default -> System.out.println("Invalid option entered: " + option);
                case 0 -> exitApp();
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();

        }
    }

    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }


    private int crudMenu() {

        return ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                -----------------------Planet Object Menu-------------------------
                |   1) Add a Planet Object                                       |
                |   2) Delete a Planet                                           |
                |   3) List all Planet Object                                    |
                |   4) Update Planet Object                                      |
                |   0) Return to Main Menu                                       |
                ------------------------------------------------------------------
                ==>>  """);
    }

    private void runCrudMenu() {
        int option = crudMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> addPlanet();
                case 2 -> deletePlanet();
                case 3 -> listPlanets();
                case 4 -> updatePlanet();
                case 0 -> runMainMenu();
                default -> System.out.println("Invalid option entered: " + option);


            }
            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the CRUD menu again
            option = crudMenu();
        }
    }

    private int reportMenu() {
        return ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                -----------------------Reports Menu-------------------------------
                |   1) Number of Planets                                         |
                |   2) Number of Gas Planets                                     |
                |   3) Number of Ice Planets                                     |
                |   4) List Planets which are heavier than                       |
                |   5) List Planets which are smaller than                       |
                |   6) Top Five Biggest Planets                                  |
                |   7) Top Five Highest Radiation Planets                        |
                |   0) Return to Main Menu                                       |
                ------------------------------------------------------------------
                ==>>  """);
    }

    private void runReportMenu() {
        int option = reportMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> printNumberOfAllPlanetObjects();
                case 2 -> printNumberOfGasPlanetObjects();
                case 3 -> printNumberOfIcePlanetObjects();
                case 4 -> printPlanetsHeavierThan();
                case 5 -> printPlanetsSmallerThan();
                case 6 -> listFiveBiggestPlanet();
                case 7 -> listTopHighestRadiationPlanet();
                case 0 -> runMainMenu();
                default -> System.out.println("Invalid option entered: " + option);


            }
            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the Report menu again
            option = reportMenu();
        }
    }

    public void printNumberOfAllPlanetObjects() { //listing all planets in the planet system
        System.out.println("The Total number of the Planets in this Planet System is: ");
        System.out.println(PlanetSystemAPI.numberOfPlanets());
    }

    public void printNumberOfGasPlanetObjects() { //listing all gas planets in the planet system
        System.out.println("The Total number of Gas Planets in this Planet System is: ");
        System.out.println(PlanetSystemAPI.numberOfGasPlanets());
    }

    public void printNumberOfIcePlanetObjects() { //listing all ice planets in the planet system
        System.out.println("The Total number of Ice Planets in this Planet System is: ");
        System.out.println(PlanetSystemAPI.numberOfIcePlanets());
    }

    private void printPlanetsHeavierThan() { //the user has to provide a mass value to look for planets in the planet system which are heavier than that
        double mass = ScannerInput.readNextDouble("View the planets which are heavier than (please enter a number):  ");
        System.out.println(PlanetSystemAPI.listAllPlanetObjectsHeavierThan(mass));
    }

    private void printPlanetsSmallerThan() { //the user has to provide a diameter value to look for planets in the planet system which are smaller than that
        double diameter = ScannerInput.readNextDouble("View the planets which smaller than (please enter a number):  ");
        System.out.println(PlanetSystemAPI.listAllPlanetObjectsSmallerThan(diameter));
    }

    private void addPlanet() { //add planet method to fill in the planet system with planets.
        //it contains a sub-menu to choose whether a gas planet or an ice planet is getting entered
        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                ---------------------------
                |   1) Add a Gas Planet   |
                |   2) Add an Ice Planet  |
                ---------------------------
                ==>> """);

        switch (option) {
            case 1 -> { //creating a gas planet object
                String name = ScannerInput.readNextLine("Enter the name of the planet: ");
                double mass = ScannerInput.readNextDouble("Enter the mass of the planet: ");
                double diameter = ScannerInput.readNextDouble("Enter the diameter of the planet: ");
                double averageTemperature = ScannerInput.readNextDouble("Enter the average temperature of the planet: ");
                String surfaceType = ScannerInput.readNextLine("Enter the surface type of the planet: ");
                char liquidWaterPlanet = ScannerInput.readNextChar("Is there liquid water on this planet? (Y/N): ");
                boolean hasLiquidWater = Utilities.YNtoBoolean(liquidWaterPlanet);
                String gasComposition = ScannerInput.readNextLine("Enter the gas composition of the planet: ");
                String coreComposition = ScannerInput.readNextLine("Enter the core composition of the planet: ");
                double radiationLevel = ScannerInput.readNextDouble("Enter the radiation level of the planet: ");
                isAdded = PlanetSystemAPI.addPlanetObject(new GasPlanet(name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater, gasComposition, coreComposition, radiationLevel));
            }
            case 2 -> { //creating an ice planet object
                String name = ScannerInput.readNextLine("Enter the name of the planet: ");
                double mass = ScannerInput.readNextDouble("Enter the mass of the planet: ");
                double diameter = ScannerInput.readNextDouble("Enter the diameter of the planet: ");
                double averageTemperature = ScannerInput.readNextDouble("Enter the average temperature of the planet: ");
                String surfaceType = ScannerInput.readNextLine("Enter the surface type of the planet: ");
                char liquidWaterPlanet = ScannerInput.readNextChar("Is there liquid water on this planet? (Y/N): ");
                boolean hasLiquidWater = Utilities.YNtoBoolean(liquidWaterPlanet);
                String iceComposition = ScannerInput.readNextLine("Enter the ice composition of the planet: ");
                isAdded = PlanetSystemAPI.addPlanetObject(new IcePlanet(name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater, iceComposition));
            }
        }

        if (isAdded) {
            System.out.println("Planet Added Successfully");

        } else {
            System.out.println("No Planet Added");
        }
    }


    private void deletePlanet() { //deleting a planet it also contains a sub-menu to decide whether we want to enter a planet id to delete or an index number to delete the given planet.

        int option = ScannerInput.readNextInt("""
                ---------------------------------
                |   1) Delete Planet by Index   |
                |   2) Delete Planet by ID      |
                ---------------------------------
                ==>> """);


        switch (option) {
            case 1 -> deletePlanetbyIndex();
            case 2 -> deletePlanetByID();
        }
    }

    private void deletePlanetByID() { //I built together the search method for Planet ID and the delete method for int index, this way I was able to delete planet by planet id.
        listPlanets();
        if (PlanetSystemAPI.numberOfPlanets() > 0) {
            int planetId = ScannerInput.readNextInt("Enter the planet id of the planet to delete ==> ");
            int result = PlanetSystemAPI.searchForPlanetID(planetId);
            if (result >= 0) {
                Planet planetToDelete = PlanetSystemAPI.deletePlanetByID(result);
                if (planetToDelete != null) {
                    System.out.println("Planet Deleted Successfully! Deleted Planet: " + planetToDelete.getName());
                } else {
                    System.out.println("Delete not successful");
                }
            }
        }

    }


    private void deletePlanetbyIndex() { // deleting planets by indext number

        listPlanets();
        if (PlanetSystemAPI.numberOfPlanets() > 0) {
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the planet to delete ==> ");

            Planet planetToDelete = PlanetSystemAPI.deletePlanetByIndex(indexToDelete);
            if (planetToDelete != null) {
                System.out.println("Planet Deleted Successfully! Deleted Planet: " + planetToDelete.getName());
            } else {
                System.out.println("Delete not successful");
            }
        }
    }

    private void listPlanets() { //list planets method to be able to see all the planets for overview, deleting, updating etc.
        System.out.println("List of Planets are:");
        System.out.println(PlanetSystemAPI.listAllPlanets());
    }

    private void listIcePlanets() { //listing all ice planets
        System.out.println("List of Ice Planets are:");
        System.out.println(PlanetSystemAPI.listAllIcePlanets());
    }

    private void listGasPlanets() { //listing all gas planets
        System.out.println("List of Gas Planets are:");
        System.out.println(PlanetSystemAPI.listAllGasPlanets());
    }

    private void updatePlanet() { //giving an update Planet sub-menu to update either gas or ice planets.
        if (PlanetSystemAPI.numberOfPlanets() > 0) {


            int option = ScannerInput.readNextInt("""
                    ------------------------------
                    |   1) Update a Gas Planet   |
                    |   2) Update an Ice Planet  |
                    ------------------------------
                    ==>> """);

            switch (option) {
                case 1 -> {
                    listGasPlanets();
                    if (PlanetSystemAPI.numberOfGasPlanets() > 0) {
                        int idToUpdate = ScannerInput.readNextInt("Enter the index of the planet to update ==> ");
                        if (PlanetSystemAPI.isValidId(idToUpdate) != false) {
                            String name = ScannerInput.readNextLine("Enter the name of the planet: ");
                            double mass = ScannerInput.readNextDouble("Enter the mass of the planet: ");
                            double diameter = ScannerInput.readNextDouble("Enter the diameter of the planet: ");
                            double averageTemperature = ScannerInput.readNextDouble("Enter the average temperature of the planet: ");
                            String surfaceType = ScannerInput.readNextLine("Enter the surface type of the planet: ");
                            char liquidWaterPlanet = ScannerInput.readNextChar("Is there liquid water on this planet? (Y/N): ");
                            boolean hasLiquidWater = Utilities.YNtoBoolean(liquidWaterPlanet);
                            String gasComposition = ScannerInput.readNextLine("Enter the gas composition of the planet: ");
                            String coreComposition = ScannerInput.readNextLine("Enter the core composition of the planet: ");
                            double radiationLevel = ScannerInput.readNextDouble("Enter the radiation level of the planet: ");

                            if (PlanetSystemAPI.updateGasPlanet(idToUpdate, name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater, gasComposition, coreComposition, radiationLevel)) { //the update recreated the gasplanet object with the new information provided by the user
                                System.out.println("Planet Updated Successfully");
                            } else {
                                System.out.println("Planet Update not successful");
                            }
                        } else {
                            System.out.println("There is no Gas Planet with this index number");
                        }
                    } else {
                        System.out.println("There is no Gas Planet to update");
                    }

                }
                case 2 -> {
                    listIcePlanets();
                    if (PlanetSystemAPI.numberOfIcePlanets() > 0) {
                        int idToUpdate = ScannerInput.readNextInt("Enter the index of the planet to update ==> ");
                        if (PlanetSystemAPI.isValidId(idToUpdate) != false) {
                            String name = ScannerInput.readNextLine("Enter the name of the planet: ");
                            double mass = ScannerInput.readNextDouble("Enter the mass of the planet: ");
                            double diameter = ScannerInput.readNextDouble("Enter the diameter of the planet: ");
                            double averageTemperature = ScannerInput.readNextDouble("Enter the average temperature of the planet: ");
                            String surfaceType = ScannerInput.readNextLine("Enter the surface type of the planet: ");
                            char liquidWaterPlanet = ScannerInput.readNextChar("Is there liquid water on this planet? (Y/N): ");
                            boolean hasLiquidWater = Utilities.YNtoBoolean(liquidWaterPlanet);
                            String iceComposition = ScannerInput.readNextLine("Enter the ice composition of the planet: ");

                            if (PlanetSystemAPI.updateIcePlanet(idToUpdate, name, mass, diameter, averageTemperature, surfaceType, hasLiquidWater, iceComposition)) { //the update recreated the ice planet object with the new information provided by the user
                                System.out.println("Planet Updated Successfully");
                            } else {
                                System.out.println("Planet Update not successful");
                            }
                        } else {
                            System.out.println("There is no Ice Planet with this index number");
                        }
                    } else {
                        System.out.println("There is no Ice Planet to update");
                    }
                }
            }
        } else {
            System.out.println("No Planets Found");
        }
    }

    //---------------------
    //  Search/Sort
    //---------------------

    private void searchPlanetsByName() { //search method to find the planets in the data base by name
        String name = ScannerInput.readNextLine("Please enter a Planet's name to search by: ");
        System.out.println(PlanetSystemAPI.searchByPlanetName(name));
    }

    private void searchPlanetsById() { //search method to find the planets in the data base by planet id
        int planetID = ScannerInput.readNextInt("Please enter a Planet ID to search for:");
        int result = PlanetSystemAPI.searchForPlanetID(planetID);
        if (result >= 0) System.out.println(PlanetSystemAPI.getPlanets().get(result));
        else System.out.println("That Planet does not exist in the list of products");
    }

    private void sortPlanetsDiameterAscending() { //sort method to list the planets according to the diameter in ascending order
        PlanetSystemAPI.sortByDiameterAscending();
        System.out.println("List of Planets in diameter(ascending) order)");
        System.out.println(PlanetSystemAPI.listAllPlanets());
    }

    private void sortPlanetsDiameterDescending() {//sort method to list the planets according to the diameter in descending order
        PlanetSystemAPI.sortByDiameterDescending();
        System.out.println("List of Planets in diameter(descending) order)");
        System.out.println(PlanetSystemAPI.listAllPlanets());
    }

    private void listFiveBiggestPlanet() { //this method is listing the five biggest planets in the planet system
        System.out.println("List of Top Five Biggest Planets is:");
        System.out.println(PlanetSystemAPI.topFiveBiggestPlanet());
    }

    private void listTopHighestRadiationPlanet() { //this method is listing five planets with the highest radiation level in the planet system
        System.out.println("List of Top Highest Radiation Planets is:");
        System.out.println(PlanetSystemAPI.topFiveHighestRadiationPlanet());
    }

    private void savePlanets() { //save all the posts in the planet system to a file on the hard disk
        try {
            System.out.println("Saving to file: " + PlanetSystemAPI.fileName());
            PlanetSystemAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadPlanets() { //load the Planet data from the xml file
        try {
            System.out.println("Loading from file: " + PlanetSystemAPI.fileName());
            PlanetSystemAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    private void displayGravity(int indexToShow) {

    }

}

