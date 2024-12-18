package lshh.pollservice.dto.poll.type;

import org.springframework.core.convert.converter.Converter;

public class PollStateWebConverter implements Converter<String, PollState> {
    @Override
    public PollState convert(String source) {
        return PollState.valueOf(source.toUpperCase());
    }
}
