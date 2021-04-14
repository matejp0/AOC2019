package cz.matejprerovsky.aoc2019;


import java.util.*;
import java.util.stream.Collectors;

public class Day6 extends Day{

    private Map<String, String> allOrbits;

    public Day6() {
        super(6);
        allOrbits = init();
    }

    private Map<String, String> init(){

        // {outer=center}
        return Arrays.stream(lines)
                .map(x -> x.split("[)]"))
                .collect(Collectors.toMap(
                        x -> x[1],
                        x -> x[0]
                ));

    }


    private int countOrbits(String outerPlanet, int result) {
        if (allOrbits.containsKey(outerPlanet)) {
            return countOrbits(allOrbits.get(outerPlanet), result + 1);
        }
        return result;
    }

    private String orbits(String outerPlanet){
        String list = outerPlanet;
        if(allOrbits.containsKey(outerPlanet)){
            list += ";" + (orbits(allOrbits.get(outerPlanet)));
        }

        return list;
    }

    /**
     * inspired by https://github.com/neiomi1/AdventOfCode2019/blob/master/src/day_06/Orbits.java
     */
    @Override
    public String part1() {
        return String.valueOf(
                allOrbits.keySet().stream()
                        .mapToInt(x -> countOrbits(x, 0))
                        .sum()
        );
    }

    @Override
    public String part2() {
        String[] me = orbits("YOU").split(";");
        String[] santa = orbits("SAN").split(";");

        for(int m = 0; m<me.length; m++){
            for(int s = 0; s<santa.length; s++){
                if(me[m].equals(santa[s])){
                    return String.valueOf(m-1+s-1);
                }
            }
        }


        return null;
    }
}
