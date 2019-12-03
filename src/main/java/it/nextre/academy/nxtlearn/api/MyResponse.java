package it.nextre.academy.nxtlearn.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MyResponse{
    private HttpStatus statusCode;
    private Map<String,Object> data = new HashMap<>();
    private MultiValueMap<String, String> customHeaders = new HttpHeaders();
    MyResponse(HttpStatus statusCode, Object body){
        this.statusCode=statusCode;
        this.data.put("data",body);
        this.data.put("status",statusCode.value());
        // this.data.put("error",statusCode.toString().substring(4).trim());
        this.data.put("timestamp", Instant.now().toEpochMilli());
    }
    public ResponseEntity getPage(){
        return new ResponseEntity(data,statusCode);
    };

    public MyResponse addData(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public MyResponse addHeader(String key, String value){
        this.customHeaders.put(key, Arrays.asList(value));
        return this;
    }
    public MyResponse addHeaders(String key, List<String> value){
        this.customHeaders.put(key, value);
        return this;
    }
}
