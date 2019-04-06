package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Subject;
import pl.myblog.springblog.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;


    public List<Subject> getAllCourses() {
        return subjectRepository.findAllByOrderByPostSubject();
    }

    public void deleteCourse(long id) {

        subjectRepository.deleteById(id);
    }


    public void addToDB(Subject subject) {

        subjectRepository.save(subject);
    }



}
