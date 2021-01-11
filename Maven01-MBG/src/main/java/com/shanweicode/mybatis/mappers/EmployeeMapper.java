package com.shanweicode.mybatis.mappers;

import com.shanweicode.mybatis.bean.Employee;
import com.shanweicode.mybatis.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package com.shanweicode.mybatis.mappers
 * @Author shanweih
 * @Date 2020/12/21 18:27
 * @TOOL IntelliJ IDEA
 */
public interface EmployeeMapper {
    int countExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert (Employee record);

    int insertSelective(Employee record);

    List<Employee>selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example")EmployeeExample example);

    int updateByExample(@Param("record")Employee record,@Param("example")EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updatePrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);



}
