package com.ms.user.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.user.dto.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Log4j2
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public UserRecordDto save (UserRecordDto userRecordDto) throws Exception{
        log.debug("Entrando no service de cadastro");
        try {
            log.debug("Convertendo DTO para Entidade");
            var userModel = this.objectMapper.convertValue(userRecordDto,UserModel.class);

            if(this.userRepository.existsByEmail(userModel.getEmail())) {
                log.info("Email já cadastrado no banco de dados");
            }else {
                log.info("Tentando salvar usuário no banco");
                userRepository.save(userModel);
                log.info("Chamando producer e publicando mensagem");
                userProducer.publishMessageEmail(userModel);
            }
            log.info("###################");
            return userRecordDto;
        }
        catch (Exception e) {
            log.error("Ocorreu um erro durante o cadastro: {}", e.getMessage());
            throw e;
        }
    }

}
