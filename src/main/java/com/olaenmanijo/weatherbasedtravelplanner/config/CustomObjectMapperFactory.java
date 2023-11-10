package com.olaenmanijo.weatherbasedtravelplanner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.olaenmanijo.weatherbasedtravelplanner.plan.Plan;
import com.fasterxml.jackson.core.JsonParser;

public class CustomObjectMapperFactory {
    public static ObjectMapper createCustomObjectMapper() {
        // ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // SerializationFeature.FAIL_ON_SELF_REFERENCES를 false로 설정
        objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
        //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
     // 믹스인 클래스를 등록
        //objectMapper.addMixIn(Plan.class, PlanMixin.class);
        return objectMapper;
    }
}

