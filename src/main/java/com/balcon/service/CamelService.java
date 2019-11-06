package com.balcon.service;

import com.balcon.model.ModbusDTO;
import org.apache.camel.Exchange;

import java.util.HashMap;
import java.util.List;

public interface CamelService {
    public String echo();
    public HashMap getData();
    public List<ModbusDTO> getModbusData();
    public List<ModbusDTO> procModbusData(Exchange exchange);
}
