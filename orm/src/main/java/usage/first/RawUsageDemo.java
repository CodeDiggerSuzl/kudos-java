package usage.first;

import com.google.gson.Gson;
import dao.UserInfoDao;
import dos.UserInfoDo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis 原始用法
 */

public class RawUsageDemo {

    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws IOException {
        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产一个SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 4.使用SqlSession创建dao接口的代理对象
        UserInfoDao userInfoDao = sqlSession.getMapper(UserInfoDao.class);
        // 5.使用代理对象执行方法
        List<UserInfoDo> dbList = userInfoDao.findByAge(23);
        String s = GSON.toJson(dbList);
        System.out.println(s);
    }
}
