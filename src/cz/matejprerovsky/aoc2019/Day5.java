package cz.matejprerovsky.aoc2019;

import java.util.Arrays;

public class Day5 extends Day{
    // strongly inspired by https://github.com/boriskurikhin/AdventOfCode2019/blob/master/Day5.java

    private final int[] mem;

    public Day5() {
        super(5);
        mem = Arrays.stream((lines[0].split(",")))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Override
    public String part1() {
        return compute(mem.clone(), 1);

    }

    @Override
    public String part2() {
        return compute(mem.clone(), 5);
    }

    private String compute(int[] nums, int input){
        int ip = 0;
        int output = 0;

        while(ip < nums.length){
            int opcode = nums[ip] % 100;
            int aMode = nums[ip] / 100 % 10;
            int bMode = nums[ip] / 1000 % 10;
//            int cMode = nums[ip] / 10000 % 10; //never used

            int a = 0;
            int b = 0;
            if((opcode >= 1 && opcode <=2) || (opcode >= 5 && opcode <= 8)){
                a = ((aMode == 1) ? nums[ip+1] : nums[nums[ip+1]]);
                b = ((bMode == 1) ? nums[ip+2] : nums[nums[ip+2]]);
            }


            switch(opcode)
            {
                /** add two numbers */
                case 1:
                    nums[nums[ip+3]] = a + b;
                    ip+=4;
                    break;

                /** multiply two numbers */
                case 2:
                    nums[nums[ip+3]] = a * b;
                    ip+=4;
                    break;

                /** save input value*/
                case 3:
                    nums[nums[ip+1]] = input;
                    ip+=2;
                    break;

                /** output */
                case 4:
                    output = nums[nums[ip+1]];
//                    System.out.println("[OUTPUT]: " + output);
                    ip+=2;

                    break;

                /** jump if true */
                case 5:
                    if(a != 0) ip = b;
                    else ip+=3;

                    break;

                /** jump if false */
                case 6:
                    if(a == 0) ip = b;
                    else ip+=3;

                    break;

                /** less than */
                case 7:
                    if(a < b) nums[nums[ip+3]] = 1;
                    else nums[nums[ip+3]] = 0;

                    ip+=4;
                    break;

                /** equals */
                case 8:
                    if(a == b) nums[nums[ip+3]] = 1;
                    else nums[nums[ip+3]] = 0;
                    ip+=4;

                    break;

                /** halt */
                case 99:
                    return String.valueOf(output);

            }

        }
        return null;
    }
}
