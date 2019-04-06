package pl.myblog.springblog.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotEmpty(message = "Pole wymaga ciągu znaków!")
    private String userComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @NotNull
    @Column(name = "userUnloggedName")
    private String name;

    private LocalDateTime date;
}
