package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AgeDAO {
	
	@Autowired
	SqlSession session;
	
	public List<GetAgeDTO> getAge(Map<String, Integer> map) {
		return session.selectList("RecommendMapper.getAge",map);
	}

}
