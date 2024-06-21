package toy.project.wabpush.dto.fcm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FcmResponse {

    private boolean validateOnly;
    private FcmResponse.Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private FcmResponse.Notification notification;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }

}
