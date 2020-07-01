package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기, 생성이 자동으로 설정, 이 위치부터 설정을 읽어가므로 항상 프로젝트 최상단에 위치해야 함
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
