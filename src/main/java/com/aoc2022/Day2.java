package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;

public class Day2 {

  private final Part2 part2;
  private final Part1 part1;

  static class Part1 {

    private enum Shape {
      ROCK(1, "A", "X"),
      PAPER(2, "B", "Y"),
      SCISSORS(3, "C", "Z");
      final Set<String> codes;
      final int score;
      static final Map<String, Shape> CODE_TO_SHAPE = new HashMap<>();

      static {
        for (Shape shape : values()) {
          shape.codes.forEach(code -> CODE_TO_SHAPE.put(code, shape));
        }
      }

      Shape(int score, String... codes) {
        this.score = score;
        this.codes = Set.of(codes);
      }

      static Shape fromCode(String code) {
        return CODE_TO_SHAPE.get(code);
      }

      int score() {
        return score;
      }

      public boolean wins(Shape opponentShape) {
        return switch (this) {
          case ROCK -> opponentShape == SCISSORS;
          case PAPER -> opponentShape == ROCK;
          case SCISSORS -> opponentShape == PAPER;
        };
      }
    }

    private static class Game {

      private final Shape opponentShape;
      private final Shape myShape;

      private Game(Shape opponentShape, Shape myShape) {
        this.opponentShape = opponentShape;
        this.myShape = myShape;
      }


      int score() {
        int outcome;
        if (myShape.wins(opponentShape)) {
          outcome = 6;
        } else if (opponentShape.wins(myShape)) {
          outcome = 0;
        } else {
          outcome = 3;
        }
        return myShape.score + outcome;
      }
    }

    private final List<Game> games = new ArrayList<>();

    public Part1(String input) {
      for (String lines : input.split("\n")) {
        String[] shapes = lines.split(" ");
        games.add(new Game(Shape.fromCode(shapes[0]), Shape.fromCode(shapes[1])));
      }
    }

    public long score() {
      return games.stream().mapToLong(Game::score).sum();
    }


  }

  static class Part2 {


    private enum Shape {
      ROCK(1, "A"),
      PAPER(2, "B"),
      SCISSORS(3, "C");
      final Set<String> codes;
      final int score;
      static final Map<String, Shape> CODE_TO_SHAPE = new HashMap<>();

      static {
        for (Shape shape : values()) {
          shape.codes.forEach(code -> CODE_TO_SHAPE.put(code, shape));
        }
      }

      Shape(int score, String... codes) {
        this.score = score;
        this.codes = Set.of(codes);
      }

      static Shape fromCode(String code) {
        return CODE_TO_SHAPE.get(code);
      }

      int score() {
        return score;
      }

      public boolean wins(Shape opponentShape) {
        return switch (this) {
          case ROCK -> opponentShape == SCISSORS;
          case PAPER -> opponentShape == ROCK;
          case SCISSORS -> opponentShape == PAPER;
        };
      }
    }

    static enum Result {
      LOSE("X"), DRAW("Y"), WIN("Z");
      static final Map<String, Result> CODE_TO_RESULT = new HashMap<>();

      static {
        for (Result result : values()) {
          CODE_TO_RESULT.put(result.code, result);
        }
      }

      private final String code;

      Result(String code) {
        this.code = code;
      }

      static Result fromCode(String code) {
        return CODE_TO_RESULT.get(code);
      }
    }

    private static class Game {

      private final Shape opponentShape;
      private final Shape myShape;

      private Game(Shape opponentShape, Result result) {
        this.opponentShape = opponentShape;
        myShape = switch (result) {

          case LOSE ->
              Arrays.stream(Shape.values()).filter(shape -> opponentShape.wins(shape)).findFirst()
                  .orElseThrow();
          case DRAW -> opponentShape;
          case WIN ->
              Arrays.stream(Shape.values()).filter(shape -> shape.wins(opponentShape)).findFirst()
                  .orElseThrow();
        };
      }


      int score() {
        int outcome;
        if (myShape.wins(opponentShape)) {
          outcome = 6;
        } else if (opponentShape.wins(myShape)) {
          outcome = 0;
        } else {
          outcome = 3;
        }
        return myShape.score + outcome;
      }
    }

    private final List<Game> games = new ArrayList<>();

    public Part2(String input) {
      for (String lines : input.split("\n")) {
        String[] shapes = lines.split(" ");
        games.add(new Game(Shape.fromCode(shapes[0]), Result.fromCode(shapes[1])));
      }
    }

    public long score() {
      return games.stream().mapToLong(Game::score).sum();
    }
  }

  public Day2(String input) {
    part1 = new Part1(input);
    part2 = new Part2(input);
  }

  public long part1() {
    return part1.score();
  }

  public long part2() {
    return part2.score();
  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day2.class.getResourceAsStream("/d2/input.txt")),
        StandardCharsets.UTF_8);
    Day2 task = new Day2(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }


}
