package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.org.sd.dal.PlakaModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class PlakaManager extends DBConnection implements IGenericService<PlakaModel> {

	@Override
	public void insert(final PlakaModel contract) {
		final Connection connection = getConnection();
		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO plaka (Plaka) VALUES ('" + contract.getPlaka() + "')");

			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(final PlakaModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PlakaModel> GetAll() {
		final List<PlakaModel> datacontract = new ArrayList<PlakaModel>();
		final Connection connection = getConnection();
		PlakaModel contract;

		try {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM plaka");
			while (resultSet.next()) {

				contract = new PlakaModel();
				contract.setId(resultSet.getInt("Id"));
				contract.setPlaka(resultSet.getString("Plaka"));
				datacontract.add(contract);

			}
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public List<PlakaModel> getList() {
		final List<PlakaModel> dataContract = new ArrayList<PlakaModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery("SELECT * from plaka");
			while (rs.next()) {
				final PlakaModel contract = new PlakaModel();

				contract.setId(rs.getInt("Id"));
				contract.setPlaka(rs.getString("Plaka"));

				dataContract.add(contract);

				// System.out.println(rs);

			}

			statement.close();
			connection.close();

		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public PlakaModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

	// jlist delete
	@Override
	public void delete(final PlakaModel contract) {
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM plaka WHERE Id=" + contract.getId());
			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
