package com.sttlab.sagdemo.sagdemo.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sttlab.sagdemo.sagdemo.models.ApiError;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	// 400

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		errors.add(error);
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final String error = ex.getRequestPartName() + " part is missing";
		
		errors.add(error);
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final String error = ex.getParameterName() + " parameter is missing";

		errors.add(error);
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	//

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		errors.add(error);
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

		errors.add(error);
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			final HttpMessageNotReadableException ex, 
			final HttpHeaders headers, 
			final HttpStatus status, 
			final WebRequest request) {
		
		logger.info(ex.getClass().getName());
		
		final List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("BAD_REQUEST");
		apiError.setHttpStatus("400");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	// 405

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		errors.add(builder.toString());

		final ApiError apiError = new ApiError();
		apiError.setErrorCode("METHOD_NOT_ALLOWED");
		apiError.setHttpStatus("405");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	// 415

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
		
		errors.add(builder.toString());

		final ApiError apiError = new ApiError();
		apiError.setErrorCode("UNSUPPORTED_MEDIA_TYPE");
		apiError.setHttpStatus("405");
		apiError.setErrorMessages(errors);
		apiError.setId(UUID.randomUUID());

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}

	// 500

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error("error", ex);
		//	
		final ApiError apiError = new ApiError();
		apiError.setErrorCode("INTERNAL_SERVER_ERROR");
		apiError.setHttpStatus("500");
		apiError.setId(UUID.randomUUID());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}