package by.samtsov.service;

import by.samtsov.bean.Configuration;
import by.samtsov.dao.ConfigurationDao;
import by.samtsov.dao.ModelDao;
import by.samtsov.dao.factory.DaoFactory;
import by.samtsov.dao.factory.DaoType;

import java.util.List;

public class ConfigurationServiceImpl implements ConfigurationService {


    @Override
    public List<Author> findAll() throws PersistentException {
        return authorDao.read();
    }

    @Override
    public Author findByIdentity(Integer identity) throws PersistentException {
        return authorDao.read(identity);
    }

    @Override
    public void save(Author author) throws PersistentException {
        if (author.getIdentity() != null) {
            authorDao.update(author);
        } else {
            author.setIdentity(authorDao.create(author));
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        authorDao.delete(identity);
    }

    @Override
    public Map<Author, Integer> findAllWithNumberOfBooks() throws PersistentException {
        return authorDao.readWithNumberOfBooks();
    }

    private UserDao getDao() {
        UserDao userDao = transaction.createDao(AuthorDao.class);

    }


    @Override
    public Configuration get(int id) {
        ConfigurationDao configurationDao = DaoFactory.createDao(DaoType.CONFIGURATION);
        ModelService modelService =
        Configuration configuration = configurationDao.get(id);
        configuration.setModel();
        return null;
    }

    @Override
    public List<Configuration> getAll() {


        return null;
    }

    @Override
    public int add(Configuration configuration) {
        return 0;
    }

    @Override
    public void update(Configuration configuration) {

    }

    @Override
    public void delete(int id) {

    }
}
