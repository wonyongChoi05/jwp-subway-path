package subway.exception;

import subway.global.error.exception.ErrorCode;
import subway.global.error.exception.SubwayException;

public class StationsAlreadyExistException extends SubwayException {

    public static SubwayException THROW = new StationsAlreadyExistException();

    public StationsAlreadyExistException() {
        super(new ErrorCode(400, "입력한 역들이 모두 존재합니다."));
    }

}
