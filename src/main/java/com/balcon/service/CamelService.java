package com.balcon.service;

import org.apache.camel.Exchange;

import java.util.HashMap;

public interface CamelService {
    public String echo();
    public HashMap getData();
    public String getModbusData();
    public void procModbusData(Exchange exchange);
}
