package toy.project.wabpush.service.fcm;

import org.springframework.stereotype.Service;
import toy.project.wabpush.dto.fcm.FcmRequest;

import java.io.IOException;

public interface FCM_Service {

    int sendMessageTo(FcmRequest fcmRequest) throws IOException;
}
