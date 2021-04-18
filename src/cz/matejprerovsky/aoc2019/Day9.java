package cz.matejprerovsky.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9 extends Day {

    private Map<Integer, Long> mem;


    public Day9() {
        super(9);
        long[] numbers = Arrays.stream((lines[0].split(",")))
                .mapToLong(Long::parseLong)
                .toArray();

        mem = IntStream.range(0, numbers.length)
                .boxed()
                .collect(Collectors.toMap(
                        x -> x,
                        x -> numbers[x]
                ));

    }

    @Override
    public String part1() {
        return String.valueOf(new IntCodeComputer().compute(1));
    }

    @Override
    public String part2() {
        return String.valueOf(new IntCodeComputer().compute(2));
    }

    private class IntCodeComputer {
        private Map<Integer, Long> memory;
        private int ip;
        private int base;
        private boolean halted = false;

        public IntCodeComputer() {
            this.memory = new HashMap<>(mem);
            ip = 0;
        }

        public boolean isHalted() {
            return halted;
        }

        public long compute(long input) {
            long output = 0;
            while (true) {
                long instruction = memory.get(ip);
                byte opcode = (byte) (instruction % 100);


                final byte[] modes = new byte[]{
                        (byte) (instruction / 100 % 10),
                        (byte) (instruction / 1000 % 10),
                        (byte) (instruction / 10000 % 10)
                };

                long[] values = new long[]{0, 0, 0};
                int[] addresses = new int[]{0, 0, 0};

                for(int i = 0; i < 3; i++){
                    try{
                        values[i] = ((modes[i] == 1) ? memory.get(ip + 1 + i) : memory.get(Math.toIntExact(memory.get(ip + 1 + i) + ((modes[i] == 2) ? base : 0))));
                    } catch (Exception e){}


                    try{
                        addresses[i] = Math.toIntExact(memory.get(ip + 1 + i) + ((modes[i] == 2) ? base : 0));
                    } catch (Exception e){}

                }
                switch (opcode) {
                    /** add two numbers */
                    case 1:
                        memory.put(addresses[2], values[0]+values[1]);
                        ip += 4;
                        break;

                    /** multiply two numbers */
                    case 2:
                        memory.put(addresses[2], values[0]*values[1]);
                        ip += 4;
                        break;

                    /** save input value*/
                    case 3:
                        memory.put(addresses[0], input);
                        ip += 2;
                        break;

                    /** output */
                    case 4:
                        output = values[0];
                        ip += 2;
                        break;

                    /** jump if true */
                    case 5:
                        ip = (values[0] != 0) ? (int) values[1] : ip + 3;
                        break;

                    /** jump if false */
                    case 6:
                        ip = (values[0] == 0) ? (int) values[1] : ip + 3;
                        break;

                    /** less than */
                    case 7:
                        memory.put(addresses[2], (values[0] < values[1]) ? 1L : 0L);

                        ip += 4;
                        break;

                    /** equals */
                    case 8:
                        memory.put(addresses[2], (values[0] == values[1]) ? 1L : 0L);

                        ip += 4;
                        break;

                    /** modify relative base */
                    case 9:
                        base += (int) values[0];
                        ip+=2;

                        break;

                    /** halt */
                    case 99:
                        halted = true;
                        return output;

                    default:
                        System.out.println("Something went wrong");
                        return output;
                }

            }
        }
    }
}
