package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.org.sd.dal.SoforModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class SoforManager extends DBConnection implements IGenericService<SoforModel> {

	@Override
	public void insert(final SoforModel contract) {
		final Connection connection = getConnection();
		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO sofor (AdSoyad) VALUES ('" + contract.getAdSoyad() + "')");

			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(final SoforModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SoforModel> GetAll() {
		final List<SoforModel> datacontract = new ArrayList<SoforModel>();
		final Connection connection = getConnection();
		SoforModel contract;

		try {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM sofor");
			while (resultSet.next()) {

				contract = new SoforModel();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdSoyad(resultSet.getString("AdSoyad"));
				datacontract.add(contract);

			}
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public List<SoforModel> getList() {

		final List<SoforModel> dataContract = new ArrayList<SoforModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery("SELECT * from sofor");
			while (rs.next()) {
				final SoforModel contract = new SoforModel();

				contract.setId(rs.getInt("Id"));
				contract.setAdSoyad(rs.getString("AdSoyad"));

				dataContract.add(contract);

			}

			statement.close();
			connection.close();

		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public SoforModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final SoforModel contract) {
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM sofor WHERE Id=" + contract.getId());
			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
