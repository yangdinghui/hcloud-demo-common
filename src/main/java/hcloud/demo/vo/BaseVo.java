package hcloud.demo.vo;

import java.io.Serializable;

public class BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private long tokenTimeOut;

    public BaseVo() {
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenTimeOut() {
        return this.tokenTimeOut;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenTimeOut(long tokenTimeOut) {
        this.tokenTimeOut = tokenTimeOut;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseVo)) {
            return false;
        } else {
            BaseVo other = (BaseVo) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$token = this.getToken();
                Object other$token = other.getToken();
                if (this$token == null) {
                    if (other$token == null) {
                        return this.getTokenTimeOut() == other.getTokenTimeOut();
                    }
                } else if (this$token.equals(other$token)) {
                    return this.getTokenTimeOut() == other.getTokenTimeOut();
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseVo;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $token = this.getToken();
        result = result * 59 + ($token == null ? 43 : $token.hashCode());
        long $tokenTimeOut = this.getTokenTimeOut();
        result = result * 59 + (int) ($tokenTimeOut >>> 32 ^ $tokenTimeOut);
        return result;
    }

    @Override
    public String toString() {
        return "BaseVo(token=" + this.getToken() + ", tokenTimeOut=" + this.getTokenTimeOut() + ")";
    }
}
