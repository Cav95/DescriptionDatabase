package template.model;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import template.data.DescriptionDAOImpl;
import template.data.api.dao.DescriptionDAO;
import template.model.api.Description;

public final class Model {

    private final Connection connection;
    DescriptionDAO descriptionDAO;

    public Model(Connection connection) {
        this.connection = connection;
        this.descriptionDAO = new DescriptionDAOImpl(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    // Other model methods can be added here

    public Optional<Description> getDescription(String itaDescription, String engDescription, String group) {
        return descriptionDAO.getDescription(itaDescription, engDescription, group); // Placeholder return statement
    }
    public List<Description> getListDescription() {
            return descriptionDAO.getListDescription();

    }
 
}
