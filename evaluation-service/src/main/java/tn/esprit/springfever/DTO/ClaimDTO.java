package tn.esprit.springfever.DTO;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.springfever.entities.User;
import tn.esprit.springfever.enums.ClaimRate;
import tn.esprit.springfever.enums.ClaimStatus;
import tn.esprit.springfever.enums.ClaimSubject;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
@ToString
public class ClaimDTO implements Serializable  {


    private Long idClaim;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private ClaimSubject claimSubject ;
    @NotBlank
    @Size(max = 500)
    private String desciption;
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus ;
    private String decision ;
    @Enumerated(EnumType.STRING)
    private ClaimRate claimRate ;
     private User user ;

    public ClaimDTO(Long idClaim, ClaimSubject claimSubject, String desciption, ClaimStatus claimStatus,
                    String decision, ClaimRate claimRate) {
        this.idClaim = idClaim;
        this.claimSubject = claimSubject;
        this.desciption = desciption;
        this.claimStatus = claimStatus;
        this.decision = decision;
        this.claimRate = claimRate;

    }
}

