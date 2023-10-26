package com.olaenmanijo.weatherbasedtravelplanner.domain.plan;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDAO {

	@Autowired
	SqlSessionTemplate session;  
	
	public int insertPlace(Item place) {
		return session.insert("PlaceMapper.insertPlace", place);
	}

	public List<Place> selectByNameAndCategory(Map<String, String> paramMap) {
		List<Place> list = session.selectList("PlaceMapper.selectByNameAndCategory", paramMap);
		return list;
	}
}
