package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProudConetentDAO {
	
	@Autowired
	SqlSession session;

	public List<GetRecommendDTO> community(){
		System.out.println(session.selectList("MainPageMapper.getRecommend"));
		return session.selectList("MainPageMapper.getRecommend");
	}
}
