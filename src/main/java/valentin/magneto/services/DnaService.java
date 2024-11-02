package valentin.magneto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valentin.magneto.entities.Dna;
import valentin.magneto.repositories.DnaRepository;

import java.util.Optional;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public static boolean isMutant(String[] dna) {
        int l = dna.length;
        int contadorseq = 0;

        //Buscar combinacion en filas
        contadorseq += Filanizador(dna, l, contadorseq);
        if (contadorseq > 1) {
            return true;
        }

        //Buscar combinacion en columnas
        contadorseq += Columnizador(dna, l, contadorseq);
        if (contadorseq > 1) {
            return true;
        }

        //Buscar combinacion en diagonales
        contadorseq += Diagonalizador(dna, l, contadorseq);
        return contadorseq > 1;
    }

    private static int Filanizador(String[] dna, int l, int total) {
        int encontradas = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < (l - 3); j++) {
                if (dna[i].charAt(j) == dna[i].charAt(j + 3)) {
                    if (dna[i].charAt(j + 1) == dna[i].charAt(j + 2)) {
                        encontradas++;
                        if (total == 1 || encontradas > 1) {
                            return 2;
                        }
                        if (j + 4 <= l - 4) {
                            j += 4;
                        } else break;
                    }
                }
            }
        }
        return encontradas;
    }

    private static int Columnizador(String[] dna, int l, int total) {
        int encontradas = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < (l - 3); j++) {
                if (dna[j].charAt(i) == dna[j + 3].charAt(i)) {
                    if (dna[j + 1].charAt(i) == dna[j + 2].charAt(i)) {
                        encontradas++;
                        if (total == 1 || encontradas > 1) {
                            return 2;
                        }
                        if (j + 4 <= l - 4) {
                            j += 4;
                        } else break;
                    }
                }
            }
        }
        return encontradas;
    }

    private static int Diagonalizador(String[] dna, int l, int total) {
        int encontradas = 0;
        int ltoa = (l - 1);
        //Buscar en la diagonal principal y secundaria de la matriz
        for (int i = 0; i < l - 3; i++) {
            if (dna[i].charAt(i) == dna[i + 3].charAt(i + 3)) {
                if (dna[i + 1].charAt(i + 1) == dna[i + 2].charAt(i + 2)) {
                    encontradas++;
                    if (total == 1 || encontradas > 1) {
                        return 2;
                    }
                    if (i + 4 < l - 4) {
                        i += 4;
                    } else break;
                }
            }
        }

        for (int i = ltoa; i >= 3; i--) {
            if (dna[i].charAt(ltoa - i) == dna[i - 3].charAt(ltoa - i + 3)) {
                if (dna[i - 1].charAt(ltoa - i + 1) == dna[i - 2].charAt(ltoa - i + 2)) {
                    encontradas++;
                    if (total == 1 || encontradas > 1) {
                        return 2;
                    }
                    if (i - 4 >= 4) {
                        i -= 4;
                    } else break;
                }
            }
        }

        //Buscar todas las diagonales de izq -> der descendentes restantes

        //Encontrar por debajo de diagonal principal
        for (int dez = 1; dez < l - 3; dez++) {
            for (int i = 0; i < l - 3 - dez; i++) {
                if (dna[dez + i].charAt(i) == dna[dez + i + 3].charAt(i + 3)) {
                    if (dna[dez + i + 1].charAt(i + 1) == dna[dez + i + 2].charAt(i + 2)) {
                        encontradas++;
                        if (total == 1 || encontradas > 1) {
                            return 2;
                        }
                        if (i + 4 < l - dez - 3) {
                            i += 4;
                        } else break;
                    }
                }
            }
        }

        //Encontrar por encima de la diagonal principal
        for (int dez = 1; dez < l - 3; dez++) {
            for (int i = 0; i < l - 3 - dez; i++) {
                if (dna[i].charAt(dez + i) == dna[i + 3].charAt(dez + i + 3)) {
                    if (dna[i + 1].charAt(dez + i + 1) == dna[i + 2].charAt(dez + i + 2)) {
                        encontradas++;
                        if (total == 1 || encontradas > 1) {
                            return 2;
                        }
                        if (i + 4 < l - dez - 3) {
                            i += 4;
                        } else break;
                    }
                }
            }
        }

        //Encontrar en las diagonales por encima de la secundaria
        for (int dez = 3; dez < ltoa - 1; dez++) {
            for (int i = 0; i < dez - 2; i++) {
                if (dna[dez - i].charAt(i) == dna[dez - i - 3].charAt(i + 3)) {
                    if (dna[dez - i - 1].charAt(i + 1) == dna[dez - i - 2].charAt(i + 2)) {
                        encontradas++;
                        if (total == 1 || encontradas > 1) {
                            return 2;
                        }
                        if (i + 4 < dez - 2) {
                            i += 4;
                        } else break;
                    }
                }
            }
        }

        //Encontrar en las diagonales por debajo de la secundaria
        for (int dez = 1; dez < l - 3; dez++) {
            for (int i = ltoa; i > 2 + dez; i--) {
                if (dna[i].charAt(ltoa - i + dez) == dna[i - 3].charAt(ltoa - i + dez + 3)) {
                    if (dna[i - 1].charAt(ltoa - i + dez + 1) == dna[i - 2].charAt(ltoa - i + dez + 2)) {
                        encontradas++;
                        if (total == 1 || encontradas > 1) {
                            return 2;
                        }
                        if (i - 4 > dez + 2) {
                            i -= 4;
                        } else break;
                    }
                }
            }
        }
        return -1;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verificamos si el ADN ya existe en la base de datos
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            // Si el ADN ya fue analizado, retornamos el resultado
            return existingDna.get().isMutant();
        }

        // Determinamos si el ADN es mutante y lo guardamos en la base de datos
        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant(dna);
    }
}
