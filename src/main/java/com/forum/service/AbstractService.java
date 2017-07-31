package com.forum.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonView;
import com.forum.configuration.constant.HttpMessage;
import com.forum.domain.AbstractEntity;
import com.forum.util.validation.RepositoryStatus;
import com.forum.util.view.Public;
/**
 * @author NamDT8
 *
 */
@Service
public class AbstractService {

public String findMessage (HttpStatus status) {
	switch (status) {
	case OK:
		return HttpMessage.SUCCESS;
	case BAD_REQUEST:

		return HttpMessage.BAD_REQUEST;
	case NOT_FOUND:
		return HttpMessage.NOT_FOUND;
		default:
			return HttpMessage.SERVER_ERROR;
	}
	}

public HttpStatus handleException(Exception exception) {
	String exceptionName = exception.getClass().getName();
	switch (exceptionName) {
	case "DataAccessException":
		return HttpStatus.INTERNAL_SERVER_ERROR;
	case "ObjectNotFoundException":
		return HttpStatus.NOT_FOUND;
	}
	return HttpStatus.INTERNAL_SERVER_ERROR;
}

@JsonView(Public.class)
public <T extends List<?>> Integer getRowCount (RepositoryStatus<T> repositoryStatus) {
	return repositoryStatus.getT().size();
}

@JsonView(Public.class)
public <T extends List<? extends AbstractEntity>> List<?> getDataList (RepositoryStatus<T> repositoryStatus) {
	return repositoryStatus.getT();
}

}