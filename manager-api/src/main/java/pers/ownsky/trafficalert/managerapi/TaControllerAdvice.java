package pers.ownsky.trafficalert.managerapi;

//import org.hibernate.JDBCException;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pers.ownsky.trafficalert.publicutils.json.RestError;
import pers.ownsky.trafficalert.publicutils.json.RestException;

//import java.sql.SQLException;

@ControllerAdvice
public class TaControllerAdvice {

//    @ResponseBody
    @ExceptionHandler(RestException.class)
    public ResponseEntity<RestError> handleRestException(RestException re){
        RestError restError = new RestError();
        restError.setStatus(re.getStatus().value());
        restError.setMessage(re.getMessage());
        return new ResponseEntity<>(restError, re.getStatus());
    }

//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<RestError> handleSQLException(SQLException se) {
//        RestException re = new RestException(HttpStatus.CONFLICT, se.getMessage());
//        return handleRestException(re);
//    }
//
//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<RestError> handleDAException(DataAccessException dae) {
//        JDBCException jdbcException = (JDBCException) dae.getCause();
//        SQLException sqlException = jdbcException.getSQLException();
//        return handleSQLException(sqlException);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError> handleException(Exception e){
        RestException re = new RestException(e);
        return handleRestException(re);
//        DataIntegrityViolationException;
//        ConstraintViolationException
    }

}
