package com.balcon.mapper;

import com.balcon.model.ModbusDTO;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModbusResponseMapper {

    List<ModbusDTO> modbusDTOList = new ArrayList<>();

    public List<ModbusDTO> mapFrom (String data){
        if (null!=data) {
            JSONObject xmlJSONObj = XML.toJSONObject(data);
            String xmlJSONArray = (String) xmlJSONObj.get("string");
            String [] values = xmlJSONArray.split(",");
            modbusDTOList.clear();
            for(int i=0;i<values.length;i++){
                ModbusDTO modbusDTO = new ModbusDTO(String.valueOf(40001+i),values[i]);
                modbusDTOList.add(modbusDTO);
            }
        }
        return modbusDTOList;
    }

    public List<ModbusDTO> mapTo (){
        return modbusDTOList;
    }

}
