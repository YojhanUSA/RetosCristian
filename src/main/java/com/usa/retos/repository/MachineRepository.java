package com.usa.retos.repository;

import com.usa.retos.crud.MachineCrud;
import com.usa.retos.model.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MachineRepository {
    @Autowired
    private MachineCrud machineCrud;

    public List<Machine> getAll() {
        return (List<Machine>) machineCrud.findAll();
    }

    public Optional<Machine> getMachine(int id) {
        return machineCrud.findById(id);
    }

    public Machine save (Machine machine) {
        return machineCrud.save(machine);
    }

    public void delete (Machine machine) {
        machineCrud.delete(machine);
    }
}
