package org.ahmeteminsaglik;

import java.util.List;
import java.util.Random;

public class Utility {
    static Random random = new Random();

     static int getProperItemIndex(int abbrSize, List<Integer> list, int requestIndex) {
        for (Integer tmp : list) {
            if (tmp == requestIndex) {
                requestIndex = getUniqeItemAbbrIndex(abbrSize, requestIndex);
                return getProperItemIndex(abbrSize, list, requestIndex);
            }
        }
        return requestIndex;
    }

    static int getUniqeItemAbbrIndex(int listSize, int index) {
        index++;
        return index % listSize;
    }

    static String getRandomGenderName() {
        if (random.nextInt(100) > 50) {
            return Main.maleName[getRandomInt(Main.maleName.length)];
        } else {
            return Main.femaleName[getRandomInt(Main.femaleName.length)];
        }

    }

    static int getRandomInt(int bound) {
        return random.nextInt(bound);
    }

    static double getRandomDoublePoint() {
        return random.nextInt(100) / 10.0;
    }

    static int getRandomFollowers() {
        return random.nextInt(100);
    }

    static void deleteComma(StringBuilder stringBuilder, int commaIndexToMinusFromLength) {
        stringBuilder.deleteCharAt(stringBuilder.length() - commaIndexToMinusFromLength);
    }
}
