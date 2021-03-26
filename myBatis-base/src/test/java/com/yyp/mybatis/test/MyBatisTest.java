package com.yyp.mybatis.test;

import com.yyp.mybatis.entity.Emp;
import com.yyp.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * myBatis 初识测试类
 *
 * MyBatis基础搭建流程
 * 1 pom文件引入mybatis核心jar包以及数据库对应的链接驱动
 * 2 创建数据库和表结构
 * 3 代码中创建相应的POJO实体类
 * 4 增加mybatis全局配置文件（可从官网上直接复制，基础版本），并修改对应的数据库连接信息
 * 5 创建POJOMapper配置文件（编写相应的sql的文件）
 *      namespace：如果是statementId方式namespace可以随意编写
 *                 如果是接口绑定方式namespace方式需要是接口的完全限定名
*       resultType：如果返回的是一个实体类需要是实体类的完全限定名（没有定义别名的情况下，后续会涉及）
 * 6 接口绑定形式，编写对应的接口
 *      注意：接口与mapper的xml文件必须在同一级目录下，所以在resource中创建相同的文件夹
 * 7 mybatis全局配置文件中配置对应的mapper映射
 */
public class MyBatisTest {

    SqlSessionFactory sessionFactory;

    /**
     * 读取配置文件创建sqlSession对象
     * 可以了解，因为与Spring整合之后此部分有spring容器替我们完成
     */
    @Before
    public void before(){
        String resourcePath="mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    /**
     * 使用sqlsession直接调用mapper文件中的设置（不会使用了解就好）即statementId方式
     * 需要在mybatis的映射文件中配置mapper
     * <mapper resource="com.yyp.mybatis.mapper.EmpMapper.xml"/>
     *
     */
    @Test
    public void test01(){
        try(SqlSession sqlSession = sessionFactory.openSession()){
            Object o = sqlSession.selectOne("com.yyp.mybatis.mapper.EmpMapper.selectEmp", 1);
            System.out.println(o);
        }
    }

    /**
     * 接口绑定方式
     * 实现步骤
     * 1：创建mapper对应的接口POJOMapper.java
     * 2：mapper中的id需要与接口中的方法名对应
     *    mapper中返回值类型需要接口中的方法返回值相同
     *    mapper中的namespace需要与接口的完全限定名相同
     * 3：修改mybatis中mapper的映射方式
     *      <mapper class="cn.tulingxueyuan.mapper.EmpMapper"></mapper>
 *     4：mapper需要与接口在同一个目录下边，在idea中resource中创建与接口相同的目录结构，在编译完成后会合并到同一个目录下
     */
    @Test
    public void test02(){
        try(SqlSession sqlSession = sessionFactory.openSession()){
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp(1);
            System.out.println(emp);
        }
    }

    /**
     * 使用注解的方式
     * @Select("select * from Emp where id = ${id}")
     * 可以与xml格式并存，但是推荐都是用xml格式即实现了解耦，也便于以后查询维护，有问题或者修改值看xml文件即可
     * 运用类注解的接口方法不能同时存在xml中有id与之对应
     */
    @Test
    public void test03(){
        try(SqlSession sqlSession = sessionFactory.openSession()){
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmpAnn(1);
            System.out.println(emp);
        }
    }
}
