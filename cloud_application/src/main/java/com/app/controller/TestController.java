package com.app.controller;


import com.app.entity.ShipDataNew;
import com.app.entity.ShipData;
import com.app.repository.ShipDataNewRepository;
import com.app.repository.ShipDataRepository;
import com.app.service.impl.ShipDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ShipDataService shipDataService;

    @Autowired
    private ShipDataRepository shipDataRepository;

    @Autowired
    private ShipDataNewRepository shipDataNewRepository;

    @RequestMapping("/data")
    public String getData() {
        double speed= 2.5;
        List<String> shipName = shipDataService.getShipName();
        shipName.forEach(name->{
            int flag = 0;
            List<ShipDataNew> newList = new ArrayList<>();
            List<ShipData> byShipName = shipDataRepository.findByShipName(name);
            ShipData shipData = byShipName.get(0);
            double x = Double.parseDouble(shipData.getxAxis());
            double y = Double.parseDouble(shipData.getyAxis());
            String time = shipData.getTime().split(" ")[1];
            String orgTime= shipData.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            for (int i = 1; i < byShipName.size()-1; i++) {
                ShipData shipData1 = byShipName.get(i);
                double x1 = Double.parseDouble(shipData1.getxAxis());
                double y1 = Double.parseDouble(shipData1.getyAxis());
                String time1 = shipData1.getTime().split(" ")[1];
                String newTime= shipData1.getTime();
                try {
                    long seconds = (sdf.parse(time1).getTime() - sdf.parse(time).getTime())/1000;
                    double sqrt = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(y1-y, 2));
                    if ( seconds*speed > sqrt){
                        x = (x+x1)/2;
                        y = (y+y1)/2;
                        orgTime = newTime;
                    }else{
                        ShipDataNew shipDataNew1 = new ShipDataNew();
                        shipDataNew1.setShipName(name);
                        shipDataNew1.setxAxis(x1+"");
                        shipDataNew1.setyAxis(y1+"");
                        shipDataNew1.setTime(newTime);
                        newList.add(shipDataNew1);
                        shipDataNew1 =null;
                        x = x1;
                        y = y1;
                        time = time1;
                        flag+=1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (newList.size()>0){
                shipDataNewRepository.save(newList);
            }
            if (flag == 0 ){
                ShipDataNew shipDataNew = new ShipDataNew();
                shipDataNew.setTime(orgTime);
                shipDataNew.setxAxis(x+"");
                shipDataNew.setyAxis(y+"");
                shipDataNew.setShipName(name);
                shipDataNewRepository.save(shipDataNew);
            }
            newList.clear();
        });
        System.out.println("success");
        return "";
    }

}
