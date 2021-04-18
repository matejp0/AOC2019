package cz.matejprerovsky.aoc2019;

import java.util.List;
import java.util.stream.Collectors;

public class Day8 extends Day{
    private final int WIDTH = 25;
    private final int HEIGHT = 6;
    private final int SIZE = WIDTH * HEIGHT;
    private final List<Integer> list = lines[0].chars()
            .boxed()
            .map(x -> x - '0')
            .collect(Collectors.toList());

    public Day8() {
        super(8);
    }

    @Override
    public String part1() {
        int fewestZeros = Integer.MAX_VALUE;

        int result = 0;
        for(int i = 0; i<list.size(); i+=SIZE){
            List<Integer> layer = list.subList(i, i+SIZE);

            int countOfZeros = (int) layer.stream().filter(x -> x == 0).count();
            if(countOfZeros < fewestZeros) {
                fewestZeros = countOfZeros;
                result = (int) layer.stream().filter(x -> x == 1).count()
                        * (int) layer.stream().filter(x -> x == 2).count();

            }
        }
        return String.valueOf(result);
    }

    @Override
    public String part2() {
        StringBuilder message = new StringBuilder();
        message.append("\n");
        for(int index = 0; index<SIZE; index++){
            message.append(String.valueOf(getColorAtIndex(0, index)).replace('1', '#').replace('0', '.'));
            if((index+1) % WIDTH == 0)
                message.append("\n");
        }
        return message.toString();
    }

    private int getColorAtIndex(int layerNumber, int index){
        List<Integer> layer = list.subList(SIZE*layerNumber, (SIZE*(layerNumber+1)));
        int color = layer.get(index);

        if(color == 2) {
            color = getColorAtIndex(layerNumber + 1, index);
        }

        return color;
    }
}
