package hcloud.demo.view;

import com.github.pagehelper.PageInfo;
import hcloud.demo.enums.ResultStatus;
import org.springframework.util.StringUtils;

import java.util.List;

public class ApiResponse<T> {
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 201;
    private Integer status;
    private String msg;
    private T body;

    public ApiResponse() {
        this.status = SUCCESS_CODE;
        this.msg = "SUCCESS";
    }

    public ApiResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiResponse(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }

    public static ApiResponse<String> success() {
        return new ApiResponse(SUCCESS_CODE, "请求成功");
    }

    public static ApiResponse<String> success(String msg) {
        return new ApiResponse(SUCCESS_CODE, msg);
    }

    public static <T> ApiResponse<T> success(String msg, T result) {
        return new ApiResponse(SUCCESS_CODE, msg, result);
    }

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse(SUCCESS_CODE, "请求成功", result);
    }

    public static ApiResponse<String> success(Integer status, String msg) {
        return new ApiResponse(status, msg);
    }

    public static <T> ApiResponse<T> success(Integer status, String msg, T result) {
        return new ApiResponse(status, msg, result);
    }

    public static <T> ApiResponse<ListModel<T>> success(List<T> result) {
        ListModel<T> listModel = new ListModel();
        listModel.setData(result);
        return new ApiResponse(SUCCESS_CODE, "请求成功", listModel);
    }

    public static <T> ApiResponse<PageModel<T>> success(String msg, PageInfo<T> pageInfo) {
        msg = StringUtils.isEmpty(msg) ? "请求成功" : msg;
        if (pageInfo == null) {
            return new ApiResponse(SUCCESS_CODE, msg, (Object) null);
        } else {
            PageModel<T> pageModel = new PageModel();
            pageModel.setCurPage(pageInfo.getPageNum());
            pageModel.setPageSize(pageInfo.getPageSize());
            pageModel.setCount(pageInfo.getTotal());
            pageModel.setPageCount(pageInfo.getPages());
            pageModel.setData(pageInfo.getList());
            return new ApiResponse(SUCCESS_CODE, msg, pageModel);
        }
    }

    public static <T> ApiResponse<PageModel<T>> success(PageInfo<T> pageInfo) {
        return success("", pageInfo);
    }

    public static <T> ApiResponse<PageModel<T>> success(PageModel<T> pageModel) {
        return pageModel == null ? new ApiResponse(SUCCESS_CODE, "请求成功", (Object) null) : new ApiResponse(SUCCESS_CODE, "请求成功", pageModel);
    }

    public static ApiResponse<String> fail() {
        return new ApiResponse(FAIL_CODE, "请求失败");
    }

    public static ApiResponse<String> fail(Integer code) {
        return new ApiResponse(code, ResultStatus.getByCode(code).getMsg());
    }

    public static ApiResponse<String> fail(String msg) {
        return new ApiResponse(FAIL_CODE, msg);
    }

    public static ApiResponse<String> fail(Integer code, String msg) {
        return new ApiResponse(code, msg);
    }

    public static <T> ApiResponse<T> fail(T result) {
        return new ApiResponse(FAIL_CODE, "请求失败", result);
    }

    public <T> ApiResponse<T> fail(String message, T result) {
        return new ApiResponse(FAIL_CODE, message, result);
    }

    public static <T> ApiResponse<T> fail(Integer code, String msg, T result) {
        return new ApiResponse(code, msg, result);
    }

    public static ApiResponse<String> fail(ResultStatus resultStatus) {
        return new ApiResponse(resultStatus.getCode(), resultStatus.getMsg());
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getBody() {
        return this.body;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ApiResponse)) {
            return false;
        } else {
            ApiResponse<?> other = (ApiResponse) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label47;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$body = this.getBody();
                Object other$body = other.getBody();
                if (this$body == null) {
                    if (other$body != null) {
                        return false;
                    }
                } else if (!this$body.equals(other$body)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ApiResponse;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $body = this.getBody();
        result = result * 59 + ($body == null ? 43 : $body.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ApiResponse(status=" + this.getStatus() + ", msg=" + this.getMsg() + ", body=" + this.getBody() + ")";
    }
}
