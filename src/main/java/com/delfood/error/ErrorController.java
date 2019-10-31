package com.delfood.error;

import com.delfood.error.exception.DuplicateIdException;
import com.delfood.error.exception.InvalidMenuGroupCountException;
import com.delfood.error.exception.InvalidMenuGroupIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 스프링에서 지원하는 예외처리 어노테이션
 * 
 * @ExceptionHandler({Exception1.class, Exception2.class}) 
 * - @Controller, @RestController에서 사용 가능
 * - 컨트롤러 클래스 내에서 발생하는 예외를 해당 어노테이션이 적용된 메서드를 통해 처리한다.
 * 
 * @ControllerAdvice ( @RestControllerAdvice )
 * - 모든 @Controller 또는 @RestController에 적용되는 공통클래스를 만들 때 사용한다.
 */
@RestControllerAdvice
public class ErrorController {

  /**
   * 패키지 명을 제외한 클래스 이름을 반환한다.
   * 
   * @param e 에러
   * @return
   */
  private static String getSimpleName(Exception e) {
    return e.getClass().getSimpleName();
  }
  
  @ResponseStatus(HttpStatus.CONFLICT) // 반환할 상태코드 설정한다.
  @ExceptionHandler(DuplicateIdException.class) // 처리할 에러를 설정한다.
  public ErrorMsg handleDuplicateIdException(DuplicateIdException e) {
    // Exception 객체의 현지화 메시지와 클래스 이름을 반환한다.
    return new ErrorMsg(e.getLocalizedMessage(), getSimpleName(e));
  }
  
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidMenuGroupCountException.class)
  public ErrorMsg handleInvalidMenuGroupCountException(InvalidMenuGroupCountException e) {
    return new ErrorMsg(e.getLocalizedMessage(), getSimpleName(e));
  }
  
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidMenuGroupIdException.class)
  public ErrorMsg handleInvalidMenuGroupIdException(InvalidMenuGroupIdException e) {
    return new ErrorMsg(e.getLocalizedMessage(), getSimpleName(e));
  }
}