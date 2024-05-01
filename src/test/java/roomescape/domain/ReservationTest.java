package roomescape.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.exception.ExceptionType;
import roomescape.exception.RoomescapeException;

class ReservationTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.now();
    private static final ReservationTime DEFAULT_TIME = new ReservationTime(1L, LocalTime.now());
    private static final Theme DEFAULT_THEME = new Theme(1L, "이름", "설명", "썸네일");

    @DisplayName("생성 테스트")
    @Test
    void constructTest() {
        assertThatThrownBy(() -> new Reservation(null, DEFAULT_DATE, DEFAULT_TIME, DEFAULT_THEME))
                .isInstanceOf(RoomescapeException.class)
                .hasMessage(ExceptionType.NAME_EMPTY.getMessage());
    }

    @Test
    @DisplayName("날짜를 기준으로 비교를 잘 하는지 확인.")
    void compareTo() {
        Reservation first = new Reservation(1L, "폴라", LocalDate.of(1999, 12, 1), new ReservationTime(
                LocalTime.of(16, 30)), DEFAULT_THEME);
        Reservation second = new Reservation(2L, "로빈", LocalDate.of(1998, 1, 8), new ReservationTime(
                LocalTime.of(16, 30)), DEFAULT_THEME);
        int compareTo = first.compareTo(second);
        Assertions.assertThat(compareTo)
                .isGreaterThan(0);
    }
}
