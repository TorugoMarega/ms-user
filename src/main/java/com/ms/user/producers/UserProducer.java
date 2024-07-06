package com.ms.user.producers;

import com.ms.user.dto.EmailDto;
import com.ms.user.models.UserModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserProducer {

    final RabbitTemplate rabbitTemplate;


    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void  publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDto();
        log.info("Preenchendo EmailDTO com os dados do UserDTO");
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro Realizado com sucesso!");
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \n Agradecemos o seu cadastro, aproveite agora todos os seus recursos da nossa plataforma!");
        log.info("Enviando para a fila");
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
