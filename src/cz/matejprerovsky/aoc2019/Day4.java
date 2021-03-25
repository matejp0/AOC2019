package cz.matejprerovsky.aoc2019;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 extends Day{
    public Day4() {
        super(4);
    }

    int lowest = Integer.parseInt(lines[0].split("-")[0]);
    int highest = Integer.parseInt(lines[0].split("-")[1]);

    @Override
    public String part1() {
        return String.valueOf(
                IntStream
                        .range(lowest, highest+1)
                        .filter(x -> check(x, true))
                        .count());
    }

    private int countOfDigits(String s, int i){
        return (int)(s.chars()
                .filter(x -> String.valueOf((char) x).equals(String.valueOf(i)))
                .count());
    }
    private boolean check(int i, boolean allowGroups){
        char[] num = String.valueOf(i).toCharArray();
        boolean smallerOrEqual = true;
        for(int n = 0; n<num.length-1; n++){
            if(!(num[n] <= num[n+1])) {
                smallerOrEqual = false;
                break;
            }
        }
        if(smallerOrEqual) {
            for (int j = 0; j < 10; j++) {
                boolean groups = allowGroups ? true : (countOfDigits(String.valueOf(i), j) == 2);
                if (String.valueOf(i).contains(String.valueOf(j) + String.valueOf(j)) && groups && smallerOrEqual) {
                    return true;
                }

            }
        }
        return false;
    }
    @Override
    public String part2() {
        return String.valueOf(
                IntStream
                        .range(lowest, highest+1)
                        .filter(x -> check(x, false))
                        .count());
    }
}
