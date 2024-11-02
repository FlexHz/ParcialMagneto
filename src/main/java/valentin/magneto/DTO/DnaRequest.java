package valentin.magneto.DTO;

import lombok.Getter;
import lombok.Setter;
import valentin.magneto.validator.ValidDna;

@Getter
@Setter
public class DnaRequest {
    @ValidDna
    private String[] dna;
}
