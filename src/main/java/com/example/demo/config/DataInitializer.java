//package com.example.demo.config;
//
//import java.util.Set;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.example.demo.model.entity.Role;
//import com.example.demo.model.entity.User;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UserRepository;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
//        return args -> {
//            if (userRepository.findByUsername("admin").isEmpty()) {
//                Role adminRole = new Role();
//                adminRole.setName("ADMIN");
//                roleRepository.save(adminRole);
//
//                User user = new User();
//                user.setUsername("admin");
//                user.setPassword(new BCryptPasswordEncoder().encode("1234")); // ⚠️ BCrypt 加密
//                user.setName("Admin");
//                user.setActive(true);
//                user.setEmail("admin@example.com");
//                user.setGender("M");
//                user.setBirthdate(java.time.LocalDate.of(1990, 1, 1));
//                user.setRoles(Set.of(adminRole));
//
//                userRepository.save(user);
//                System.out.println("✅ 預設 admin 使用者建立完成");
//            }
//        };
//    }
//}
