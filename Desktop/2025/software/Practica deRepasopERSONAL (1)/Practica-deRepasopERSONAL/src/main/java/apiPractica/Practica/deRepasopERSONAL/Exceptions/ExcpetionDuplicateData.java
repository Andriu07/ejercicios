package apiPractica.Practica.deRepasopERSONAL.Exceptions;

import lombok.Getter;

public class ExcpetionDuplicateData extends RuntimeException {

    @Getter
    private String duplicateData;
    public ExcpetionDuplicateData(String message, String duplicateData) {
        super(message);
        this.duplicateData = duplicateData;
    }
}
