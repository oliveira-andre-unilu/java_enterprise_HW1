package lu.uni.jakartaee.moviefy;

public class Result {
  private String msg;
  private String value;

  public Result()
  {
    msg = "";
    value="";
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
