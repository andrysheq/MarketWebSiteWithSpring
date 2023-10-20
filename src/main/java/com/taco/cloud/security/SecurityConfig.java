package com.taco.cloud.security;

import com.taco.cloud.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CloudUserService cloudUserService;

    public SecurityConfig(CloudUserService cloudUserService) {
        this.cloudUserService = cloudUserService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsServiceCreateList(PasswordEncoder encoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User(
//                "buzz", encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        usersList.add(new User(
//                "woody", encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(usersList);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepo) {
//        return username -> {
//            com.taco.cloud.models.User user = userRepo.findByUsername(username);
//            if (user != null) return user;
//            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
//        };
//    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(cloudUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .mvcMatchers("/design", "/orders").hasRole("USER")
                .requestMatchers(toH2Console()).permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin(form -> form.loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/design"))
                .logout(logout -> logout.logoutSuccessUrl("/login"))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console())
                        .disable())
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers(toH2Console()).permitAll())
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                .authorizeRequests()
//                .mvcMatchers("/design", "/orders").hasRole("USER")
//                 .requestMatchers(toH2Console()).permitAll()
//                .anyRequest().permitAll()
//                 .and()
//                .formLogin(form -> form.loginPage("/login")
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/design"))
//                .logout(logout -> logout.logoutSuccessUrl("/login"))
//                 .csrf(csrf -> csrf
//                         .ignoringRequestMatchers(toH2Console())
//                         .disable())
////                 .authorizeHttpRequests(auth -> auth
////                         .requestMatchers(toH2Console()).permitAll())
//                 .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
//
//         return http.build();
//    }
}
