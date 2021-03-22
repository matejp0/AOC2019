package cz.matejprerovsky.aoc2019;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 extends Day{
    public Day2() {
        super(2);
    }

    @Override
    public String part1() {
        int[] nums = Arrays.stream((lines[0].split(",")))
                .mapToInt(Integer::parseInt)
                .toArray();
        // replacement
        nums[1] = 12;
        nums[2] = 2;

        return String.valueOf(compute(nums));
    }

    private void printState(int[] nums){
        System.out.println(Arrays.toString(nums));
    }
    private int compute(int[] nums){
        for(int ip = 0; ip<nums.length; ip++){
            switch(nums[ip])
            {
                case 1:
                    nums[nums[ip+3]] = nums[nums[ip+1]]+nums[nums[ip+2]];
                    ip+=3;
                    break;

                case 2:
                    nums[nums[ip+3]] = nums[nums[ip+1]]*nums[nums[ip+2]];
                    ip+=3;
                    break;

                case 99:
                    return nums[0];

            }

        }
        return 0;
    }
    @Override
    public String part2() {
        for(int noun = 0; noun<100; noun++){
            for(int verb = 0; verb<100; verb++){
                int[] nums = Arrays.stream((lines[0].split(",")))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                nums[1] = noun;
                nums[2] = verb;
                if(compute(nums) == 19690720)
                    return String.valueOf((100*noun) + verb);

            }
        }

        return null;
    }
}
