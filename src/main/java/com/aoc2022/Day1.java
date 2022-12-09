package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class Day1 {

  private final List<Integer> elvesCalories;

  public Day1(String input) {
    elvesCalories = createElvesList(input);
  }

  private static List<Integer> createElvesList(String input) {
    List<Integer> elves = new ArrayList<>();
    int caloriesSum = 0;
    for (String calories : input.split("\n", -1)) {
      if (calories.isBlank()) {
        elves.add(caloriesSum);
        caloriesSum = 0;
      } else {
        caloriesSum += Integer.parseInt(calories);
      }
    }
    elves.add(caloriesSum);
    return elves;
  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day1.class.getResourceAsStream("/d1/input.txt")),
        StandardCharsets.UTF_8);
    Day1 task = new Day1(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());

  }

  int part1() {
    return elvesCalories.stream().mapToInt(x -> x).max().orElseThrow();
  }

  int part2() {
    return elvesCalories.stream().sorted(Comparator.reverseOrder()).mapToInt(x -> x).limit(3).sum();
  }
}
