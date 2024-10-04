//package com.example.springdonateweb.Config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
////@Service
////@RequiredArgsConstructor
////@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
////    public class CustomUserDetailService implements UserDetailsService {
////
////        PersonRepository personRepository;
////
////        @Override
////        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////            PersonEntity user = personRepository.findByIdAndStatusTrue(Long.parseLong(username))
////                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
////            return new org.springframework.security.core.userdetails.User(
////                    user.getUsername(),
////                    user.getPassword(),
////                    List.of(new SimpleGrantedAuthority(user.getRole().getValue()))
////            );
////        }
////    }
//}
