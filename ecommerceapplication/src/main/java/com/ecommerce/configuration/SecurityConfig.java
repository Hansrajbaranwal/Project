//package com.ecommerce.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/","/shop/**","/register").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .failureUrl("/login?error= true")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and()
//                .oauth2Login()
//                .loginPage("/login")
//                .successHandler(googleOAuth2SuccessHandler)
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/"))
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .exceptionHandling()
//                .and()
//                .csrf()
//                .disable()
//
//        http.headers().frameOptions().disable();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(customUserDetailService);
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/resources/**","/static/**","/images/**","/productImages/**","/css/**","/js/**");
//    }
//
//}
package com.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.oauth2.success.OAuth2LoginAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebSecurityConfigurer<SecurityFilterChain> {

    @Autowired
    private OAuth2LoginAuthenticationSuccessHandler googleOAuth2SuccessHandler;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/", "/shop/**", "/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .oauth2Login(OAuth2LoginConfigurer::loginPage, login -> login
                        .loginPage("/login")
                        .successHandler(googleOAuth2SuccessHandler)
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.build();
    }
}
