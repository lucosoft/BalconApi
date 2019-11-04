package com.balcon.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("CamelService")
public class CamelServiceImpl implements CamelService {
    public String echo() {
        return "balcon-api camel echo";
    }

    public HashMap getData() {
        return new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
    }
}
