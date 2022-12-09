package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day7Test {

  private static final String INPUT = """
      $ cd /
      $ ls
      dir a
      14848514 b.txt
      8504156 c.dat
      dir d
      $ cd a
      $ ls
      dir e
      29116 f
      2557 g
      62596 h.lst
      $ cd e
      $ ls
      584 i
      $ cd ..
      $ cd ..
      $ cd d
      $ ls
      4060174 j
      8033020 d.log
      5626152 d.ext
      7214296 k
           """;

  @Test
  void part1Test() {
    Day7 task = new Day7(INPUT);
    Assertions.assertEquals(95437, task.part1());
  }

  @Test
  void part2Test() {
    Day7 task = new Day7(INPUT);
    Assertions.assertEquals(24933642L, task.part2());
  }
}
