package toy.project.wabpush.dto.fcm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FcmRequest {

    private String token;
    private String title;
    private String body;
}
