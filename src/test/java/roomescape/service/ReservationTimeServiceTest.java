package roomescape.service;

import static roomescape.exception.ExceptionType.DUPLICATE_RESERVATION_TIME;

import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.dto.ReservationTimeRequest;
import roomescape.exception.RoomescapeException;
import roomescape.repository.CollectionReservationRepository;
import roomescape.repository.CollectionReservationTimeRepository;

class ReservationTimeServiceTest {

    @Test
    @DisplayName("중복된 시간은 생성할 수 없는지 검증")
    void saveFailCauseDuplicate() {
        CollectionReservationTimeRepository reservationTimeRepository = new CollectionReservationTimeRepository();
        CollectionReservationRepository reservationRepository = new CollectionReservationRepository(
                reservationTimeRepository);
        ReservationTimeService reservationTimeService = new ReservationTimeService(reservationRepository,
                reservationTimeRepository);
        ReservationTimeRequest reservationTimeRequest = new ReservationTimeRequest(LocalTime.of(10, 0));
        reservationTimeService.save(reservationTimeRequest);

        Assertions.assertThatThrownBy(() -> reservationTimeService.save(reservationTimeRequest))
                .isInstanceOf(RoomescapeException.class)
                .hasMessage(DUPLICATE_RESERVATION_TIME.getMessage());
    }
}