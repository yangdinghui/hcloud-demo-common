package hcloud.demo.handler;

import hcloud.demo.enums.ResultStatus;
import hcloud.demo.exception.BusinessException;
import hcloud.demo.view.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public ApiResponse<String> handlerException(Exception e) {
        if (e instanceof BusinessException) {
            log.error("业务异常：" + e.getMessage());
            BusinessException businessException = (BusinessException) e;
            return ApiResponse.fail(businessException.getCode(), businessException.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = exception.getBindingResult().getAllErrors();
            StringBuffer sb = new StringBuffer();
            Iterator var13 = errors.iterator();

            while (var13.hasNext()) {
                ObjectError error = (ObjectError) var13.next();
                String message = error.getDefaultMessage();
                sb.append(message).append(";");
            }

            String message = sb.toString().substring(0, sb.toString().lastIndexOf(";"));
            log.error("参数校验错误：" + message);
            return ApiResponse.fail(202, message);
        } else if (!(e instanceof ConstraintViolationException)) {
            log.error("系统异常：", e);
            return ApiResponse.fail(ResultStatus.SYSTEM_ERROR.getCode(), "系统后台数据处理异常");
        } else {
            ConstraintViolationException exception = (ConstraintViolationException) e;
            StringBuffer sb = new StringBuffer();
            Iterator var4 = exception.getConstraintViolations().iterator();

            while (var4.hasNext()) {
                ConstraintViolation constraint = (ConstraintViolation) var4.next();
                sb.append(constraint.getInvalidValue()).append("值不正确，").append(constraint.getMessage()).append(";");
            }

            String message = sb.toString().substring(0, sb.toString().lastIndexOf(";"));
            log.error("参数校验错误：" + message);
            return ApiResponse.fail(202, message);
        }
    }
}
