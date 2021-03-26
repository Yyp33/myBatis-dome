package com.yyp.mybatis.mapper;

import com.yyp.mybatis.entity.Emp;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface EmpMapper {

    /**
     * 查询员工
     * @param id
     * @return
     */
    public Emp selectEmp(Integer id);


    /*
        查询员工注解方式
        可以mapper xml文件格式并存
     */
    @Select("select * from Emp where id = ${id}")
    public Emp selectEmpAnn(Integer id);
}
