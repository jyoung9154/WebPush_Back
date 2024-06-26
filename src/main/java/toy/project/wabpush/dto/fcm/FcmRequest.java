package toy.project.wabpush.dto.fcm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FcmRequest {

    /* 클라이언트 토큰 */
    private String token;

    /* 전달 메세지 제목 */
    private String title;

    /* 전달 메세지 내용 */
    private String body;
}
