package toy.project.wabpush.service.fcm.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import toy.project.wabpush.dto.fcm.FcmRequest;
import toy.project.wabpush.dto.fcm.FcmResponse;
import toy.project.wabpush.service.fcm.FCM_Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FCM_ServiceImpl implements FCM_Service {

    /* FCM Key 데이터 */
    @Value("${firebase.key-path}")
    private String fcmKeyPath;

    /* 메세지 전송 기능 */
    @Override
    public int sendMessageTo(FcmRequest fcmRequest) throws IOException {

        /* 메세지 가공 객체 */
        String message = makeMessage(fcmRequest);
        RestTemplate restTemplate = new RestTemplate();

        /* UTF-8 인코딩 */
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        /* 메세지 전송 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity entity = new HttpEntity<>(message, headers);

        String API_URL = "https://fcm.googleapis.com/v1/projects/webpush-79c07/messages:send";
        ResponseEntity response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        return response.getStatusCode() == HttpStatus.OK ? 1 : 0;
    }

    /* Firebase Admin SDK의 비공개 키를 참조하여 Bearer 토큰을 발급 */
    private String getAccessToken() throws IOException {

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(fcmKeyPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    /* FCM 전송 정보를 기반으로 메시지를 구성 */
    private String makeMessage(FcmRequest fcmRequest) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        FcmResponse fcmResponse = FcmResponse.builder()
                .message(FcmResponse.Message.builder()
                        .token(fcmRequest.getToken())
                        .notification(FcmResponse.Notification.builder()
                                .title(fcmRequest.getTitle())
                                .body(fcmRequest.getBody())
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return om.writeValueAsString(fcmResponse);
    }
}
