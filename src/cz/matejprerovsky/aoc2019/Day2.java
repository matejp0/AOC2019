package cz.matejprerovsky.aoc2019;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 extends Day{

    private final int[] mem;

    public Day2() {
        super(2);
        mem = Arrays.stream((lines[0].split(",")))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int[] getNums(){
        return mem.clone();
    }


    @Override
    public String part1() {
        int[] nums = this.getNums();

        // replacement
        nums[1] = 12;
        nums[2] = 2;

        return String.valueOf(compute(nums)[0]);
    }

    private int[] compute(int[] nums){
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
                    return nums;

            }

        }
        return null;
    }
    @Override
    public String part2() {
        for(int noun = 0; noun<100; noun++){
            for(int verb = 0; verb<100; verb++){
                int[] nums = this.getNums();
                nums[1] = noun;
                nums[2] = verb;
                if(compute(nums)[0] == 19690720)
                    return String.valueOf((100*noun) + verb);

            }
        }

        return null;
    }
}
