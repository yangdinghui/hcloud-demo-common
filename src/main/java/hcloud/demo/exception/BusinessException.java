package hcloud.demo.exception;

import hcloud.demo.enums.ResultStatus;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code;

    public BusinessException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
        this.code = resultStatus.getCode();
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
