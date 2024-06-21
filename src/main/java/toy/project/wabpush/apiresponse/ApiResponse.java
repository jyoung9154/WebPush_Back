package toy.project.wabpush.apiresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ApiResponse<T> {
    private final ApiResponseStatus status;
    private final T data;
    private final ApiError error;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseStatus.SUCCESS, data, null);
    }

    public static <T> ApiResponse<T> failure(ApiError error) {
        return new ApiResponse<>(ApiResponseStatus.FAILURE, null, error);
    }

    public static <T> ApiResponse<T> exception(ApiError error) {
        return new ApiResponse<>(ApiResponseStatus.EXCEPTION, null, error);
    }
}

