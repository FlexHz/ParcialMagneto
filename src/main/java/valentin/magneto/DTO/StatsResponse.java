package valentin.magneto.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import valentin.magneto.validator.ValidDna;

@AllArgsConstructor
@Getter
@Setter
public class StatsResponse {

    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;
    @JsonProperty
    private double ratio;
}
