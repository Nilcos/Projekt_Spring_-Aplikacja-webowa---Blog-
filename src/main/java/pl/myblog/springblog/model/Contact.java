package pl.myblog.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Contact {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotEmpty(message = "Proszę podać e-mail!")
        @Email(message = "Proszę podać e-mail!")
        @Column(unique = true)
        private String email;

        @NotEmpty(message = "Pole wymaga ciągu znaków!")
        private String message;

        private LocalDateTime date;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;


}
