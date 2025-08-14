package template.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import template.data.api.dao.DescriptionColumnName;
import template.data.api.dao.DescriptionDAO;
import template.data.queries.Queries;
import template.data.utils.DAOException;
import template.data.utils.DAOUtils;
import template.model.api.Description;

public class DescriptionDAOImpl implements DescriptionDAO {

    private final Connection connection;

    /**
     * Constructor for PlanetDAOImpl.
     * 
     * @param connection the database connection
     */
    public DescriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Description> getDescription(String itaDescription, String engDescription, String group)
            throws DAOException {
        try (
                var statement = DAOUtils.prepare(connection, Queries.GET_ONE_DES, itaDescription, engDescription,
                        group);
                var resultSet = statement.executeQuery();) {
            if (resultSet.next()) {
                var itaDes = resultSet.getString(DescriptionColumnName.ITA_DES.getColumnName());
                var engDes = resultSet.getString(DescriptionColumnName.ENG_DES.getColumnName());
                var groupRes = resultSet.getString(DescriptionColumnName.GROUP.getColumnName());
                var description = new Description(itaDes, engDes, groupRes);
                return Optional.of(description);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Description> getListDescription(String itaDescription, String engDescription, String group) {
        List<Description> descriptions = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, Queries.GET_ALL_TABLE, itaDescription, engDescription, group);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                descriptions.add(new Description(resultSet.getString(DescriptionColumnName.ITA_DES.getColumnName()),
                        resultSet.getString(DescriptionColumnName.ENG_DES.getColumnName()),
                        resultSet.getString(DescriptionColumnName.GROUP.getColumnName())));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return descriptions;

    }

    @Override
    public void addDescription(String itaDescription, String engDescription, String group) {
        {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_ONE_DES, itaDescription, engDescription,
                            group);) {
                statement.executeUpdate();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void deleteDescription(String itaDescription, String engDescription, String group) {

        try (
                var statement = DAOUtils.prepare(connection, Queries.DELETE_ONE_DES, itaDescription, engDescription,
                        group);) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    @Override
    public void updateDescription(String exItaDescription, String exEngDescription, String exGroup,
            String newItaDescription, String newEngDescription) {
        try (
                var statement = DAOUtils.prepare(connection, Queries.UPDATE_ONE_DES,
                        newItaDescription, newEngDescription, exItaDescription, exEngDescription, exGroup);) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    @Override
    public List<String> getAllGroupTypeString() throws DAOException {
        List<String> groupTypes = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, Queries.ALL_GROUP_TYPE_STRING);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                groupTypes.add(resultSet.getString(DescriptionColumnName.GROUP.getColumnName()));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return groupTypes;
    }
}
