package lshh.pollservice.dto.schedule;

import lshh.pollservice.domain.entity.schedule.Schedule;
import lshh.pollservice.dto.Result;

import java.time.Instant;

public record ScheduleDetail(
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
)  implements Result {
    public static ScheduleDetail from(Schedule schedule) {
        return new ScheduleDetail(
                schedule.getId(),
                schedule.getStartAt(),
                schedule.getEndAt(),
                schedule.getState()
        );
    }
}
