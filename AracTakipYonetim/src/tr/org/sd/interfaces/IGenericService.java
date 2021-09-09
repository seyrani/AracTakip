package tr.org.sd.interfaces;

import java.util.List;

public interface IGenericService<T> {

	public void insert(T contract);

	public void update(T contract);

	boolean delete(int id);

	public void delete(T contract);

	public List<T> GetAll();

	public List<T> getList();

	public T getById(int id);

}
