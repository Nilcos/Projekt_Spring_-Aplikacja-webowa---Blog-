package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.repository.ContactRepository;
import pl.myblog.springblog.repository.PostRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Autowired
    private PostRepository postRepository;

//    public void addToDB(Contact contact){
//        contact.setDate  (java.time.LocalDateTime.now().plusHours(1));
//        contactRepository.save(contact);
}
