package com.ms.user.controllers;

import com.ms.user.dto.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        log.info("Copiando DTO para Entidade");
        BeanUtils.copyProperties(userRecordDto, userModel);
        log.info("Chamando serviço de salvar usuário!");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.save(userModel)
        );
    }
}
