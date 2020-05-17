package cn.pinkpeachabc.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.pinkpeachabc.POJO.User;
import cn.pinkpeachabc.POJO.UserParam;
import cn.pinkpeachabc.Utils.MyBatisUtils;

/**
 * 测试
 */
public class TestMyBatis {
    /**
     * 通过id查询
     *
     * @throws Exception
     */
    @Test
    public void findUserByUid() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        User user = sqlSession.selectOne("cn.pinkpeachabc.Mapper.UserMapper.findUserByUid", 1);
        System.out.println(user);
        sqlSession.close();

    }

    /**
     * 通过username查询
     *
     * @throws Exception
     */
    @Test
    public void findUserByname() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        List<User> listUser = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByname", "1");
        for (User user1 : listUser) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    /**
     * 通过姓名和性别查询（通过javabean方法）
     *
     * @throws Exception
     */
    @Test
    public void findUserByJavaBean() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam param = new UserParam();
        param.setUserName("1");
        param.setUserSex("男");
        List<User> listUser = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByJavaBean", param);
        for (User user2 : listUser) {
            System.out.println(user2);
        }
        sqlSession.close();
    }

    /**
     * 通过姓名和性别查询（hashMap方法）
     *
     * @throws Exception
     */
    @Test
    public void findUserByMap() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uname", "1");
        hashMap.put("usex", "男");
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByMap", hashMap);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 想数据库user中添加信息
     *
     * @throws Exception
     */
    @Test
    public void addUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        User user = new User();
        user.setUsername("张三1");
        user.setPassword("123abc");
        user.setGender("男");
        user.setEmail("123456@qq.com");
        user.setTelephone("123456");
        int insert = sqlSession.insert("cn.pinkpeachabc.Mapper.UserMapper.addUser", user);
        System.out.println("插入" + (insert > 0 ? "成功" : "失败"));
        sqlSession.commit();//若不提交不会插入成功
        System.out.println("user的Id为" + user.getId());
        sqlSession.close();
    }

    /**
     * 修改数据库中的数据
     *
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pwd", "123abc");
        hashMap.put("sex", "女");
        hashMap.put("id", "11");
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.updateUser", hashMap);
        System.out.println("修改" + (update > 0 ? "成功" : "失败"));
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 删除数据库中的数据
     *
     * @throws Exception
     */
    @Test
    public void deleteUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "8");
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.deleteUser", hashMap);
        System.out.println("删除" + (update > 0 ? "成功" : "失败"));
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 动态sql查询Where
     *
     * @throws Exception
     */
    @Test
    public void findUserByWhere() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam userParam = new UserParam();
        userParam.setUserName("admin");
        // userParam.setUserPhone();
        List<User> list = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByWhere", userParam);
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();

    }

    /**
     * 动态sql查询-Trim标签
     *
     * @throws Exception
     */
    @Test
    public void findUserByTrim() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam userParam = new UserParam();
        userParam.setUserName("admin");
        // userParam.setUserPhone();
        List<User> list = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByTrim", userParam);
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();

    }

    /**
     * 动态sql查询-Trim标签
     *
     * @throws Exception
     */
    @Test
    public void updateUserTrim() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam param = new UserParam();
        param.setUserPwd("123456789");
        param.setUserSex("男");
        param.setId(6);
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.updateUserTrim", param);
        System.out.println("Trim更新" + (update > 0 ? "成功" : "失败"));
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * set标签
     *
     * @throws Exception
     */
    @Test
    public void updateUserSet() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam param = new UserParam();
        param.setUserSex("女");
        param.setId(6);
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.updateUserSet", param);
        System.out.println("Trim更新" + (update > 0 ? "成功" : "失败"));
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 动态sql删除-Trim标签
     *
     * @throws Exception
     */
    @Test
    public void deleteUserTrim() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "7");
        int delete = sqlSession.delete("cn.pinkpeachabc.Mapper.UserMapper.deleteUserTrim", hashMap);
        System.out.println("Trim删除" + (delete > 0 ? "成功" : "失败"));
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * Bind标签自动拼接字符串
     *
     * @throws Exception
     */
    @Test
    public void findUserByUnameBind() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam userParam = new UserParam();
        userParam.setUserName("1");
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByUnameBind", userParam);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * Foreach标签-传递集合
     *
     * @throws Exception
     */
    @Test
    public void findUserByIdForeachList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        List<Integer> listId = new ArrayList<>();
        listId.add(1);
        listId.add(2);
        listId.add(3);
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByIdForeachList", listId);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * foreach标签-传递数组
     *
     * @throws Exception
     */
    @Test
    public void findUserByIdForeachMap() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sex", "女");
        int[] ids = new int[]{1, 2, 3};
        hashMap.put("uids", ids);
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByIdForeachMap", hashMap);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * foreach标签-JavaBean传递多个数据
     *
     * @throws Exception
     */
    @Test
    public void findUserByIdForeachBean() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam userParam = new UserParam();
        userParam.setUserSex("男");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        userParam.setListId(list);
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByIdForeachBean", userParam);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }


}