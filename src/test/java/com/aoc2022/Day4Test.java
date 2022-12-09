package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day4Test {
 private static final String INPUT = """
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
     """;

 @Test
 void part1Test() {
   Day4 task = new Day4(INPUT);
   Assertions.assertEquals(2, task.part1());
 }

 @Test
 void part2Test() {
   Day4 task = new Day4(INPUT);
   Assertions.assertEquals(4, task.part2());
 }
}
