package Department.SSSO.Exceptions;

import lombok.Getter;

public class ExceptionDatosDuplicados extends RuntimeException {

  @Getter
  private String campoDuplicado;

  public ExceptionDatosDuplicados(String message) {
  } public ExceptionDatosDuplicados(String message ,String campoDuplicado){
    super( message);
    this.campoDuplicado = campoDuplicado;
  }
}
