package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.IOUtils;

public class Day9 {

  private final boolean printEnabled;
  private final Rope rope1 = new Rope(2);
  private final Rope rope2 = new Rope(10);

  public Day9(String input, boolean printEnabled) {
    this.printEnabled = printEnabled;
    for (String line : input.split("\n")) {
      String[] move = line.split(" ");
      int positions = Integer.parseInt(move[1]);
      int x = 0;
      int y = 0;
      switch (move[0]) {
        case "R" -> x = 1;
        case "L" -> x = -1;
        case "D" -> y = 1;
        case "U" -> y = -1;
      }
      for (int i = 0; i < positions; i++) {
        rope1.move(x, y);
        rope2.move(x, y);
        if (printEnabled) {
          rope1.print(6);
          rope2.print(6);
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day9.class.getResourceAsStream("/d9/input.txt")),
        StandardCharsets.UTF_8);
    Day9 task = new Day9(input, false);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  public int part1() {
    return rope1.tailPositions.size();
  }

  public int part2() {
    return rope2.tailPositions.size();
  }

  record Position(int x, int y) {

  }

  static class Rope {

    private final List<Position> knots = new ArrayList<>();
    private final Set<Position> tailPositions = new HashSet<>();


    Rope(int size) {
      for (int i = 0; i < size; i++) {
        knots.add(new Position(0, 0));
      }
      tailPositions.add(tail());
    }


    public void move(int x, int y) {
      moveHead(x, y);
      for (int i = 1; i < knots.size(); i++) {
        moveKnot(i);
      }
      tailPositions.add(tail());
    }

    private void moveHead(int x, int y) {
      Position head = knots.get(0);
      knots.set(0, new Position(head.x + x, head.y + y));
    }

    private void moveKnot(int index) {
      Position head = knots.get(index - 1);
      Position tail = knots.get(index);

      if (head.x == tail.x - 2 && head.y == tail.y - 2) {
        tail = new Position(tail.x - 1, tail.y - 1);
      } else if (head.x == tail.x + 2 && head.y == tail.y - 2) {
        tail = new Position(tail.x + 1, tail.y - 1);
      } else if (head.x == tail.x + 2 && head.y == tail.y + 2) {
        tail = new Position(tail.x + 1, tail.y + 1);
      } else if (head.x == tail.x - 2 && head.y == tail.y + 2) {
        tail = new Position(tail.x - 1, tail.y + 1);
      } else if (head.x == tail.x - 2) {
        tail = new Position(tail.x - 1, head.y);
      } else if (head.x == tail.x + 2) {
        tail = new Position(tail.x + 1, head.y);
      } else if (head.y == tail.y - 2) {
        tail = new Position(head.x, tail.y - 1);
      } else if (head.y == tail.y + 2) {
        tail = new Position(head.x, tail.y + 1);
      }
      knots.set(index, tail);
    }

    Position tail() {
      return knots.get(knots.size() - 1);
    }

    void print(int gridSize) {
      for (int y = -gridSize; y < gridSize; y++) {
        x:
        for (int x = -gridSize; x < gridSize; x++) {
          for (int i = 0; i < knots.size(); i++) {
            Position knot = knots.get(i);
            if (knot.x == x && knot.y == y) {
              System.out.print(i == 0 ? "H" : i);
              continue x;
            }
          }
          if (y == 0 && x == 0) {
            System.out.print("s");
          } else {
            System.out.print(".");
          }
        }
        System.out.println();
      }
      System.out.println();
    }
  }
}
