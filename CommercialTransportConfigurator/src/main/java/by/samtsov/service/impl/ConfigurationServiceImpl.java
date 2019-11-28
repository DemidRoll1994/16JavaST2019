package by.samtsov.service.impl;

import by.samtsov.bean.Configuration;
import by.samtsov.service.ConfigurationService;

import java.util.List;

public class ConfigurationServiceImpl implements ConfigurationService {
    @Override
    public Configuration get(int id) {
        return null;//todo
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
/*

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

    }*/
}
