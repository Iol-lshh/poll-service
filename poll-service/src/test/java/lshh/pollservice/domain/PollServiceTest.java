package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.dto.poll.type.PollState;
import lshh.pollservice.dto.poll.type.SelectPollOptionType;
import lshh.pollservice.dto.poll.schedule.PollScheduleCreateCommand;
import lshh.pollservice.dto.poll.schedule.PollScheduleDetail;
import lshh.pollservice.dto.poll.schedule.PollScheduleOptionInputDto;
import lshh.pollservice.dto.schedule.ScheduleCreate;
import lshh.pollservice.dto.schedule.ScheduleDetail;
import lshh.pollservice.dto.schedule.ScheduleState;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class PollServiceTest {
    @Autowired
    private PollService pollService;
    @Autowired
    private ScheduleService scheduleService;

    @Nested
    class CreateTest {
        @Test
        void create() {
            ScheduleCreate scheduleCreate = new ScheduleCreate(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule = scheduleService.create(scheduleCreate);
            ScheduleCreate scheduleCreate2 = new ScheduleCreate(Instant.now(), Instant.now(), ScheduleState.OPENED);
            ScheduleDetail schedule2 = scheduleService.create(scheduleCreate2);
            log.info(schedule.toString());
            log.info(schedule2.toString());
            assertNotNull(schedule.id());
            assertNotNull(schedule2.id());
            // given
            PollScheduleCreateCommand command = new PollScheduleCreateCommand(
                    "title",
                    "description",
                    PollState.OPENED,
                    SelectPollOptionType.ONE_VOTE,
                    List.of(
                            new PollScheduleOptionInputDto(schedule.id()),
                            new PollScheduleOptionInputDto(schedule2.id())
                    )
            );
            log.info(command.toString());
            // when
            PollScheduleDetail result = pollService.create(command);
            // then
            assertNotNull(result);
            assertNotNull(result.id());
            assertEquals(2, result.options().size());
            result.options().forEach(option -> assertNotNull(option.id()));
        }
    }
}