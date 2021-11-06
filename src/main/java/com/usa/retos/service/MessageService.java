package com.usa.retos.service;

import com.usa.retos.model.Message;
import com.usa.retos.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es el servicio de Mensaje
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    /**
     * Este metodo obtiene toda la lista de mensajes
     * @return
     */
    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    /**
     * Este metodo obtiene un mensaje por id
     * @param idMessage
     * @return
     */
    public Optional<Message> getMessage(int idMessage) {
        return messageRepository.getMessage(idMessage);
    }

    /**
     * Este metodo guarda un mensaje
     * @param message
     * @return
     */
    public Message save(Message message){
        if(message.getIdMessage()==null){
            return messageRepository.save(message);
        }else{
            Optional<Message> tmpMessage =messageRepository.getMessage(message.getIdMessage());
            if(tmpMessage.isEmpty()) {
                return messageRepository.save(message);
            }else{
                return message;
            }
        }
    }

    /**
     * Este metodo actualiza un mensaje
     * @param message
     * @return
     */
    public Message update(Message message) {
        if (message.getIdMessage()!= null) {
            Optional<Message> tmpMessage = messageRepository.getMessage(message.getIdMessage());
            if (!tmpMessage.isEmpty()) {
                if (message.getMessageText()!= null) {
                    tmpMessage.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(tmpMessage.get());
                return tmpMessage.get();

            } else {
                return message;
            }
        } else {
            return message;
        }
    }

    /**
     * Este metodo elimina un mensaje
     * @param idMessage
     * @return
     */
    public boolean deleteMessage(int idMessage) {
        Boolean aBoolean = getMessage(idMessage).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
