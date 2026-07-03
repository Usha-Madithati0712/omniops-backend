package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Client;
import com.omniops.omniops_backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @PostMapping("/convert/{leadId}")
    public Client convertLead(@PathVariable Integer leadId){
        return clientService.convertLead(leadId);
    }

    @GetMapping
    public List<Client> all(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable Integer id){
        return clientService.findById(id);
    }
@PutMapping("/{id}")
public Client update(@PathVariable Integer id,
                     @RequestBody Client client){

    return clientService.update(id, client);

}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        clientService.delete(id);
    }

}