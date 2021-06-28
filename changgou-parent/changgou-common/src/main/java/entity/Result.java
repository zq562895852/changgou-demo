package entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返回结果实体类
 */
public class Result<T> {
    /**
     *
     * ApiModelProperty 参数说明
     value：字段说明，
     name：重写属性名字，
     dataType：重写属性类型，
     required：是否必须，默认false，
     example：举例，
     hidden：隐藏。
     */
    @ApiModelProperty(value = "是否返回成功")
    private boolean flag;//是否成功
    @ApiModelProperty(value = "返回的状态码")
    private Integer code;//返回码
    @ApiModelProperty(value = "返回的提示信息")
    private String message;//返回消息

    private T data;//返回数据

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T)data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "执行成功";
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
