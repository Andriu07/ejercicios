package SSS0.Observation_Employee.Excptions;

import lombok.Getter;

public class ExceptionDatosDuplicados extends RuntimeException {

    @Getter
    private String campoDuplicado;

    public ExceptionDatosDuplicados(String message) {
        super(message);
    }
    public ExceptionDatosDuplicados(String message ,String campoDuplicado){
        super( message);
        this.campoDuplicado = campoDuplicado;
    }
}
