package template.data.api.dao;

import java.util.Optional;

import template.data.api.Person;
import template.data.utils.DAOException;

/**
 * Interface for Data Access Object (DAO) that provides methods to interact with
 * Person data.
 * This interface defines methods for retrieving a person by their CUIPerson.
 */
public interface PersonDAO {

    Optional<Person> getFromCUI(String CUIPerson) throws DAOException;


}
