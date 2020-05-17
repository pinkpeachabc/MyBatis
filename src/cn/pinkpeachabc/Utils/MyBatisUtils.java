package cn.pinkpeachabc.Utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *���ӹ�����,��װSQLSession�Ĵ���.
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
