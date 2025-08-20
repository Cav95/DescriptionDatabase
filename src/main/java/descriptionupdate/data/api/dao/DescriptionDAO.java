package descriptionupdate.data.api.dao;

import java.util.List;
import java.util.Optional;

import descriptionupdate.data.utils.DAOException;
import descriptionupdate.model.api.Description;

/**
 * Interface for Data Access Object (DAO) that provides methods to interact with
 * Person data.
 * This interface defines methods for retrieving a person by their CUIPerson.
 */
public interface DescriptionDAO {

    Optional<Description> getDescription(String itaDescription, String engDescription, String group) throws DAOException;

    void addDescription(String itaDescription, String engDescription, String group);

    void deleteDescription(String itaDescription, String engDescription, String group);

    void updateDescription(String exItaDescription, String exEngDescription, String exGroup, String newItaDescription,
            String newEngDescription);

    List<String> getAllGroupTypeString() throws DAOException;

    List<Description> getListDescription(String itaDescription, String engDescription, String group);

}
