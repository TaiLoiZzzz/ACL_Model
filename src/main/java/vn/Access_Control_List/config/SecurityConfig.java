package vn.Access_Control_List.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authz -> authz
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        
                        // User management endpoints
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("READ_USERS", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasAnyAuthority("CREATE_USERS", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyAuthority("UPDATE_USERS", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("DELETE_USERS", "ROLE_ADMIN")
                        
                        // Role management endpoints
                        .requestMatchers(HttpMethod.GET, "/api/roles/**").hasAnyAuthority("READ_ROLES", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/roles").hasAnyAuthority("CREATE_ROLES", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/roles/**").hasAnyAuthority("UPDATE_ROLES", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/roles/**").hasAnyAuthority("DELETE_ROLES", "ROLE_ADMIN")
                        
                        // Permission management endpoints
                        .requestMatchers(HttpMethod.GET, "/api/permissions/**").hasAnyAuthority("CREATE_PERMISSIONS", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/permissions").hasAnyAuthority("CREATE_PERMISSIONS", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/permissions/**").hasAnyAuthority("UPDATE_PERMISSIONS", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/permissions/**").hasAnyAuthority("DELETE_PERMISSIONS", "ROLE_ADMIN")
                        
                        // Profile endpoints - any authenticated user
                        .requestMatchers("/api/profile/**").authenticated()
                        
                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //CORS là cơ chế cho phép frontend ở domain khác gọi API backend.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        //Tạo mới đối tượng cấu hình CORS.
        CorsConfiguration configuration = new CorsConfiguration();

        //Cho phép tất cả các origin (domain) gọi đến.
        configuration.setAllowedOriginPatterns(List.of("*"));

        //Chỉ định những phương thức HTTP được phép gọi từ frontend.
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        //Cho phép mọi header trong request gửi lên (ví dụ: Authorization, Content-Type...)
        configuration.setAllowedHeaders(List.of("*"));

        //Cho phép gửi cookie, token (khi dùng withCredentials ở frontend)
        configuration.setAllowCredentials(true);

        //Gán cấu hình CORS ở trên cho toàn bộ các endpoint (/**).
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Đây là một phương thức trả về AuthenticationProvider, dùng để xác thực người dùng trong Spring Security.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        //Khi xác thực người dùng, hãy dùng BCryptPasswordEncoder để kiểm tra mật khẩu
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 