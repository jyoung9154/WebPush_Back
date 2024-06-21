package toy.project.wabpush.apiresponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiError {
    private final int code;
    private final String message;
}
