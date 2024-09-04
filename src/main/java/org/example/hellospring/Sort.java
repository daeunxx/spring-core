package org.example.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

  public static void main(String[] args) {
    List<Integer> scores = Arrays.asList(5, 7, 1, 9, 2, 8);
    Collections.sort(scores);
    scores.forEach(System.out::println);

    List<String> strings = Arrays.asList("z", "x", "spring", "java");
    Collections.sort(strings);
    strings.forEach(System.out::println);

    System.out.println();

    //문자열 길이 순서대로 정렬
    Collections.sort(strings, (o1, o2) -> o1.length() - o2.length());
    strings.forEach(System.out::println);
  }
}
