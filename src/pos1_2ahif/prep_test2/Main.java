package pos1_2ahif.prep_test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Florian on 09.12.2014.
 */
public class Main {
    public static class Solution implements WhereIsWaldo.Exercises {

        public int findWaldo(WhereIsWaldo whereIsWaldo) {
            // first we get all positions where he could be hiding
            List<Integer> keys = new ArrayList<Integer>(whereIsWaldo.keySet());
            // we fetched them into a list
            // so that we can sort them
            Collections.sort(keys);

            // now we can try to find him
            // we do it like this:
            // we start searching in the middle
            // and go outwards from there

            // when we find a hint
            // we dismiss all the entries where he cannot be
            // then we start searching from the new middle again

            // we continue in this fashion
            // until we find him


            // at the start we have to consider the whole range
            // from 0 to size()-1
            int currentMin = 0;
            int currentMax = keys.size() - 1;

            // offset means, how far we search away from the mid
            // we will search at positions (mid+offset) and (mid-offset)
            int currentOffset = 0;

            outerLoop:
            while (true) {
                // the mid between min and max is at
                // mid/2 + max/2
                //   e.g. the mid of 20 and 40
                //   20/2 + 40/2 = 10 + 20 = 30

                // from the mid we will search in both directions
                // up and down (hence mid+offset and mid-offset)
                int lowerIndex = currentMin / 2 + currentMax / 2 - currentOffset;
                int upperIndex = currentMin / 2 + currentMax / 2 + currentOffset;

                // we check that we are still within bounds
                if (lowerIndex < currentMin) {
                    lowerIndex = currentMin;
                }
                if (upperIndex > currentMax) {
                    upperIndex = currentMax;
                }

                // we try both indexes (upper and lower) in a loop
                for (Integer index : Arrays.asList(lowerIndex, upperIndex)) {

                    // so far we have a index in the keySet
                    // we get the corresponding key
                    int key = keys.get(index);

                    // then we get the HidingPlace and search it for Waldo
                    Object o = whereIsWaldo.get(key).search();

                    if (o instanceof WhereIsWaldo.HintLeft) {
                        // we got a hint, that waldo is left of this entry
                        // so we reset our max to be to the left
                        // and we restart the search from the new middle
                        currentMax = index - 1;
                        currentOffset = 0;
                        continue outerLoop;
                    } else if (o instanceof WhereIsWaldo.HintRight) {
                        // we got a hint, that waldo is right of this entry
                        // so we reset our min to be to the right
                        // and we restart the search from the new middle
                        currentMin = index + 1;
                        currentOffset = 0;
                        continue outerLoop;
                    } else if (o instanceof WhereIsWaldo.Waldo) {
                        // we found him! we return the key where he was hiding!!!
                        return key;
                    }
                }

                // we did not found him
                // continue searching
                // next iteration we will
                // search the next outer entries
                currentOffset++;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Waldo is hiding! Be patient...");
        WhereIsWaldo whereIsWaldo = new WhereIsWaldo(10000000); // challenge: do 10000000
        System.out.println("Waldo is ready now! Go find him!");
        System.out.println("Searching...");


        // CHANGE THIS LINE
        WhereIsWaldo.Exercises solution = new Solution();
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
