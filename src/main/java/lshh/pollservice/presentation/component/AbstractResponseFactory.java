package lshh.pollservice.presentation.component;

import lshh.pollservice.dto.Result;
import lshh.pollservice.presentation.vo.ResponseState;

import java.util.Map;

public abstract class AbstractResponseFactory {

    public Map<String, Object> ok(Object data) {
        return Map.of(
                "status", ResponseState.OK,
                "data", data
        );
    }

    public Map<String, Object> success(Object data){
        return Map.of(
                "status", ResponseState.SUCCESS,
                "data", data
        );
    }

    public Map<String, Object> result(Result result) {
        ResponseState state = ResponseState.from(result);
        return Map.of(
                "status", state,
                "message", state.getDefaultMessage()
        );
    }
    public Map<String, Object> fail(Object data){
        return Map.of(
                "status", ResponseState.FAIL,
                "message", data
        );
    }
    public Map<String, Object> error(Object data){
        return Map.of(
                "status", ResponseState.ERROR,
                "message", data
        );
    }
}
