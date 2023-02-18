package tn.esprit.springfever.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.esprit.springfever.enums.MediaType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "PostMedia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class PostMedia implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String location;

    @Lob
    byte[] content;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @ManyToOne
    private Post post;

    public PostMedia(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public PostMedia(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

}