/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.components.JwtService;
import com.dnt.pojo.TaiKhoan;
import com.dnt.services.TaiKhoanService;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
public class ApiTaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    
    @PostMapping(path = "/taikhoan/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<TaiKhoan> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file
            , HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaiKhoan tk = new TaiKhoan();
        tk.setUsername(params.get("username"));
        tk.setPassword(this.passwordEncoder.encode(params.get("password")));
        tk.setVaiTro(params.get("vaitro"));
        if (file.length > 0)
            tk.setFile(file[0]);
        
        this.taiKhoanService.addTaiKhoan(tk);
        TaiKhoan createdTaiKhoan = this.taiKhoanService.getTaiKhoanById(tk.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaiKhoan);
    }
    
    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody TaiKhoan tk) {
        if (this.taiKhoanService.authTaiKhoan(tk.getUsername(), tk.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(tk.getUsername());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/current-taikhoan/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<TaiKhoan> details(Principal user) {
        TaiKhoan tk = this.taiKhoanService.getTaiKhoanByUsername(user.getName());
        return new ResponseEntity<>(tk, HttpStatus.OK);
    }
    
}

