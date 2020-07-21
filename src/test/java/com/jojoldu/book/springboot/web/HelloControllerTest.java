package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 SpringRunner라는 스프링 실행자를 사용
@WebMvcTest(controllers = HelloController.class,
                excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
                }) // Web(Spring MVC)에 집중할 수 있는 어노테이션, 여기선 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈(Bean)을 주입받음
    private MockMvc mvc; // 스프링 MVC 테스트의 시작점, 웹 API를 테스트할 때 사용

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 HTTP GET 요청을 함 ex)localhost:8080/hello
                .andExpect(status().isOk()) // HTTP Header의 Status를 확인, 여기선 200인지 확인
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증, 여기선 "hello"를 리턴하는지 확인
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 100;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
