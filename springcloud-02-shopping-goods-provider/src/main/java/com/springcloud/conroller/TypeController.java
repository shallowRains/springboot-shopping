package com.springcloud.conroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.TypeService;
import com.springcloud.vo.ResultValue;

/**
 * 一级类别与二级类别类别控制器
 * @author RLDS
 *
 */
@RestController
@RequestMapping("type")
public class TypeController {
	@Autowired 
	private TypeService typeService;
	
	@RequestMapping(value = "/selectAll")
	public ResultValue selectAllTypeOne() {
		ResultValue rv = new ResultValue();
		try {
			
			List<Class1> selectAllTypeOne = this.typeService.selectAllTypeOne();
			if(selectAllTypeOne != null && selectAllTypeOne.size()>0) {
				Map<String,Object> hashMap = new HashMap<>();
				hashMap.put("TypeOneList", selectAllTypeOne);
				rv.setCode(0);
				rv.setDataMap(hashMap);
				return rv;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(0);
		rv.setMessage("查询失败");
		return rv;
	}
	@RequestMapping(value="/selectById")
	public ResultValue selectById(@RequestParam("typeOneId") Integer typeOneId) {
		ResultValue rv = new ResultValue();
		try {
			
			List<Class2> list = this.typeService.selectTypeTwoByTypeOneId(typeOneId);
			if(list != null && list.size()>0) {
				Map<String,Object> hashMap = new HashMap<>();
				hashMap.put("TypeTwoList", list);
				rv.setCode(0);
				rv.setDataMap(hashMap);
				return rv;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(0);
		rv.setMessage("查询失败");
		return rv;
	}
}
