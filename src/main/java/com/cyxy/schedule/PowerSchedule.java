package com.cyxy.schedule;

import com.cyxy.dao.PowerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
@EnableAsync
public class PowerSchedule {
    @Autowired
    private PowerDao powerDao;
    @Async
    @Scheduled(cron = "0 0/2 * * * *")
    public void updatePowerFull(){
        powerDao.updatePowerFullState();

        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("s","277DCV");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3829.0 Safari/537.36 Edg/77.0.197.1");
        headers.setBasicAuth("xxx","xxx");
        HttpEntity httpEntity = new HttpEntity(map,headers);
        String response = restTemplate.postForEntity("https://hpjav.tv/tw",httpEntity,String.class).getBody();
        System.out.println(response);



    }
}
