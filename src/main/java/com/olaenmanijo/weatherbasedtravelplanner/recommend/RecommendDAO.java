package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendDAO {

	@Autowired
	SqlSession session;
	
	public List<GetLocationDTO> getLocation(Map<String,Object> parameters) {
		return session.selectList("RecommendMapper.getLocation",parameters);
	}
}
