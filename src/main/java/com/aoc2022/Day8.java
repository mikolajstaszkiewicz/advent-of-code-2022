package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class Day8 {

  private final String input;

  public Day8(String input) {
    this.input = input;
  }


  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day8.class.getResourceAsStream("/d8/input.txt")),
        StandardCharsets.UTF_8);
    Day8 task = new Day8(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  public long part1() {
    int visibleTrees = 0;
    String[] lines = input.split("\n");
    for (int y = 0; y < lines.length; y++) {
      for (int x = 0; x < lines[y].length(); x++) {
        boolean leftMatch = true;
        for (int left = 0; left < x; left++) {
          if (lines[y].charAt(x) <= lines[y].charAt(left)) {
            leftMatch = false;
            break;
          }
        }
        if (leftMatch) {
          ++visibleTrees;
          continue;
        }

        boolean rightMatch = true;
        for (int right = lines[y].length() - 1; right > x; right--) {
          if (lines[y].charAt(x) <= lines[y].charAt(right)) {
            rightMatch = false;
            break;
          }
        }
        if (rightMatch) {
          ++visibleTrees;
          continue;
        }

        boolean upMatch = true;
        for (int up = 0; up < y; up++) {
          if (lines[y].charAt(x) <= lines[up].charAt(x)) {
            upMatch = false;
            break;
          }
        }
        if (upMatch) {
          ++visibleTrees;
          continue;
        }

        boolean downMatch = true;
        for (int down = lines.length - 1; down > y; down--) {
          if (lines[y].charAt(x) <= lines[down].charAt(x)) {
            downMatch = false;
            break;
          }
        }
        if (downMatch) {
          ++visibleTrees;
          continue;
        }
      }
    }
    return visibleTrees;
  }

  public long part2() {
    int maxScenicScore = 0;
    String[] lines = input.split("\n");
    for (int y = 0; y < lines.length; y++) {
      for (int x = 0; x < lines[y].length(); x++) {
        int leftScore = 0;
        for (int left = x - 1; left >= 0; left--) {
          ++leftScore;
          if (lines[y].charAt(x) <= lines[y].charAt(left)) {
            break;
          }
        }
        int rightScore = 0;
        for (int right = x + 1; right < lines[y].length(); right++) {
          ++rightScore;
          if (lines[y].charAt(x) <= lines[y].charAt(right)) {
            break;
          }
        }
        int upScore = 0;
        for (int up = y - 1; up >= 0; up--) {
          ++upScore;
          if (lines[y].charAt(x) <= lines[up].charAt(x)) {
            break;
          }
        }
        int downScore = 0;
        for (int down = y + 1; down < lines.length; down++) {
          ++downScore;
          if (lines[y].charAt(x) <= lines[down].charAt(x)) {
            break;
          }
        }
        int scenicScore = leftScore * rightScore * upScore * downScore;
        maxScenicScore = Math.max(maxScenicScore, scenicScore);
      }
    }
    return maxScenicScore;
  }

}
