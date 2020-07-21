package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // JPA Auditing 활성화 - WebMvcTest를 위해 제거
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기, 생성이 자동으로 설정, 이 위치부터 설정을 읽어가므로 항상 프로젝트 최상단에 위치해야 함
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*
window port 확인 및 죽이기
netstat
: 실행중인 port 찾기
*
netstat -a -o
: 실행중인 port 표시, 프로세스id(pid) 표시

taskkill /f /pid 1234
: 1234 프로세스id(pid) kill하기
*/