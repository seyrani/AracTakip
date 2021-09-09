package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.org.sd.dal.AracBilgiModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class AracBilgiManager extends DBConnection implements IGenericService<AracBilgiModel> {

	@Override
	public void insert(final AracBilgiModel contract) {
		final Connection connection = getConnection();
		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO arac_bilgi (Plaka,AracTuru,Marka,Model,ModelYil,BaslamaTarihi,BitisTarihi,GelenKm,SaseNo,Durum) VALUES ('"
							+ contract.getPlaka() + "','" + contract.getAracTuru() + "','" + contract.getMarka() + "','"
							+ contract.getModel() + "'," + contract.getModelYil() + ",'" + contract.getBaslamaTarihi()
							+ "','" + contract.getBitisTarihi() + "'," + contract.getGelenKm() + ",'"
							+ contract.getSaseNo() + "','" + contract.getDurum() + "')");

			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(final AracBilgiModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		final Connection connection = getConnection();
		final String sql = "DELETE FROM arac_bilgi WHERE Id=" + id + "";
		try {
			final Statement statement = connection.createStatement();
			return statement.executeUpdate(sql) > 0;

		} catch (final SQLException e) {
			return false;
			// e.printStackTrace();
		}
	}

	@Override
	public void delete(final AracBilgiModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AracBilgiModel> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AracBilgiModel> getList() {
		final List<AracBilgiModel> dataContract = new ArrayList<AracBilgiModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery("SELECT * FROM arac_bilgi");
			while (rs.next()) {
				final AracBilgiModel contract = new AracBilgiModel();
				contract.setId(rs.getInt("Id"));
				contract.setPlaka(rs.getString("Plaka"));
				contract.setAracTuru(rs.getString("AracTuru"));
				contract.setMarka(rs.getString("Marka"));
				contract.setModel(rs.getString("Model"));
				contract.setModelYil(rs.getString("ModelYil"));
				contract.setBaslamaTarihi(rs.getString("BaslamaTarihi"));
				contract.setBitisTarihi(rs.getString("BitisTarihi"));
				contract.setGelenKm(rs.getString("GelenKm"));
				contract.setSaseNo(rs.getString("SaseNo"));
				contract.setDurum(rs.getString("Durum"));

				dataContract.add(contract);

			}

			statement.close();
			connection.close();

		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return dataContract;
	}

	// arama data access layer
	public List<AracBilgiModel> getSearch(final String name) {

		final List<AracBilgiModel> dataContract = new ArrayList<AracBilgiModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery("SELECT * FROM arac_bilgi WHERE marka LIKE '%" + name + "%'");
			while (rs.next()) {
				final AracBilgiModel contract = new AracBilgiModel();
				contract.setId(rs.getInt("Id"));
				contract.setPlaka(rs.getString("Plaka"));
				contract.setAracTuru(rs.getString("AracTuru"));
				contract.setMarka(rs.getString("Marka"));
				contract.setModel(rs.getString("Model"));
				contract.setModelYil(rs.getString("ModelYil"));
				contract.setBaslamaTarihi(rs.getString("BaslamaTarihi"));
				contract.setBitisTarihi(rs.getString("BitisTarihi"));
				contract.setGelenKm(rs.getString("GelenKm"));
				contract.setSaseNo(rs.getString("SaseNo"));
				contract.setDurum(rs.getString("Durum"));

				dataContract.add(contract);
			}

		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;

	}

	@Override
	public AracBilgiModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
