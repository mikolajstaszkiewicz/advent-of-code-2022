package com.aoc2022;

import static java.util.Objects.requireNonNull;

import com.aoc2022.Day3.Part1.Backpack;
import com.aoc2022.Day3.Part2.Group;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

public class Day3 {

  private final Part1 part1;
  private final Part2 part2;

  public Day3(String input) {
    part1 = new Part1(input);
    part2 = new Part2(input);


  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day3.class.getResourceAsStream("/d3/input.txt")),
        StandardCharsets.UTF_8);
    Day3 task = new Day3(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  public long part1() {
    return part1.backpacks.stream().mapToLong(Backpack::rearrangementPriority).sum();
  }

  public long part2() {
    return part2.groups.stream().mapToLong(Group::groupPriority).sum();
  }

  static class Part1 {

    private final List<Backpack> backpacks = new ArrayList<>();

    public Part1(String input) {
      for (String line : input.split("\n")) {
        backpacks.add(new Backpack(line));
      }
    }

    static class Backpack {

      private final Set<Integer> firstCompartment;
      private final Set<Integer> secondCompartment;

      Backpack(String line) {
        this.firstCompartment = line.substring(0, line.length() / 2).chars().boxed()
            .collect(Collectors.toSet());
        this.secondCompartment = line.substring(line.length() / 2).chars().boxed()
            .collect(Collectors.toSet());
      }

      public long rearrangementPriority() {
        HashSet<Integer> common = new HashSet<>(firstCompartment);
        common.retainAll(secondCompartment);
        return common.stream().mapToLong(c -> {
              if (c >= 'a') {
                return c - 'a' + 1;
              } else {
                return c - 'A' + 27;
              }
            })
            .sum();
      }
    }

  }

  static class Part2 {

    private final LinkedList<Group> groups = new LinkedList<>();

    public Part2(String input) {
      int i = 0;
      for (String line : input.split("\n")) {
        if (i++ % 3 == 0) {
          groups.add(new Group());
        }
        groups.getLast().addBackpack(line);
      }
    }

    static class Group {

      private final List<Set<Integer>> backpacks = new ArrayList<>();


      void addBackpack(String line) {
        backpacks.add(line.chars().boxed()
            .collect(Collectors.toSet()));
      }

      public long groupPriority() {
        HashSet<Integer> common = new HashSet<>(backpacks.get(0));
        common.retainAll(backpacks.get(1));
        common.retainAll(backpacks.get(2));
        return common.stream().mapToLong(c -> {
              if (c >= 'a') {
                return c - 'a' + 1;
              } else {
                return c - 'A' + 27;
              }
            })
            .sum();
      }
    }


  }

}
