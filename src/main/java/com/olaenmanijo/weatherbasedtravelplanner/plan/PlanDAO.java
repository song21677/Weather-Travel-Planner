package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlanDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	public int insertPlan(Plan plan) {
		return session.insert("PlanMapper.insertPlan", plan);
	}
	
	public int insertDetailPlan(PlanDTO2 planDTO2) {
		return session.insert("InsertDetailPlanMapper.insertDetailPlan", planDTO2);
	}
}