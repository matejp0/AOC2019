package cz.matejprerovsky.aoc2019;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day7 extends Day{

    private final int[] mem;


    public Day7() {
        super(7);
        mem =  Arrays.stream((lines[0].split(",")))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static Set<String> getPermutation(String str) {

        // create a set to avoid duplicate permutation
        Set<String> permutations = new HashSet<>();

        // check if string is null
        if (str == null) {
            return null;

        // terminating condition for recursion
        } else if (str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        // get the first character
        char first = str.charAt(0);

        // get the remaining substring
        String sub = str.substring(1);

        // make recursive call to getPermutation()
        Set<String> words = getPermutation(sub);

        // access each element from words
        for (String strNew : words) {
            for (int i = 0;i<=strNew.length();i++){

                // insert the permutation to the set
                permutations.add(strNew.substring(0, i) + first + strNew.substring(i));
            }
        }
        return permutations;
    }


    @Override
    public String part1() {
        int highest = 0;

        for (String permutation : getPermutation("01234")){
            int[] array = permutation.chars()
                    .map(x -> x-'0')
                    .toArray();

            int lastOutput = 0;
            for(int i : array){
                lastOutput = computeForPart1((lastOutput != 0) ? lastOutput : 0, i);
            }
            if(lastOutput > highest)
                highest = lastOutput;
        }
        return String.valueOf(highest);
    }

    public int computeForPart1(int input, int phase){
        int[] code = mem.clone();
        int ip = 0;
        int output = 0;

        boolean sentPhase = false;

        while(ip < code.length){
            int opcode = code[ip] % 100;
            int aMode = code[ip] / 100 % 10;
            int bMode = code[ip] / 1000 % 10;
            //            int cMode = code[ip] / 10000 % 10; //never used

            int a = 0;
            int b = 0;
            if((opcode >= 1 && opcode <=2) || (opcode >= 5 && opcode <= 8)){
                a = ((aMode == 1) ? code[ip+1] : code[code[ip+1]]);
                b = ((bMode == 1) ? code[ip+2] : code[code[ip+2]]);
            }


            switch(opcode)
            {
                /** add two numbers */
                case 1:
                    code[code[ip+3]] = a + b;
                    ip+=4;
                    break;

                /** multiply two numbers */
                case 2:
                    code[code[ip+3]] = a * b;
                    ip+=4;
                    break;

                /** save input value*/
                case 3:
                    code[code[ip+1]] = sentPhase ? input : phase;
                    sentPhase = true;
                    ip+=2;
                    break;

                /** output */
                case 4:
                    output = code[code[ip+1]];
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
                    if(a < b) code[code[ip+3]] = 1;
                    else code[code[ip+3]] = 0;

                    ip+=4;
                    break;

                /** equals */
                case 8:
                    if(a == b) code[code[ip+3]] = 1;
                    else code[code[ip+3]] = 0;
                    ip+=4;

                    break;

                /** halt */
                case 99:
                    return output;

            }

        }
        return 0;
    }

    private IntCode[] computers;

    private class IntCode
    {
        private int[] code;
        private int phase;
        private boolean sentPhase = false;
        private int ip;
        private boolean halted = false;

        public IntCode(int phase) {
            this.phase = phase;
            this.code = mem.clone();
            ip = 0;
        }

        public boolean isHalted() {
            return halted;
        }

        public int compute(int input){
            int output = 0;
            while(ip < code.length){
                int opcode = code[ip] % 100;
                int aMode = code[ip] / 100 % 10;
                int bMode = code[ip] / 1000 % 10;
    //            int cMode = code[ip] / 10000 % 10; //never used

                int a = 0;
                int b = 0;
                if((opcode >= 1 && opcode <=2) || (opcode >= 5 && opcode <= 8)){
                    a = ((aMode == 1) ? code[ip+1] : code[code[ip+1]]);
                    b = ((bMode == 1) ? code[ip+2] : code[code[ip+2]]);
                }


                switch(opcode)
                {
                    /** add two numbers */
                    case 1:
                        code[code[ip+3]] = a + b;
                        ip+=4;
                        break;

                    /** multiply two numbers */
                    case 2:
                        code[code[ip+3]] = a * b;
                        ip+=4;
                        break;

                    /** save input value*/
                    case 3:
                        code[code[ip+1]] = sentPhase ? input : phase;
                        sentPhase = true;
                        ip+=2;
                        break;

                    /** output */
                    case 4:
                        output = code[code[ip+1]];
//                        System.out.println("[OUTPUT with phase " + phase + "]: " + output);
                        ip+=2;
                        return output;

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
                        if(a < b) code[code[ip+3]] = 1;
                        else code[code[ip+3]] = 0;

                        ip+=4;
                        break;

                    /** equals */
                    case 8:
                        if(a == b) code[code[ip+3]] = 1;
                        else code[code[ip+3]] = 0;
                        ip+=4;

                        break;

                    /** halt */
                    case 99:
                        halted = true;
//                        System.out.println("[HALT with phase " + phase + "]: " + output);
                        return output;
                }

            }
            return output;
        }

        @Override
        public String toString() {
            return "IntCode{" +
                    ", phase=" + phase +
                    '}';
        }
    }


    @Override
    public String part2() {
        int highest = 0;
        for (String permutation : getPermutation("56789")){
            computers = permutation.chars()
                    .map(x -> x-'0')
                    .mapToObj(x -> new IntCode(x))
                    .toArray(IntCode[]::new);

            int n = 0;
            int i = 0;
            while(!computers[4].isHalted()){
                int got = computers[n%5].compute(i);
                i = (got == 0) ? i : got;
                n++;
            }
            if(i > highest) {
                highest = i;
            }


        }
        return String.valueOf(highest);
    }
}
