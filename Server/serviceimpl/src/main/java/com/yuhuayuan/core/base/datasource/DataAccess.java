package com.yuhuayuan.core.base.datasource;

import com.yuhuayuan.core.model.dao.versionMapper;
import com.yuhuayuan.core.model.entity.version;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.InputStream;

/**
 * Created by cl on 2017/2/23.
 */
public class DataAccess {

    @

    public static void main(int argc, String []argv)
    {
        new DataAccess().test();
    }

    static SqlSessionFactory sqlSessionFactory;
    public synchronized SqlSessionFactory getInstance()
    {
        try {
            if(null == sqlSessionFactory) {
                String resource = "com/yuhuayuan/core/model/persistmap/datasource/mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }catch (Exception e)
        {

        }

        return sqlSessionFactory;
    }


    public void test()
    {
        version blog;
        try {


            SqlSession session = sqlSessionFactory.openSession();
            try {

                versionMapper mapper = session.getMapper(versionMapper.class);
                long id = 1;
                blog = mapper.selectByPrimaryKey(id);

            } finally {
                session.close();
            }

        }catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }
}
