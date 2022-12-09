package com.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day6Test {


  @ParameterizedTest
  @CsvSource({"mjqjpqmgbljsphdztnvjfqwrcgsmlb,7", "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
      "nppdvjthqldpwncqszvftbrmjlhg,6", "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
      "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"})
  void part1Test(String input, int expectedResult) {
    Day6 task = new Day6(input);
    Assertions.assertEquals(expectedResult, task.part1());
  }

  @ParameterizedTest
  @CsvSource({"mjqjpqmgbljsphdztnvjfqwrcgsmlb,19", "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
      "nppdvjthqldpwncqszvftbrmjlhg,23", "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
      "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"})
  void part2Test(String input, int expectedResult) {
    Day6 task = new Day6(input);
    Assertions.assertEquals(expectedResult, task.part2());
  }


}
