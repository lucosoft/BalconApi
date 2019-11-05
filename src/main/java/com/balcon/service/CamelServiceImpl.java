package com.balcon.service;

import com.balcon.mapper.ModbusResponseMapper;
import com.balcon.model.ModbusDTO;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("CamelService")
public class CamelServiceImpl implements CamelService {

    @Autowired
    private ModbusResponseMapper modbusResponseMapper;

    public String echo() {
        return "balcon-api camel echo";
    }

    public HashMap getData() {
        return new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
    }

    public String getModbusData() {
        return modbusResponseMapper.mapTo();
    }

    public void procModbusData(Exchange exchange) {
        String responseData = (String) exchange.getIn().getBody();
        modbusResponseMapper.mapFrom(responseData);
        System.out.println("procData: " + responseData
        );
    }
}
