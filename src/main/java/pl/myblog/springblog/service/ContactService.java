package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Contact;
import pl.myblog.springblog.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    public void addToDB(Contact contact) {
        contact.setDate(java.time.LocalDateTime.now().plusHours(1));
        contactRepository.save(contact);
    }
}
