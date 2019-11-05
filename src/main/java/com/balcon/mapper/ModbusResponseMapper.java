package com.balcon.mapper;

import com.balcon.model.ModbusDTO;
import org.springframework.stereotype.Component;

@Component
public class ModbusResponseMapper {

    ModbusDTO modbusDTO = new ModbusDTO();

    public ModbusDTO mapFrom (String data){
        if (null!=data) modbusDTO.setDatos(data);
        return modbusDTO;
    }

    public String mapTo (){
        return modbusDTO.getDatos() != null ? modbusDTO.getDatos() : "";
    }

}
