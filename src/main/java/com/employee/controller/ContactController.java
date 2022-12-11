package com.employee.controller;

import com.employee.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employee.ServicesImpl.EmployeeService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class ContactController {

    @Autowired
    private EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @PostMapping("/add")
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
//        return employeeService.addContact(contact);
        return new ResponseEntity<Contact>(employeeService.addContact(contact), HttpStatus.CREATED);
    }

    @GetMapping("/getcontacts")
    public List<Contact> getAllContact() {
        return employeeService.getAllContacts();
    }

    @GetMapping("/contacts/{id}")
    public Optional<Contact> getContact(@PathVariable int id) {
        return employeeService.getContact(id);
    }

    @PutMapping("/contacts/{id}")
    public void updateContact(@RequestBody Contact contact, @PathVariable int id) {
        employeeService.updateContact(id, contact); }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable int id) {
        employeeService.deleteContact(id);
    }
}

