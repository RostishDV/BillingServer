package org.novonet.billing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();

        http.csrf().disable();
    }


//    private void loginSuccessHandler(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication) throws IOException {
//
//        response.setStatus(HttpStatus.OK.value());
//        objectMapper.writeValue(response.getWriter(), "Yayy you logged in!");
//    }
//
//    private void loginFailureHandler(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException e) throws IOException {
//
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        objectMapper.writeValue(response.getWriter(), "Nopity nop!");
//    }
//
//    private void logoutSuccessHandler(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication) throws IOException {
//
//        response.setStatus(HttpStatus.OK.value());
//        objectMapper.writeValue(response.getWriter(), "Bye!");
//    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}