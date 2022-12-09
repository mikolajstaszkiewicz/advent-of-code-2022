package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class Day3Test {
  private static final String INPUT = """
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
      """;

  @Test
  void part1Test() {
    Day3 task = new Day3(INPUT);
    Assertions.assertEquals(157, task.part1());
  }

  @Test
  void part2Test() {
    Day3 task = new Day3(INPUT);
    Assertions.assertEquals(70L, task.part2());
  }
}
