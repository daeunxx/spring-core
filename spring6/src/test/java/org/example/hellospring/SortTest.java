package org.example.hellospring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortTest {

  Sort sort;

  @BeforeEach
  void init() {
    sort = new Sort();

    // 테스트 실행할 때마다 인스턴스 생성
    System.out.println(this);
  }

  @Test
  void sort() {
    List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));
    assertThat(list).isEqualTo(List.of("b", "aa"));
  }

  @Test
  void sort3Items() {
    List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));
    assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
  }

  @Test
  void sortAlreadySorted() {
    List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));
    assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
  }
}