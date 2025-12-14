package koo.mybatis_study_project.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Getter + @Setter + @ToString + @RequiredArgsConstructor == @Data
@Getter @Setter @ToString
public class RequestBoardDto {

    @NotNull(message = "작성자 이름은 Null일 수 없습니다.") // Null만 허용하지 않음, ""나 " "는 허용한다.
    @Size(min = 1, max = 10, message = "작성자 이름은 1글자 이상 10글자 이하여야합니다.")
    private String boardWriter;
    @NotEmpty // Null과 ""를 허용하지 않음, " "는 허용한다.
    private String boardPass; // 게시글 비밀번호
    @NotBlank(message = "빈 값 입력 금지") // Null과 "", " "를 허용하지 않는다.
    private String boardTitle;
    private String boardContents;
//    @Email // 이메일 형식이 아닌 경우 Exception 발생
//    private String emaill
//    Custom Exception도 작성가능

}
