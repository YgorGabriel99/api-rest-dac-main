/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import trabalhofinal.api.event.*;
import java.util.List;

/**
 *
 * @author jana_
 */

@RestController
@RequestMapping("/event")

public class EventController {
    
    @Autowired
    private EventRepository repository;

    @GetMapping
    public List<Event> Get() {
        return repository.findAll();
    }

    
    @PostMapping
    @Transactional
    public void Post(@RequestBody EventRecord eventData){
         repository.save(new Event(eventData));
    
    }
    @GetMapping("/{id}")
    public ResponseEntity<Event> GetById(@PathVariable(value = "id") Long id) {
        Optional<Event> event = repository.findById(id);
    
        if (event.isPresent()) {
            return ResponseEntity.ok().body(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Event> Put(@PathVariable(value = "id") Long id, @RequestBody Event eventData) {
        Optional<Event> optionalEvent = repository.findById(id);
    
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            
            event.setNome(eventData.getNome());
            event.setDescricao(eventData.getDescricao());
            event.setSigla(eventData.getSigla());

    
            Event updatedEvent = repository.save(event);
            return ResponseEntity.ok().body(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Event> event = repository.findById(id);
        if(event.isPresent()){
            repository.delete(event.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    
}
