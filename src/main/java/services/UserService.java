package services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import entity.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by nikita93 on 03.07.2017.
 */
public class UserService {

    private ConnectionSource source;
    private Dao<User, String> dao;

    public UserService(Properties properties) throws SQLException, IOException {
        source = new JdbcConnectionSource(properties.getProperty("sqlite_url"));
        dao = DaoManager.createDao(source, User.class);
        createUserTable(properties.getProperty("create_table"));
    }

    private void createUserTable(String s) throws SQLException {
        dao.executeRawNoArgs(s);
    }

    public List <User> getAll() throws SQLException {
        return dao.queryForAll();
    }

    public User getById(String id) throws SQLException {
        return dao.queryForId(id);
    }

    public void insertUser(User user) throws SQLException {
        dao.createOrUpdate(user);
    }

    public boolean isExist(String id) {
        return true;
    }

}
