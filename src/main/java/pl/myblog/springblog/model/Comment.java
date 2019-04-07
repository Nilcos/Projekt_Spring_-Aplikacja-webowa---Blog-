package pl.myblog.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotEmpty(message = "Pole wymaga ciągu znaków!")
    private String userComment;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @NotNull
//    private User user;


    @Column(name = "userUnloggedName")
    @NotEmpty(message = "Pole wymaga ciągu znaków!")
    private String name;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;



    private LocalDateTime date;
}
