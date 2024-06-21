package toy.project.wabpush.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.project.wabpush.apiresponse.ApiResponse;
import toy.project.wabpush.dto.fcm.FcmRequest;
import toy.project.wabpush.service.fcm.FCM_Service;

import java.io.IOException;

import static toy.project.wabpush.apiresponse.ApiResponseStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/fcm")
public class FcmController {

    private final FCM_Service fcmService;

    public FcmController(FCM_Service fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Object>> pushMessage(@RequestBody @Validated FcmRequest fcmRequest) throws IOException {
        log.debug("[+] 푸시 메시지를 전송합니다. ");
        int result = fcmService.sendMessageTo(fcmRequest);

        ApiResponse<Object> arw = ApiResponse
                .builder()
                .data(result)
                .status(SUCCESS)
                .build();
        return new ResponseEntity<>(arw, HttpStatus.OK);
    }
}