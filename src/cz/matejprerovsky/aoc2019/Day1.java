package cz.matejprerovsky.aoc2019;

import java.util.stream.Stream;

public class Day1 extends Day{
    public Day1() {
        super(1);
    }

    @Override
    public String part1() {
        return String.valueOf(
                Stream.of(lines)
                        .mapToInt(Integer::parseInt)
                        .map(x -> (x/3)-2)
                        .sum());
    }

    @Override
    public String part2() {
        return String.valueOf(
                Stream.of(lines)
                        .mapToInt(Integer::parseInt)
                        .map(this::fuelNeeded)
                        .sum());
    }

    private int fuelNeeded(int mass){
        int fuel = (mass/3)-2;
        return (fuel <= 0) ? 0 : fuelNeeded(fuel)+fuel;
    }
}
