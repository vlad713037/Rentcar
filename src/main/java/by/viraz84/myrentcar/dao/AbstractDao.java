package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.RowMapper;
import by.viraz84.myrentcar.exception.EntityDeleteException;
import by.viraz84.myrentcar.exception.EntityNotFoundException;
import by.viraz84.myrentcar.exception.EntityRetrieveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

    public static final String SELECT_STATEMENT = "SELECT * FROM %s";
    public static final String DELETE_STATEMENT = "DELETE FROM %s WHERE id = %d";
    public static final String SELECT_BY_ID_SQL = SELECT_STATEMENT + " WHERE id = %d";
    public static final String ORDERED_PAGINATED = SELECT_STATEMENT + " ORDER BY %s LIMIT %d OFFSET %d";

    protected RowMapper<T> rowMapper;
    protected String tableName;


    public AbstractDao(RowMapper<T> rowMapper, String tableName){
        super();
        this.rowMapper = rowMapper;
        this.tableName = tableName;
    }

    public T getById(int id) {

        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(String.format(SELECT_BY_ID_SQL, tableName, id));
            if (resultSet.next()) {
                T entity = rowMapper.toObject(resultSet);
                return entity;
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user retrieval by id=" + id, e);
            throw new EntityNotFoundException();
        }
    }

    public List<T> findAll(){
        List<T> entities = new ArrayList<>();
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            ResultSet resultSet = connection.createStatement().executeQuery(String.format(SELECT_STATEMENT, tableName));


            while (resultSet.next()){
                entities.add(rowMapper.toObject(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during users retrieval", e);
            throw new EntityRetrieveException(e);
        }
        return entities;
    }

    public void deleteById(int id){
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            int num = connection.createStatement().executeUpdate(String.format(DELETE_STATEMENT, tableName, id));

            if (num != 1) {
                throw new EntityDeleteException("Entity not deleted");
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during users retrieval", e);
            throw new EntityRetrieveException(e);
        }
    }

    public List<T> findAllSorded(String fieldName, Integer limit, Integer offset) {
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement()
                    .executeQuery(String.format(ORDERED_PAGINATED, tableName, fieldName, limit, offset));

            List<T> entities = new ArrayList<>();

            while (resultSet.next()) {
                entities.add(rowMapper.toObject(resultSet));
            }

            return entities;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during users retrieval", e);
            throw new EntityRetrieveException(e);
        }
    }

    public RowMapper<T> getRm() {
        return rowMapper;
    }

}
