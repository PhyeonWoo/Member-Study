package com.example.MemberStudy.controller;


import com.example.MemberStudy.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get") // RequestMapping URI를 지정해주는 Annotation
public class GetApiController {

    @GetMapping(path = "/hello")  // 명확하게 경로를 지정해주는 방식
    public String getHello() {
        return "API hello";
    }


    // name의 이름이 계속해서 바뀔때는 @PathVariable을 하고 String name을 적으면 된다
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable String name) {
        System.out.println("name = " + name);
        return name;
    }



    // QueryParam일 경우에 이 방식을 사용하면 된다
    // http://localhost:9090/api/get/query-param?q=sddd -> key,value를 매치 시켜주기 위해서는 map 형태로 하는게 좋다
    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+"="+entry.getValue()+"\n");
        });
        return sb.toString();
    }



    // Query-Param이 많아지면 불편하기 때문에 이를 DTO형태로 매핑할 수 있게 만들어놨다
    @GetMapping(path = "/query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int phone
     )
    {
        System.out.println(name);
        System.out.println(email);
        System.out.println(phone);

        return name+"\n"+email+"\n"+phone;
    }


    // Dto를 이용해서 객체로 받는 것을 표현한 것
    // Dto는 객체로 저장하기 위해서 정의하는 분
    @GetMapping(path = "/query-param3")
    public String queryParam3(UserRequest userRequest)
    {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getPhone());

        return userRequest.toString();
    }
}
