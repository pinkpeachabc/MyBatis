package cn.pinkpeachabc.POJO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserParam {
    private String userName, userSex,userPhone,userPwd;
    private int id;
    private List<Integer> listId;


}
