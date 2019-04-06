package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}