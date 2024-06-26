package toy.project.wabpush.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /* PUSH 메세지 전송 기능 */
    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Object>> pushMessage(@RequestBody @Validated FcmRequest fcmRequest) throws IOException {

        /* PUSH 메세지 */
        int result = fcmService.sendMessageTo(fcmRequest);

        /* 결과 전달 객체 */
        ApiResponse<Object> arw = ApiResponse
                .builder()
                .data(result)
                .status(SUCCESS)
                .build();

        /* 전달 */
        return new ResponseEntity<>(arw, HttpStatus.OK);
    }
}