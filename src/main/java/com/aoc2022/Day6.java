package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class Day6 {

  String input;

  public Day6(String input) {
    this.input = input;
  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day6.class.getResourceAsStream("/d6/input.txt")),
        StandardCharsets.UTF_8);
    Day6 task = new Day6(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  int part1() {
    return findStartOfSignal(4);
  }

  int part2() {
    return findStartOfSignal(14);
  }

  int findStartOfSignal(int signalLength) {
    for (int i = signalLength - 1; i < input.length(); i++) {
      if (input.substring(i - signalLength + 1, i + 1).chars().distinct().count() == signalLength) {
        return i + 1;
      }
    }
    throw new IllegalStateException("No start-of signal marker detected");
  }
}
