package cz.matejprerovsky.aoc2019;

import java.util.*;

public class Day3 extends Day{
    public Day3() {
        super(3);
    }

    public int[] getDir (char c) {
        switch (c) {
            case 'U': return new int[] {0,1};
            case 'D': return new int[] {0,-1};
            case 'L': return new int[] {-1,0};
            case 'R': return new int[] {1,0};
        }
        return null;
    }

    @Override
    public String part1() {
        Set<String> wire = new HashSet<String>();
        int x = 0, y = 0;
        for(String element : lines[0].split(",")){
            int[] direction = this.getDir(element.charAt(0));
            int count = Integer.parseInt(element.substring(1));
            for(int i = 0; i < count; i++){
                x+=direction[0];
                y+=direction[1];
                wire.add(x + ";" + y);
            }

        }

        x = 0;
        y = 0;
        int distance = Integer.MAX_VALUE;
        for(String element : lines[1].split(",")){
            int[] direction = this.getDir(element.charAt(0));
            int count = Integer.parseInt(element.substring(1));
            for(int i = 0; i < count; i++){
                x+=direction[0];
                y+=direction[1];
                if(wire.contains(x + ";" + y) /* java unsolved Set.contains() vs !Set.add()*/) {
                    distance = Math.min(distance, Math.abs(x) + Math.abs(y));
                }
            }

        }

        return String.valueOf(distance);
    }

        private void drawPath(Set wire){

    }

    @Override
    public String part2() {
        return null;
    }
}
