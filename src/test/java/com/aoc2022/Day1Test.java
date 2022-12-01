package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day1Test {

  private static final String INPUT = """
      1000
      2000
      3000

      4000

      5000
      6000

      7000
      8000
      9000

      10000
      """;

  @Test
  void part1Test() {
    Day1 task = new Day1(INPUT);
    Assertions.assertEquals(24000, task.part1());
  }

  @Test
  void part2Test() {
    Day1 task = new Day1(INPUT);
    Assertions.assertEquals(45000, task.part2());
  }
}
