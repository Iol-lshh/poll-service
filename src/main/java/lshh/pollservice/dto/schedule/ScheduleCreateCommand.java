package lshh.pollservice.dto.schedule;

import lshh.pollservice.dto.Request;

import java.time.Instant;

public record ScheduleCreateCommand(
        Instant startAt,
        Instant endAt,
        ScheduleState state
)  implements Request {
}
