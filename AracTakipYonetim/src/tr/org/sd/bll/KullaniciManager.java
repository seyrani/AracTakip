package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.org.sd.dal.KullaniciModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class KullaniciManager extends DBConnection implements IGenericService<KullaniciModel> {

	@Override
	public void insert(final KullaniciModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final KullaniciModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<KullaniciModel> GetAll() {
		final List<KullaniciModel> datacontract = new ArrayList<KullaniciModel>();
		final Connection connection = getConnection();
		KullaniciModel contract;

		try {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM kullanici");
			while (resultSet.next()) {
				contract = new KullaniciModel();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				contract.setEposta(resultSet.getString("Eposta"));

				datacontract.add(contract);
			}
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;

	}

	@Override
	public List<KullaniciModel> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KullaniciModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final KullaniciModel contract) {
		// TODO Auto-generated method stub

	}

}
