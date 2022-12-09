package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day5Test {
 private static final String INPUT = """
         [D]   \s
     [N] [C]   \s
     [Z] [M] [P]
      1   2   3\s

     move 1 from 2 to 1
     move 3 from 1 to 3
     move 2 from 2 to 1
     move 1 from 1 to 2
     """;

 @Test
 void part1Test() {
   Day5 task = new Day5(INPUT);
   Assertions.assertEquals("CMZ", task.part1());
 }

 @Test
 void part2Test() {
   Day5 task = new Day5(INPUT);
   Assertions.assertEquals("MCD", task.part2());
 }
}
