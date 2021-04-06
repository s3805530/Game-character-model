
/**
 * Author:[Shrestha Ghosh]-[s3805530]
 */

import java.util.Scanner;

public class GameCharGen {

    /**
     *
     * This methods prints the display menu
     * 
     */

    public void displayMenu() {
        System.out.println("*** Wok GameCharGen Menu ***");
        System.out.println("Create Wizard               WZ");
        System.out.println("Create Fighter              FG");
        System.out.println("Create Clerci               CL");
        System.out.println("WisestOfThemAll             WC");
        System.out.println("WiserThanStrongAsWise       WS");
        System.out.println("Display Characters          DC");
        System.out.println("Exit Program                EX");
        System.out.print("Enter selection: ");
    }

	/**
     * ALGORITHM
     * 
     * BEGIN:
     * 
     * Displays the menu by calling diaplaymenu()
     * Takes input from the user
     * Calls function executeToRun() to run 
     * 
     * END
     * 
     */
   
    public void run() throws NoGameCharacterFoundException {
        GameCharGenModel myCharGen = new GameCharGenModel();
        Scanner menuInput = new Scanner(System.in);
       
       int i=1;
        String selection;
        // Implement Q1 & Q4 menu updates here
        // Q2, Q3, & Q4 will be implemented in the GameCharGenModel class
        do {
            displayMenu();
            selection = menuInput.nextLine();
            executeToRun(myCharGen, selection);
            i++;
        } while (i>1);
        menuInput.close();
	}
	
	 /**
     * ALGORITHM
     * 
     * BEGIN: 
     * 
     * Creates an object of GameCharacter 
     * Checks for each selection 
     * If matches the selection:
     *   Character is created and character values generated using randGen
     * IF WC is selected: 
     *   calls the function wisestOfThemAll() and displays highest wisdom points
     * IF WS is selected: 
     *   calls the function wiserThanStrongOrStrongAsWise() and filters the array
     * IF DC is selected: 
     *   calls the function display() and displays the array
     * IF EX is selected:
     *   exits the program
     * Adds charater to the array by calling addGameCharacter 
     * 
     * END
     * 
     * Description: 
     * selection is the input given by the user
     */

    public void executeToRun(GameCharGenModel myCharGen, String selection) throws NoGameCharacterFoundException {

        boolean maxhitpoints= true;
        GameCharacter ch = null;
        if (selection.equalsIgnoreCase("WZ")) {
            ch = new Wizard();
            ch.randGen(maxhitpoints);
        } else if (selection.equalsIgnoreCase("FG")) {
            ch = new Fighter();
            ch.randGen(false);
        } else if (selection.equalsIgnoreCase("CL")) {
            ch = new Cleric();
            ch.randGen(maxhitpoints);
        } else if (selection.equalsIgnoreCase("WC")) {
            String[] result = myCharGen.wisestOfThemAll();

            System.out
                    .println(result[1] + " " + "character at index #" + result[0] + " " + "is the wisest of them all");
            System.out.println(
                    result[1] + " " + "had" + " " + result[2] + " " + "points and the average was:" + " " + result[3]);

        } else if (selection.equalsIgnoreCase("WS")) {
            myCharGen.wiserThanStrongOrStrongAsWise();

        } else if (selection.equalsIgnoreCase("DC")) {
            System.out.println(myCharGen.display());

        } else if (selection.equalsIgnoreCase("EX")) {
            System.exit(0);
        }
          else {
            System.out.println("Unrecognised selection.");
        }
        if (ch != null) {                        //adding the characters
            myCharGen.addGameCharacter(ch);
            System.out.println(ch.display());
        }
        
    }

    

}
