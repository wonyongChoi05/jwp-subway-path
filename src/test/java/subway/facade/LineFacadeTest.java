package subway.facade;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import subway.line.facade.LineFacade;
import subway.line.presentation.dto.LineRequest;
import subway.line.presentation.dto.LineResponse;
import subway.line.service.LineService;
import subway.section.service.SectionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LineFacadeTest {

    @InjectMocks
    LineFacade lineFacade;

    @Mock
    LineService lineService;

    @Mock
    SectionService sectionService;

    @DisplayName("모든 호선을 조회한다.")
    @Test
    void findAll() {
        // given
        LineResponse response1 = new LineResponse(1L, "1호선", "검정");
        LineResponse response2 = new LineResponse(2L, "2호선", "초록");
        LineResponse response3 = new LineResponse(3L, "3호선", "노랑");
        List<LineResponse> results = List.of(response1, response2, response3);
        doReturn(results).when(lineService).findAll();

        // when
        List<LineResponse> responses = lineFacade.getAll();

        // then
        assertAll(
                () -> assertThat(responses).extracting(LineResponse::getId)
                        .contains(1L, 2L, 3L),
                () -> assertThat(responses).extracting(LineResponse::getName)
                        .contains("1호선", "2호선", "3호선"),
                () -> assertThat(responses).extracting(LineResponse::getColor)
                        .contains("검정", "초록", "노랑")
        );
    }

    @DisplayName("호선의 아이디를 입력받아 조회한다.")
    @ParameterizedTest
    @CsvSource(value = {"1"})
    void getLineResponseById(Long id) {
        // given
        LineResponse response = new LineResponse(1L, "1호선", "검정");
        doReturn(response).when(lineService).findById(id);

        // when
        LineResponse result = lineFacade.getLineResponseById(id);

        // then
        assertAll(
                () -> Assertions.assertThat(result.getId()).isEqualTo(1L),
                () -> Assertions.assertThat(result.getName()).isEqualTo("1호선"),
                () -> Assertions.assertThat(result.getColor()).isEqualTo("검정")
        );
    }

    @DisplayName("호선의 아이디를 입력받아 호선의 정보를 수정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1"})
    void updateLine(Long id) {
        // given
        LineRequest request = new LineRequest("2호선", "초록", 10);

        // when
        lineService.update(id, request);

        // then
        verify(lineService, times(1)).update(id, request);
    }

    @DisplayName("호선의 아이디를 입력받아 호선의 정보를 수정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1"})
    void deleteLineById(Long id) {
        // when
        lineService.deleteById(id);

        // then
        verify(lineService, times(1)).deleteById(id);
    }

}
