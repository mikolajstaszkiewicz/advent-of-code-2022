package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day9Test {

  private static final String INPUT_1 = """
      R 4
      U 4
      L 3
      D 1
      R 4
      D 1
      L 5
      R 2
      """;
  private static final String INPUT_2 = """
      R 5
      U 8
      L 8
      D 3
      R 17
      D 10
      L 25
      U 20
      """;

  @Test
  void part1Test() {
    Day9 task = new Day9(INPUT_1, false);
    Assertions.assertEquals(13, task.part1());
  }

  @Test
  void part2Test1() {
    Day9 task = new Day9(INPUT_1, false);
    Assertions.assertEquals(1, task.part2());
  }

  @Test
  void part2Test2() {
    Day9 task = new Day9(INPUT_2, false);
    Assertions.assertEquals(36, task.part2());
  }
}
