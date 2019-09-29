package hcloud.demo.enums;

public enum ResultStatus {
    SYSTEM_ERROR(500, "系统错误"),
    PARAMETER_CHECK_ERROR(501, "参数校验错误"),
    UNLOGIN_ERROR(401, "用户未登录或登录状态超时失效"),
    AUTH_VALID_ERROR(402, "用户权限不足"),
    ILLEGAL_REQUEST_ERROR(403, "非法请求"),
    MD5_ENCRYPT_ERRO(300, "MD5加密异常"),
    JAVABEANTOMAP_ERROR(301, "JavaBean转Map异常"),
    FILEUPLOAD_ERROR(302, "文件上传异常"),
    FILEDOWN_ERROR(303, "文件下载异常"),
    FILETYPE_ERROR(304, "不支持的文件类型上传"),
    REDIS_ERROR(305, "Redis操作异常"),
    SERIALRULE_ERROR(600, "流水号生成异常");

    private final Integer code;
    private final String msg;

    private ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return this.code.toString();
    }

    public static ResultStatus getByCode(Integer code) {
        ResultStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultStatus _enum = var1[var3];
            if (_enum.getCode() == code || _enum.getCode().equals(code)) {
                return _enum;
            }
        }

        return null;
    }
}
