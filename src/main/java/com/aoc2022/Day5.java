package com.aoc2022;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

public class Day5 {

  private final Ship1 ship1 = new Ship1();
  private final Ship2 ship2 = new Ship2();

  public Day5(String input) {
    Pattern movePattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
    boolean moves = false;
    for (String line : input.split("\n")) {
      if (moves) {
        Matcher moveMatcher = movePattern.matcher(line);
        if (!moveMatcher.find()) {
          throw new IllegalArgumentException("Not matching move pattern " + line);
        }
        ship1.move(parseInt(moveMatcher.group(1)), parseInt(moveMatcher.group(2)) - 1,
            parseInt(moveMatcher.group(3)) - 1);
        ship2.move(parseInt(moveMatcher.group(1)), parseInt(moveMatcher.group(2)) - 1,
            parseInt(moveMatcher.group(3)) - 1);
      } else {
        if (line.isEmpty()) {
          moves = true;
        } else {
          for (int i = 0; i <= line.length() / 4; i++) {
            ship1.add(i, line.substring(i * 4 + 1, i * 4 + 2));
            ship2.add(i, line.substring(i * 4 + 1, i * 4 + 2));
          }
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day5.class.getResourceAsStream("/d5/input.txt")),
        StandardCharsets.UTF_8);
    Day5 task = new Day5(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  String part1() {
    return ship1.stacks.stream().map(LinkedList::peek).collect(Collectors.joining());
  }

  String part2() {
    return ship2.stacks.stream().map(LinkedList::peek).collect(Collectors.joining());
  }

  private static class Ship1 {

    List<LinkedList<String>> stacks = new ArrayList<>();

    void add(int stack, String crate) {
      if (stacks.size() <= stack) {
        stacks.add(new LinkedList<>());
      }
      if (!crate.isBlank()) {
        stacks.get(stack).addLast(crate);
      }
    }

    void move(int count, int fromStack, int toStack) {
      for (int i = 0; i < count; i++) {
        if (!stacks.get(fromStack).isEmpty()) {
          stacks.get(toStack).push(stacks.get(fromStack).pop());
        }
      }
    }
  }

  static class Ship2 {

    List<LinkedList<String>> stacks = new ArrayList<>();

    void add(int stack, String crate) {
      if (stacks.size() <= stack) {
        stacks.add(new LinkedList<>());
      }
      if (!crate.isBlank()) {
        stacks.get(stack).addLast(crate);
      }
    }

    void move(int count, int fromStack, int toStack) {

      List<String> toMove = stacks.get(fromStack)
          .subList(0, Math.min(count, stacks.get(fromStack).size()));
      stacks.get(toStack).addAll(0,
          toMove);
      toMove.clear();


    }
  }
}
