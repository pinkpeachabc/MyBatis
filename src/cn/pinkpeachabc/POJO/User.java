package cn.pinkpeachabc.POJO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据库用户
 */
@Setter
@Getter
@ToString
public class User {
    private int id;
    private String username, password, gender, email, telephone;


}
