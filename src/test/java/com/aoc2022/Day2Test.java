package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day2Test {

  private static final String INPUT = """
A Y
B X
C Z
      """;

  @Test
  void part1Test() {
    Day2 task = new Day2(INPUT);
    Assertions.assertEquals(15L, task.part1());
  }

  @Test
  void part2Test() {
    Day2 task = new Day2(INPUT);
    Assertions.assertEquals(12L, task.part2());
  }


}
