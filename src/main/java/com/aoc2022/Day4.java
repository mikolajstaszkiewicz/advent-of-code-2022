package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class Day4 {

  int overlapsAtAll = 0;
  int overlapFully = 0;
  public Day4(String input) {
    for (String line : input.split("\n")) {
      String[] elves = line.split(",");
      Elf elf1 = new Elf(elves[0]);
      Elf elf2 = new Elf(elves[1]);
      if (elf1.contains(elf2) || elf2.contains(elf1)) {
        ++overlapFully;
      }
      if (elf1.overlaps(elf2)) {
        ++overlapsAtAll;
      }
    }

  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day4.class.getResourceAsStream("/d4/input.txt")),
        StandardCharsets.UTF_8);
    Day4 task = new Day4(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  public int part1() {
    return overlapFully;
  }

  public long part2() {
    return overlapsAtAll;
  }

  static class Elf {

    final int min;
    final int max;

    Elf(String line) {
      String[] sector = line.split("-");
      min = Integer.parseInt(sector[0]);
      max = Integer.parseInt(sector[1]);
    }

    boolean contains(Elf other) {
      return min <= other.min && max >= other.max;
    }

    boolean overlaps(Elf other) {
      return min <= other.max && max >= other.min;
    }
  }
}
