package cz.matejprerovsky.aoc2019;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 extends Day{

    private final int lowest = Integer.parseInt(lines[0].split("-")[0]);
    private final int highest = Integer.parseInt(lines[0].split("-")[1]);

    private int[] clearArr = new int[10];

    public Day4() {
        super(4);
        Arrays.fill(clearArr, 0);
    }

    @Override
    public String part1() {
        return String.valueOf(
                IntStream
                        .rangeClosed(lowest, highest)
                        .filter(x -> check(x, true))
                        .count());
    }

    private boolean check(int i, boolean allowGroups){
        int[] num = String.valueOf(i).chars()
                .map(x -> x - '0')
                .toArray();

        int[] arr = clearArr.clone();

        for(int n = 0; n<num.length; n++){
            if(n < num.length - 1 && num[n] > num[n+1]) return false;
            arr[num[n]] += 1;
        }
        for(int n : arr){
            if(allowGroups ? n>=2 : n==2)
                return true;
        }

        return false;
    }
    @Override
    public String part2() {
        return String.valueOf(
                IntStream
                        .rangeClosed(lowest, highest)
                        .filter(x -> check(x, false))
                        .count());
    }
}
