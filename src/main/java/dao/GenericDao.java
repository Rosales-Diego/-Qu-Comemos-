package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {
	public T create(T entity);

	public T getById(Serializable id);

	public T update(T entity);

	public T delete(Serializable id);

	public void delete(T entity);

	public boolean exists(Serializable id);

	public List<T> getAll(String column);

}
