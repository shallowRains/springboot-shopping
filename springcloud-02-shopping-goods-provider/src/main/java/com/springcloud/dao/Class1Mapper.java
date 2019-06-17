package com.springcloud.dao;

import com.springcloud.entity.Class1;
import java.util.List;

public interface Class1Mapper {
    int deleteByPrimaryKey(Integer class1Id);

    int insert(Class1 record);

    Class1 selectByPrimaryKey(Integer class1Id);

    List<Class1> selectAll();

    int updateByPrimaryKey(Class1 record);
}