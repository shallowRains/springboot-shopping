package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.dao.Class1Mapper;
import com.springcloud.dao.Class2Mapper;
import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	private Class1Mapper class1Mapper;

	@Autowired
	private Class2Mapper class2Mapper;

	@Override
	public List<Class1> selectAllTypeOne() {
		return this.class1Mapper.selectAll();
	}

	@Override
	public List<Class2> selectTypeTwoByTypeOneId(Integer typeOneId) {
		return this.class2Mapper.selectByClass1Id(typeOneId);
	}

}
