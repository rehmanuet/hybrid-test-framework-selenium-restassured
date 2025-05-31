package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private Integer id;
    private Integer ownerId;
    private String ownerName;
    private String name;
    private String birthDate;
    private PetTypePOJO type;
    private List<Visit> visits;


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class PetTypePOJO {
        private Integer id;
        private String name;
    }
}