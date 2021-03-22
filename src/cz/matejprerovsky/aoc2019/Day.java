package cz.matejprerovsky.aoc2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class Day {
    String[] lines;
    public Day(int number){
        this.lines = this.loadInput(number);
    }

    public abstract String puzzle1();

    public abstract String puzzle2();
    public void solve(){
        System.out.println("Part 1: " + this.puzzle1());
        System.out.println("Part 2: " + this.puzzle2());
    }
    private String[] loadInput(int number){
        try {
            return Files.lines(Paths.get("inputs/" + number)).toArray(String[]::new);
        }
        catch (IOException e){
            return null;
        }
    }
}
