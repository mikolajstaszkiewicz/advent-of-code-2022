package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day8Test {

  private static final String INPUT = """
      30373
      25512
      65332
      33549
      35390
                 """;

  @Test
  void part1Test() {
    Day8 task = new Day8(INPUT);
    Assertions.assertEquals(21, task.part1());
  }

  @Test
  void part2Test() {
    Day8 task = new Day8(INPUT);
    Assertions.assertEquals(8, task.part2());
  }
}
