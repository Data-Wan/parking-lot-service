package assessment.parkinglot.common.presentation.controller.handler;

import assessment.parkinglot.parkingspace.application.exception.ReserveParkingSpaceFailedNoEmptySpacesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error("Unhandled runtime exception: ", ex);
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Unhandled exception: ", ex);
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReserveParkingSpaceFailedNoEmptySpacesException.class)
    public ResponseEntity<Object> handleException(ReserveParkingSpaceFailedNoEmptySpacesException ex) {
        log.error("Exception: ", ex);
        return new ResponseEntity<>("Parking space reservation failed, no empty spaces available for this type of vehicle", HttpStatus.FORBIDDEN);
    }
}
