package cz.matejprerovsky.aoc2019;

import java.util.stream.Stream;

public class Day1 extends Day{
    public Day1(int number) {
        super(number);
    }

    @Override
    public String puzzle1() {
        return String.valueOf(
                Stream.of(lines)
                        .mapToInt(x -> Integer.parseInt(x))
                        .map(x -> (x/3)-2)
                        .sum());
    }

    @Override
    public String puzzle2() {
        return String.valueOf(
                Stream.of(lines)
                        .mapToInt(x -> Integer.parseInt(x))
                        .map(x -> fuelNeeded(x))
                        .sum());
    }

    private int fuelNeeded(int mass){
        int fuel = (mass/3)-2;
        return (fuel <= 0) ? 0 : fuelNeeded(fuel)+fuel;
    }
}
