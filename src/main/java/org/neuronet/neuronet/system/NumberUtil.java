package org.neuronet.neuronet.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberUtil {
    public static int getFactorial(int number/*, int steps*/) {
        int result = number;
        for (int i = number-1; i > 0; i--) {
            //if (number - i == steps)
            //    break;
            result *= i;

        }
        return result;
    }

    public static int getPermutationsFor(int number, int selectedCount) {
        final int numberFactorial = getFactorial(number);
        final int selectedFactorial = getFactorial(number - selectedCount);

        final double result = (double) numberFactorial / (double) selectedFactorial;
        return (int) Math.round(result);
    }

    public static List<int[]> getPermutationsNoRep(int count) {
        final List<int[]> listPerms = new ArrayList<>();

        int[] indexesArray = new int[count];
        for (int i = 0; i < count; i++) {
            indexesArray[i] = i;
        }

        listPerms.add(indexesArray);
        int[] permArray = indexesArray;
        while ((permArray = nextPermutationNoRep(permArray)) != null) {
            listPerms.add(permArray);
        }
        return listPerms;
    }

    public static int[] nextPermutationNoRep(int[] indexesArray) {
        final int[] permArray = Arrays.copyOf(indexesArray, indexesArray.length);

        int i = indexesArray.length - 1;
        while (i > 0 && indexesArray[i - 1] >= indexesArray[i]) {
            i--;
        }

        if (i <= 0) {
            return null;
        }

        int j = indexesArray.length - 1;
        while (indexesArray[j] <= indexesArray[i - 1]) {
            j--;
        }

        int temp = indexesArray[i - 1];
        permArray[i - 1] = indexesArray[j];
        permArray[j] = temp;

        j = indexesArray.length - 1;
        while (i < j) {
            temp = indexesArray[i];
            permArray[i] = indexesArray[j];
            permArray[j] = temp;
            i++;
            j--;
        }
        return permArray;
    }

    public static List<Integer> getPermutationsRep(int[] options, int count) {
        final List<Integer> listOutput = new ArrayList<>();
        generatePermutationsRep(options, count, listOutput);
        return listOutput;
    }

    public static void generatePermutationsRep(int[] options, int count, List<Integer> output) {
        if (count == 0) {
            System.out.println(output);
        } else {
            for (int i = 0; i < options.length; i++) {
                output.add(options[i]);
                generatePermutationsRep(options, count - 1, output);
                output.remove(output.size() - 1);
            }
        }
    }

}
