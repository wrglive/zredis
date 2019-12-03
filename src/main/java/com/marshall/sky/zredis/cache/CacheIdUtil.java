package com.marshall.sky.zredis.cache;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

public class CacheIdUtil {
    private CacheIdUtil(){}

    protected static String buildKeyId(Object[] parameterValues, String[] parameterNames, String[] spElKeyArray) {
        if (spElKeyArray == null || spElKeyArray.length == 0) {
            buildDefaultKeyId(parameterValues);
        }
        List<Object> idList = Lists.newArrayList();
        SpelExpressionParser parser = new SpelExpressionParser();
        EvaluationContext evaluationContext = new StandardEvaluationContext();

        for (int i = 0; i < parameterValues.length; i++) {
            evaluationContext.setVariable(parameterNames[i], parameterValues[i]);
        }

        for (int i = 0; i < spElKeyArray.length; i++) {
            String spEl = spElKeyArray[i];
            SpelExpression spelExpression = parser.parseRaw(spEl);
            Object value = spelExpression.getValue(evaluationContext);

            idList.add(value);
        }
        return JSON.toJSONString(idList);
    }

    protected static String buildDefaultKeyId(Object[] parameterValues) {
        List<Object> idList = Lists.newArrayList();
        for (Object parameterValue : parameterValues) {
            idList.add(parameterValue);
        }
        return JSON.toJSONString(idList);
    }

}
