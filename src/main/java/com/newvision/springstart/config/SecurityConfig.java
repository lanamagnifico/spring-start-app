package com.newvision.springstart.config;

import com.newvision.springstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers("/css/**","/js/**","/resources/**").permitAll()
                    .antMatchers("/console/**").hasRole("ADMIN")
                    .antMatchers("/api/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/showCustomLoginPage")
                    .loginProcessingUrl("/authenticate")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(myFailureHandler)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint);

        // for H2 console
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); 
        auth.setPasswordEncoder(customPasswordEncoder()); 
        return auth;
    }
    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        };
    }
}
