package team1.quick_quize.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class QuizeAuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz

            .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
            .authenticated())// 上記以外は全員アクセス可能
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))// h2-console用にCSRF対策を無効化
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$10$HPx9ln1zUc/ZZ9PriSvAsePCTK45YskMTz6vdzcdQGxvmuod/ZDNy").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$10$0HVuCn2naFjtcTZ4lnkwxu9yJHzpzRKfQxPihH43K85F8r.J5eyj.").roles("USER").build();
    UserDetails user3 = User.withUsername("user3")
        .password("{bcrypt}$2y$10$5AOEQofwMt08KcY2MEpx7uj6sWMuLPSb02vqWufEEeZHh8suCP7Wm")
        .roles("USER")
        .build();
    UserDetails user4 = User.withUsername("user4")
        .password("{bcrypt}$2y$10$WLsQaZTtZhFag3yAEPCvvO/Mp3wAiFl3Fh6AAXKYNGdrjojnxyHH2")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user1, user2, user3, user4);
  }

}
