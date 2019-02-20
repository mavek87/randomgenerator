package com.matteoveroni.randomgenerator;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RandIntegerWithoutRepetitionGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void if0ThrowIllegalArgument() {
        new RandIntegerWithoutRepetitionGenerator(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifNegativeThrowIllegalArgument() {
        new RandIntegerWithoutRepetitionGenerator(0, -5);
    }

    @Test
    public void ifBetween0And1Extract0Or1() throws NoMoreIntegersToGenerateException {
        int min = 0;
        int max = 1;
        RandIntegerWithoutRepetitionGenerator rand = new RandIntegerWithoutRepetitionGenerator(min, max);

        int firstExtraction = rand.nextInt();
        int secondExtraction = rand.nextInt();

        assertTrue(firstExtraction >= min && firstExtraction <= max);
        assertTrue(secondExtraction >= min && secondExtraction <= max);

        // If first and secondExtraction are the opposite number between 0 and 1 their module should always be 1
        assertTrue((firstExtraction ^ secondExtraction) == 1);
    }

    @Test
    public void ifBetween2And15ExtractOnlyCompatibleNumbers() {
        int min = 2;
        int max = 15;
        RandIntegerWithoutRepetitionGenerator rand = new RandIntegerWithoutRepetitionGenerator(min, max);

        List<Integer> extractedNumbers = new ArrayList<>();
        List<Integer> listOfExpectedNumbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            listOfExpectedNumbers.add(i);
            try {
                Integer extractNumber = rand.nextInt();
                extractedNumbers.add(extractNumber);
                assertTrue(extractNumber >= min);
                assertTrue(extractNumber <= max);
            } catch (Exception ex) {
            }
        }

        for (Integer number : extractedNumbers) {
            listOfExpectedNumbers.remove(number);
        }

        assertTrue(listOfExpectedNumbers.isEmpty());

        Exception exOccurred = null;
        try {
            rand.nextInt();
        } catch (NoMoreIntegersToGenerateException ex) {
            exOccurred = ex;
        } finally {
            assertNotNull(exOccurred);
            assertTrue(exOccurred instanceof NoMoreIntegersToGenerateException);
        }
    }
}
