package site.yuanzhou.entity.resp;



import java.io.Serializable;
import java.util.Objects;

public class Response<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        // 返回json 格式
        return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"data\":" + data + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(code, response.code) && Objects.equals(msg, response.msg) && Objects.equals(data, response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, data);
    }

    private Response() {
    }

    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }

    public static <T> Response<T> success() {
        return new Response<>(200, "success", null);
    }

    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(200, msg, data);
    }


    public static <T> Response<T> success(Integer code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public static <T> Response<T> fail(Integer code, String msg) {
        return new Response<>(code, msg, null);
    }

    public static <T> Response<T> fail(Integer code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public static <T> Response<T> fail(String msg) {
        return new Response<>(500, msg, null);
    }

    public static <T> Response<T> fail() {
        return new Response<>(500, "内部错误", null);
    }
}
