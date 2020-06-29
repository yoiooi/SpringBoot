package com.jojoldu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jojoldu.book.springboot.web.dto.HelloResponseDto;

/*
 * '@RestController' 은 해당 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
 */
@RestController
public class HelloController {
    // '@GetMapping' 은 Get 의 요청을 받을 수 있는 API로 만들어 줌
    // /hello로 요청이 오면 문자열 hello을 반환하는 기능을 가짐
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // '@RequestParam' 은 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        return new HelloResponseDto(name, amount);
    }
}
