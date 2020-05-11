package com.woniuxy.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FastJsonJavaConfig {
    @Bean
    public HttpMessageConverters converter(){
        FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
        FastJsonConfig jsonConfig=new FastJsonConfig();
        //设置日期格式
        jsonConfig.setDateFormat("yyyy-MM-dd");
        //设置编码方式
        jsonConfig.setCharset(Charset.forName("UTF-8"));
         /*
            是否在生成的Json中输出类名
            是否输出value为null的数据
            生成的json格式化
            空集合输出[]而非null
            空字符串输出“”而非null等配置
         */
        jsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect);//禁掉循环的关联
        converter.setFastJsonConfig(jsonConfig);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);
        HttpMessageConverters converters=new HttpMessageConverters(converter);
        return converters;
    }
}
