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
 * ����
 */
public class TestMyBatis {
    /**
     * ͨ��id��ѯ
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
     * ͨ��username��ѯ
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
     * ͨ���������Ա��ѯ��ͨ��javabean������
     *
     * @throws Exception
     */
    @Test
    public void findUserByJavaBean() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam param = new UserParam();
        param.setUserName("1");
        param.setUserSex("��");
        List<User> listUser = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByJavaBean", param);
        for (User user2 : listUser) {
            System.out.println(user2);
        }
        sqlSession.close();
    }

    /**
     * ͨ���������Ա��ѯ��hashMap������
     *
     * @throws Exception
     */
    @Test
    public void findUserByMap() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uname", "1");
        hashMap.put("usex", "��");
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByMap", hashMap);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * �����ݿ�user�������Ϣ
     *
     * @throws Exception
     */
    @Test
    public void addUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        User user = new User();
        user.setUsername("����1");
        user.setPassword("123abc");
        user.setGender("��");
        user.setEmail("123456@qq.com");
        user.setTelephone("123456");
        int insert = sqlSession.insert("cn.pinkpeachabc.Mapper.UserMapper.addUser", user);
        System.out.println("����" + (insert > 0 ? "�ɹ�" : "ʧ��"));
        sqlSession.commit();//�����ύ�������ɹ�
        System.out.println("user��IdΪ" + user.getId());
        sqlSession.close();
    }

    /**
     * �޸����ݿ��е�����
     *
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pwd", "123abc");
        hashMap.put("sex", "Ů");
        hashMap.put("id", "11");
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.updateUser", hashMap);
        System.out.println("�޸�" + (update > 0 ? "�ɹ�" : "ʧ��"));
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * ɾ�����ݿ��е�����
     *
     * @throws Exception
     */
    @Test
    public void deleteUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "8");
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.deleteUser", hashMap);
        System.out.println("ɾ��" + (update > 0 ? "�ɹ�" : "ʧ��"));
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * ��̬sql��ѯWhere
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
     * ��̬sql��ѯ-Trim��ǩ
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
     * ��̬sql��ѯ-Trim��ǩ
     *
     * @throws Exception
     */
    @Test
    public void updateUserTrim() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam param = new UserParam();
        param.setUserPwd("123456789");
        param.setUserSex("��");
        param.setId(6);
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.updateUserTrim", param);
        System.out.println("Trim����" + (update > 0 ? "�ɹ�" : "ʧ��"));
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * set��ǩ
     *
     * @throws Exception
     */
    @Test
    public void updateUserSet() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam param = new UserParam();
        param.setUserSex("Ů");
        param.setId(6);
        int update = sqlSession.update("cn.pinkpeachabc.Mapper.UserMapper.updateUserSet", param);
        System.out.println("Trim����" + (update > 0 ? "�ɹ�" : "ʧ��"));
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * ��̬sqlɾ��-Trim��ǩ
     *
     * @throws Exception
     */
    @Test
    public void deleteUserTrim() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "7");
        int delete = sqlSession.delete("cn.pinkpeachabc.Mapper.UserMapper.deleteUserTrim", hashMap);
        System.out.println("Trimɾ��" + (delete > 0 ? "�ɹ�" : "ʧ��"));
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * Bind��ǩ�Զ�ƴ���ַ���
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
     * Foreach��ǩ-���ݼ���
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
     * foreach��ǩ-��������
     *
     * @throws Exception
     */
    @Test
    public void findUserByIdForeachMap() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sex", "Ů");
        int[] ids = new int[]{1, 2, 3};
        hashMap.put("uids", ids);
        List<User> userList = sqlSession.selectList("cn.pinkpeachabc.Mapper.UserMapper.findUserByIdForeachMap", hashMap);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * foreach��ǩ-JavaBean���ݶ������
     *
     * @throws Exception
     */
    @Test
    public void findUserByIdForeachBean() throws Exception {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserParam userParam = new UserParam();
        userParam.setUserSex("��");
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