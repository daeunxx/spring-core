package org.example.hellospring.learningtest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {

  // Clock을 이용해서 현재 시간 가져옴
  @Test
  void clock() {
    Clock clock = Clock.systemDefaultZone();
    LocalDateTime ldt1 = LocalDateTime.now(clock);
    LocalDateTime ldt2 = LocalDateTime.now(clock);

    assertThat(ldt2).isAfter(ldt1);
  }

  // Clock을 Test에서 사용 시, 원하는 시간 지정
  @Test
  void fixedClock() {
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    LocalDateTime ldt1 = LocalDateTime.now(clock);
    LocalDateTime ldt2 = LocalDateTime.now(clock);
    LocalDateTime ldt3 = LocalDateTime.now(clock).plusHours(1);

    assertThat(ldt2).isEqualTo(ldt1);
    assertThat(ldt3).isEqualTo(ldt1.plusHours(1));
  }
}
