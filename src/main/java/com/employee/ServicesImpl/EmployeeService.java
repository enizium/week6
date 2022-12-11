 package com.employee.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.Repository.ContactRepository;
import com.employee.model.Contact;

@Service
public class EmployeeService {
	
	@Autowired
    private ContactRepository contactRepository;
	

    public List<Contact> getAllContacts(){
        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll()
                .forEach(contacts::add);
        return contacts;
    }

    public Contact addContact(Contact contact){
        contactRepository.save(contact);
        return contact;
    }

    public Optional<Contact> getContact(int id){
        return contactRepository.findById(id);
    }

    public void updateContact(int id, Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(int id){
         contactRepository.deleteById(id);
    }
}

