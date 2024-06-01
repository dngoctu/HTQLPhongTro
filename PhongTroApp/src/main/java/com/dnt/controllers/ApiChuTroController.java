/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.ChuTro;
import com.dnt.services.ChuTroService;
import com.dnt.services.TaiKhoanService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiChuTroController {

    @Autowired
    private ChuTroService chuTroService;
    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/chutro/")
    @CrossOrigin
    public ResponseEntity<List<ChuTro>> list(@RequestParam Map params) {
        return new ResponseEntity<>(this.chuTroService.getChuTro(params),
                HttpStatus.OK);
    }

    @PostMapping(path = "/chutro/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<ChuTro> create(@RequestParam Map<String, String> params,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChuTro c = new ChuTro();
        c.setTen(params.get("ten"));
        c.setHo(params.get("ho"));
        c.setSdt(params.get("sdt"));
        c.setDiaChi(params.get("diaChi"));
        c.setNgayTao(new Date());
        c.setIdtaiKhoan(taiKhoanService.getTaiKhoanById(Integer.parseInt(params.get("idtaiKhoan"))));
        this.chuTroService.addOrUpdateChuTro(c);
        ChuTro createdChuTro = this.chuTroService.getChuTroById(c.getId());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChuTro);
    }

    @GetMapping(path = "/chutro/{chutroId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<ChuTro> retrieve(@PathVariable(value = "chutroId") int id) {
        return new ResponseEntity<>(this.chuTroService.getChuTroById(id), HttpStatus.OK);
    }

    @DeleteMapping("/chutro/{chutroId}/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "chutroId") int id) {
        this.chuTroService.deleteChuTro(id);
    }
}
