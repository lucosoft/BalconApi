package com.balcon.service;

import com.balcon.mapper.ModbusResponseMapper;
import com.balcon.model.ModbusDTO;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

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

    public List<ModbusDTO> getModbusData() {
        return modbusResponseMapper.mapTo();
    }

    public List<ModbusDTO> procModbusData(Exchange exchange) {
        String responseData = (String) exchange.getIn().getBody();
        System.out.println("procData: " + responseData);
        return modbusResponseMapper.mapFrom(responseData);
    }
}
