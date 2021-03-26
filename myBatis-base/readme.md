myBatis 初识模块
    一 目的：了解myBatis的基本配置以及简单的增删改查（CRUD）操作
        ，运行一个最简单的CRU的操作，具体的各个配置文件的配置方法将在后续模块进行演示学习。
    二 配置一个基础的myBatis框架需要的要素
         1 pom文件引入mybatis核心jar包以及数据库对应的链接驱动
         2 创建数据库和表结构
         3 代码中创建相应的POJO实体类
         4 增加mybatis全局配置文件（可从官网上直接复制，基础版本），并修改对应的数据库连接信息
         5 创建POJOMapper配置文件（编写相应的sql的文件）
               namespace：如果是statementId方式namespace可以随意编写
                          如果是接口绑定方式namespace方式需要是接口的完全限定名
               resultType：如果返回的是一个实体类需要是实体类的完全限定名（没有定义别名的情况下，后续会涉及）
         6 接口绑定形式，编写对应的接口
               注意：接口与mapper的xml文件必须在同一级目录下，所以在resource中创建相同的文件夹
         7 mybatis全局配置文件中配置对应的mapper映射
    三 接口绑定方式：
         1：创建mapper对应的接口POJOMapper.java
         2：mapper中的id需要与接口中的方法名对应
             mapper中返回值类型需要接口中的方法返回值相同
             mapper中的namespace需要与接口的完全限定名相同
         3：修改mybatis中mapper的映射方式
             <mapper class="cn.tulingxueyuan.mapper.EmpMapper"></mapper>
         4：mapper需要与接口在同一个目录下边，在idea中resource中创建与接口相同的目录结构，在编译完成后会合并到同一个目录下
    四 官方中文文档：（如果链接失效，可以在GitHub上搜索myBatis） 