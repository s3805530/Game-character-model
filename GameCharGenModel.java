
/**
 * Author:[Shrestha Ghosh]-[s3805530]
 */
import java.util.Arrays;

public class GameCharGenModel {

    static public int NUM_CHARS_BLOCK = 500;
    final public int STRENGTH = 0;
    final public int INTELLIGENCE = 1;
    final public int WISDOM = 2;

    private int gameCharCount = 0;
    private GameCharacter[] randomGameChars = new GameCharacter[NUM_CHARS_BLOCK];

    /**
     * Returns the number of randomly-generated characters so far.
     *
     * @return Number of randomly-generated characters.
     */
    public int getNumCharacters() {
        return gameCharCount;
    }

    /**
     * Adds a character to the array of characters
     *
     * @param c character to be added
     */
    public void addGameCharacter(GameCharacter c) {
        if (gameCharCount >= randomGameChars.length) {
            GameCharacter[] newRandomChars = Arrays.copyOf(randomGameChars,
                    randomGameChars.length + GameCharGenModel.NUM_CHARS_BLOCK);
            randomGameChars = newRandomChars;
        }

        randomGameChars[gameCharCount] = c;
        gameCharCount++;
    }

    /**
     * Returns a character at a given index
     *
     * @param idx Provided index
     * @return Character at a given index
     */
    public GameCharacter gameCharacterAt(int idx) {
        if (idx < 0 || idx >= randomGameChars.length) {
            throw new IndexOutOfBoundsException();
        }

        return randomGameChars[idx];
    }

    /**
     * Finds a character with either maximum strength, intelligence or wisdom based
     * on the parameter passed.
     *
     * @return Index of the character with lowest HP in the character array.
     * @throws NoGameCharacterFoundException
     */

    /**
     * ALGORITHM
     * 
     * BEGIN:
     * 
     * Checks if there are any characters in the array IF(gameCharCount==0)
     * gameCharCount is the number of characters executes
     * NoGameCharacterFoundException() Checks for maximum wisdom Adds the wisdom
     * value of every characters Copies in an array the position of the maximum
     * wisdom Copies in an array the name of the character with maximum wisdom
     * Copies in an array the value of maximum wisdom Copies in an array the average
     * of all the wisdom Returns the array
     * 
     * END
     * 
     */

    public String[] wisestOfThemAll() throws NoGameCharacterFoundException {
        // Implement Q2 here
        String[] result = new String[4];
        if (gameCharCount == 0) {
            throw new NoGameCharacterFoundException();
        }
        int index = 0;
        double average = 0;
        double sum = gameCharacterAt(0).getWisdom();
        for (int i = 1; i < gameCharCount; i++) {
            if (gameCharacterAt(i).getWisdom() > gameCharacterAt(index).getWisdom()) {
                index = i;
            }
            sum = sum + gameCharacterAt(i).getWisdom();
        }
        result[0] = String.valueOf(index);
        result[1] = gameCharacterAt(index).getName();
        result[2] = String.valueOf(gameCharacterAt(index).getWisdom());
        average = sum / gameCharCount;
        result[3] = String.format("%.2f", average);
        return result;
    }

    /**
     * ALGORITHM
     * 
     * BEGIN: 
     * Checks if there are any characters in the array 
     * IF(gameCharCount==0)       gameCharCount is the number of characters 
     * executes NoGameCharacterFoundException() 
     * Remove the characters having wisdom < strength 
     * Tracks the indexes of the characters having wisdom >= strength
     * Copies thoses indexes of the character to a new array 
     * Clears the original array 
     * Copies all the characters from the new array to the original array 
     * Sort the array using wiserThanStrongOrStrongAsWise()
     * 
     * END
     * 
     */

    public void wiserThanStrongOrStrongAsWise() throws NoGameCharacterFoundException {
        // Implement Q3 here
        if (gameCharCount == 0) {
            throw new NoGameCharacterFoundException();
        }

        int[] indexes = new int[gameCharCount]; // keep track of indexes of the characters having wisdom >= strength
        int j = 0;
        int counter = 0;
        for (int i = 0; i < gameCharCount; i++) {
            if (gameCharacterAt(i).getWisdom() >= gameCharacterAt(i).getStrength()) {
                indexes[j] = i;
                counter++;
                j++;

            }
        }

        GameCharacter[] tempArr = new GameCharacter[counter];

        for (int i = 0; i < counter; i++) {
            tempArr[i] = randomGameChars[indexes[i]];
        }
        // Clear the array of all characters
        for (int i = 0; i < gameCharCount; i++) {
            randomGameChars[i] = null;
        }
        gameCharCount = 0;

        // Copy the eligible characters to the original array
        for (GameCharacter c : tempArr) {
            addGameCharacter(c);
        }

        // Sorting the array
        
        tempArr = new GameCharacter[gameCharCount];
        String tab[];
        int index;
        int i = 0;
        while (gameCharCount > 0) {
            tab = wisestOfThemAll();
            index = Integer.parseInt(tab[0]);
            tempArr[i] = randomGameChars[index];
            i++;
            for (int k = index; k < gameCharCount - 1; k++) {
                randomGameChars[k] = randomGameChars[k + 1];
            }
            randomGameChars[gameCharCount - 1] = null;
            gameCharCount--;
        }

        for (i = 0; i < gameCharCount; i++) {
            randomGameChars[i] = null;          //clears original array
        }
		gameCharCount = 0;
		

        for (GameCharacter c : tempArr) {  
            addGameCharacter(c);           //copies the sorted array to the original array
        }

    }

    /**
     * This method copies the characters in the array  in a string. 
     * Returns the string which contains the details of the characters.
     */

    public String display() {
        String result = "";
        for (int i = 0; i < gameCharCount; i++) {
            result = result + randomGameChars[i].display() + "\n";
        }

        return result;
    }
}
