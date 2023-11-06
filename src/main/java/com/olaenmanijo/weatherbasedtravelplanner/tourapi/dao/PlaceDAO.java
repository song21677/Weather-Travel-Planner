package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;

@Repository
public class PlaceDAO {

	@Autowired
	SqlSessionTemplate session;  
	
	public int insertPlace(Place place) {
		return session.insert("PlaceMapper.insertPlace", place);
	}

	public List<Place> selectByNameAndCategory(Map<String, String> paramMap) {
		List<Place> list = session.selectList("PlaceMapper.selectByNameAndCategory", paramMap);
		return list;
	}
}
