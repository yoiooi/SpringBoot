package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomsOAuth2UserService customsOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 disable
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 권한 관리 대상을 지정하는 옵션, 지정된 URL들은 전체 열람 권한을 줌
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 해당 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 함
                    .anyRequest().authenticated() // 지정하지 않은 나머지 URL로 인증된 사용자만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth 2 로그인 성공 후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customsOAuth2UserService); // 소셜 로그인 성공 시 UserService 인터페이스의 구현체를 등록
    }
}
