package template.data.api.dao;

import java.util.List;
import java.util.Optional;

import template.data.utils.DAOException;
import template.model.api.Description;

/**
 * Interface for Data Access Object (DAO) that provides methods to interact with
 * Person data.
 * This interface defines methods for retrieving a person by their CUIPerson.
 */
public interface DescriptionDAO {

    Optional<Description> getDescription(String itaDescription, String engDescription, String group) throws DAOException;

    List<Description> getListDescription() ;

    void deleteDescription(String itaDescription, String engDescription, String group);

    void fixDescription(String exItaDescription, String exEngDescription, String exGroup, String newItaDescription,
            String newEngDescription, String newGroup);

}
