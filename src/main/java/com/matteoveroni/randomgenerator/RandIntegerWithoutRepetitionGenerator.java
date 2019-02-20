package com.matteoveroni.randomgenerator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matteo Veroni
 *
 * Returns a unique random integer in a range delimited by an upper and lower
 * value without the possibility of repetition.
 *
 * Example: domain(1-4) - {1,2,3,4} ===> nextInt() = 3 -> nextInt() = 4 ->
 * nextInt() = 1 -> nextInt() = 2 -> nextInt() = Exception
 */
public class RandIntegerWithoutRepetitionGenerator {

    private final int maxBound;
    private final int minBound;
    private final List<Integer> numbers = new LinkedList<>();

    public RandIntegerWithoutRepetitionGenerator(int minBound, int maxBound) throws IllegalArgumentException {
        if (minBound >= maxBound) {
            throw new IllegalArgumentException("minRange must be smaller than maxRange");
        }
        this.maxBound = maxBound;
        this.minBound = minBound;
        resetNumberRepetionSystem();
    }

    /**
     * Method which returns a random integer without repetition
     * @return A random integer without repetition
     * @throws NoMoreIntegersToGenerateException if is not possible to extract any other integer because of repetition a NoMoreNumbersToGenerateException is thrown.
     */
    public final int nextInt() throws NoMoreIntegersToGenerateException {
        if (!numbers.isEmpty()) {
            return numbers.remove(0);
        } else {
            throw new NoMoreIntegersToGenerateException();
        }
    }

    /**
     * Method which resets the logical model behind the number repetition system
     */
    public final void resetNumberRepetionSystem() {
        initNumbers();
        Collections.shuffle(numbers);
    }

    private void initNumbers() {
        for (int i = minBound; i <= maxBound; i++) {
            numbers.add(i);
        }
    }

}
