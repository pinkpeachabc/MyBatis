package cn.pinkpeachabc.Utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *连接工具类,封装SQLSession的创建.
 */
public class MyBatisUtils {
    private static SqlSessionFactory factory;

    static {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-configuration.xml");
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}
