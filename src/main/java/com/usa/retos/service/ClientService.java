package com.usa.retos.service;

import com.usa.retos.model.Client;
import com.usa.retos.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es el servicio de Cliente
 */
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Este metodo obtiene toda la lista de clientes
     * @return
     */
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    /**
     * Este metodo obtiene un cliente por id
     * @param idClient
     * @return
     */
    public Optional<Client> getClient(int idClient) {
        return clientRepository.getClient(idClient);
    }

    /**
     * Este metodo guarda un cliente
     * @param client
     * @return
     */
    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> tmpClient = clientRepository.getClient(client.getIdClient());
            if (tmpClient.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    /**
     * Este metodo actualiza un cliente
     * @param client
     * @return
     */
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> tmpClient = clientRepository.getClient(client.getIdClient());
            if (!tmpClient.isEmpty()) {
                if (client.getEmail() != null) {
                    tmpClient.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    tmpClient.get().setPassword(client.getPassword());
                }
                if (client.getName() != null) {
                    tmpClient.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    tmpClient.get().setAge(client.getAge());
                }
                clientRepository.save(tmpClient.get());
                return tmpClient.get();

            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    /**
     * Este metodo elimina un cliente
     * @param idClient
     * @return
     */
    public boolean deleteClient(int idClient) {
        Boolean aBoolean = getClient(idClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
