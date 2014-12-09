package pos1_2ahif.prep_test2;

/**
 * Created by Florian on 09.12.2014.
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("Waldo is hiding! Be patient...");
        WhereIsWaldo whereIsWaldo = new WhereIsWaldo(100); // challenge: do 10000000
        System.out.println("Waldo is ready now! Go find him!");
        System.out.println("Searching...");


        // CHANGE THIS LINE
        WhereIsWaldo.Exercises solution = null; // TODO provide your implementation here
        // CHANGE THIS LINE

        int found = solution.findWaldo(whereIsWaldo);

        if (whereIsWaldo.containsKey(found)
                && (whereIsWaldo.get(found).search() instanceof WhereIsWaldo.Waldo)) {
            System.out.println("There he is: " + found);
        } else {
            System.out.println("You must be mistaken: There is no Waldo at " + found + " :(");
        }
    }
}
