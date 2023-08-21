//package com.pangan.animedb.auth.dao;
//
//import com.pangan.animedb.auth.dto.UserAuthRequestDto;
//import com.pangan.animedb.auth.dto.UserAuthResponseDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/auth")
//public class UserAuthController {
//
//    private final UserAuthService userAuthService;
//
//    @Autowired
//    public UserAuthController(UserAuthService userAuthService) {
//        this.userAuthService = userAuthService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<UserAuth> register(@RequestBody UserAuthRequestDto userAuthRequestDto) {
//        return ResponseEntity.ok(userAuthService.register(userAuthRequestDto));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserAuthResponseDto> login(@RequestBody UserAuthRequestDto userAuthRequestDto) {
//        return ResponseEntity.ok(userAuthService.login(userAuthRequestDto));
//    }
//}
