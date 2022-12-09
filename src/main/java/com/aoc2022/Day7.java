package com.aoc2022;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalLong;
import java.util.stream.LongStream;
import org.apache.commons.io.IOUtils;

public class Day7 {

  private final Dir root = new Dir(null);

  Day7(String input) {
    Dir currentDir = null;
    for (String cmd : input.split("\\n")) {
      if (cmd.equals("$ cd /")) {
        currentDir = root;
      } else if (cmd.equals("$ cd ..")) {
        currentDir = currentDir.parent;
      } else if (cmd.startsWith("$ cd ")) {
        currentDir = currentDir.dirs.get(cmd.substring("$ cd ".length()));
      } else if (cmd.startsWith("dir ")) {
        currentDir.addDir(cmd.substring("dir ".length()));
      } else if (!cmd.equals("$ ls")) {
        String[] fileDef = cmd.split(" ");
        currentDir.addFile(fileDef[1], Long.parseLong(fileDef[0]));
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String input = IOUtils.toString(
        requireNonNull(Day7.class.getResourceAsStream("/d7/input.txt")),
        StandardCharsets.UTF_8);
    Day7 task = new Day7(input);
    System.out.println("Part1: " + task.part1());
    System.out.println("Part2: " + task.part2());
  }

  long getDirSizeLessThan(int atMost) {
    return root.getTotalDirSizeLessThan(atMost);
  }

  long findSmallestGreaterThan(long atLeast) {
    return root.findSmallestGreaterThan(atLeast).orElseThrow();
  }

  long part1() {
    return getDirSizeLessThan(100000);
  }

  public long part2() {
    long needSpace = 30000000 - (70000000L - root.size());
    return findSmallestGreaterThan(needSpace);
  }

  static class Dir {

    private final Dir parent;
    private final Map<String, File> files = new HashMap<>();
    private final Map<String, Dir> dirs = new HashMap<>();
    private Long size;

    Dir(Dir parent) {
      this.parent = parent;
    }

    long size() {
      if (size == null) {
        size =
            files.values().stream().mapToLong(File::size).sum() + dirs.values().stream()
                .mapToLong(Dir::size)
                .sum();
      }
      return size;
    }

    void addFile(String name, long size) {
      files.putIfAbsent(name, new File(name, size));
    }

    void addDir(String name) {
      dirs.putIfAbsent(name, new Dir(this));
    }

    long getTotalDirSizeLessThan(int atMost) {
      return (size() > atMost ? 0L
          : size()) + dirs.values().stream().mapToLong(d -> d.getTotalDirSizeLessThan(atMost))
                 .sum();
    }

    OptionalLong findSmallestGreaterThan(long atLeast) {
      if (size() > atLeast) {
        return LongStream.concat(LongStream.of(size()),
            dirs.values().stream().map(d -> d.findSmallestGreaterThan(atLeast))
                .filter(OptionalLong::isPresent).mapToLong(OptionalLong::getAsLong)).min();
      } else {
        return OptionalLong.empty();
      }
    }
  }

  record File(String name, long size) {

  }

}
