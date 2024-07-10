package com.ms.user.controllers;

import com.ms.user.dto.UserRecordDto;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
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
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        log.info("###################");
        log.info("Iniciando cadastro");
        log.debug("Entrando no controller de cadastro");
        try {
            log.info("Chamando serviço de salvar usuário!");
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    userService.save(userRecordDto)
            );
        }
        catch (Exception e) {
            log.error("Ocorreu um erro durante o cadastro: {}", e.getMessage());
            log.info("###################");
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
