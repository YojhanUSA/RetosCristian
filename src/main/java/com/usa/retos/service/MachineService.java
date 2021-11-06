package com.usa.retos.service;

import com.usa.retos.model.Machine;
import com.usa.retos.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAll() {
        return machineRepository.getAll();
    }

    public Optional<Machine> getMachine(int id) {
        return machineRepository.getMachine(id);
    }

    public Machine save(Machine machine){
        if(machine.getId()==null){
            return machineRepository.save(machine);
        }else{
            Optional<Machine> e=machineRepository.getMachine(machine.getId());
            if(e.isEmpty()){
                return machineRepository.save(machine);
            }else{
                return machine;
            }
        }
    }

    public Machine update(Machine machine){
        if(machine.getId()!=null){
            Optional<Machine> tmpMachine =machineRepository.getMachine(machine.getId());
            if(!tmpMachine.isEmpty()){
                if(machine.getName()!=null){
                    tmpMachine.get().setName(machine.getName());
                }
                if(machine.getBrand()!=null){
                    tmpMachine.get().setBrand(machine.getBrand()); ;
                }
                if(machine.getYear() !=null){
                    tmpMachine.get().setYear(machine.getYear());
                }
                if(machine.getDescription()!=null){
                    tmpMachine.get().setDescription(machine.getDescription());
                }
                if(machine.getCategory()!=null){
                    tmpMachine.get().setCategory(machine.getCategory());
                }
                machineRepository.save(tmpMachine.get());
                return tmpMachine.get();
            }else{
                return machine;
            }
        }else{
            return machine;
        }
    }

    public boolean deleteMachine(int id) {
        Boolean aBoolean = getMachine(id).map(machine -> {
            machineRepository.delete(machine);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
