/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.ChuTro;
import com.dnt.pojo.Comment;
import com.dnt.pojo.NguoiThue;
import com.dnt.pojo.PhongTro;
import com.dnt.pojo.Tin;
import com.dnt.services.ChuTroService;
import com.dnt.services.CommentService;
import com.dnt.services.NguoiThueService;
import com.dnt.services.TinService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
public class ApiTinController {
    @Autowired
    private TinService tinService;
    @Autowired
    private ChuTroService chuTroService;
    @Autowired
    private NguoiThueService nguoiThueService;
    @Autowired
    private CommentService commentService;

    
    @GetMapping("/tin/")
    @CrossOrigin
    public ResponseEntity<List<Tin>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.tinService.getTin(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/tin/{tinId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tin> retrieve(@PathVariable(value = "tinId") int id) {
        return new ResponseEntity<>(this.tinService.getTinById(id), HttpStatus.OK); 
    }
    
    @PostMapping(path = "/tin/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Tin tin) {
        this.tinService.addOrUpdate(tin);
    }

    @PatchMapping(path = "/tin/{tinId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(value = "tinId") int tinId, @RequestBody @Valid Tin tin) {
        tin.setId(tinId);

        this.tinService.addOrUpdate(tin);
    }

    @DeleteMapping("/tin/{tinId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "tinId") int id) {
        this.tinService.deleteTin(id);
    }
}
