package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.org.sd.dal.AracYakitModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class AracYakitManager extends DBConnection implements IGenericService<AracYakitModel> {

	@Override
	public void insert(final AracYakitModel contract) {

		final Connection connection = getConnection();
		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO arac_yakit (Tarih,Plaka,YakitTuru,Miktar,OdemeTuru,BelgeNo,AktifKm,Tutar) VALUES ('"
							+ contract.getTarih() + "','" + contract.getPlaka() + "','" + contract.getYakitTuru() + "',"
							+ contract.getMiktar() + ",'" + contract.getOdemeTuru() + "'," + "'" + contract.getBelgeNo()
							+ "'," + contract.getAktifKm() + "," + contract.getTutar() + ")");

			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(final AracYakitModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		final Connection connection = getConnection();
		final String sql = "DELETE FROM arac_yakit WHERE Id=" + id + "";
		try {
			final Statement statement = connection.createStatement();
			return statement.executeUpdate(sql) > 0;

		} catch (final SQLException e) {
			return false;
			// e.printStackTrace();
		}

	}

	@Override
	public void delete(final AracYakitModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AracYakitModel> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AracYakitModel> getList() {
		final List<AracYakitModel> dataContract = new ArrayList<AracYakitModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery("SELECT * FROM arac_yakit");
			while (rs.next()) {
				final AracYakitModel contract = new AracYakitModel();
				contract.setId(rs.getInt("Id"));
				contract.setTarih(rs.getString("Tarih"));
				contract.setPlaka(rs.getString("Plaka"));
				contract.setYakitTuru(rs.getString("YakitTuru"));
				contract.setMiktar(rs.getString("Miktar"));
				contract.setOdemeTuru(rs.getString("OdemeTuru"));
				contract.setBelgeNo(rs.getString("BelgeNo"));
				contract.setAktifKm(rs.getString("AktifKm"));
				contract.setTutar(Double.parseDouble(rs.getString("Tutar")));

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
	public List<AracYakitModel> getSearch(final String name) {

		final List<AracYakitModel> dataContract = new ArrayList<AracYakitModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery("SELECT * FROM arac_yakit WHERE Plaka LIKE '%" + name + "%'");
			while (rs.next()) {
				final AracYakitModel contract = new AracYakitModel();
				contract.setId(rs.getInt("Id"));
				contract.setTarih(rs.getString("Tarih"));
				contract.setPlaka(rs.getString("Plaka"));
				contract.setYakitTuru(rs.getString("YakitTuru"));
				contract.setMiktar(rs.getString("Miktar"));
				contract.setOdemeTuru(rs.getString("OdemeTuru"));
				contract.setBelgeNo(rs.getString("BelgeNo"));
				contract.setAktifKm(rs.getString("AktifKm"));
				contract.setTutar(Double.parseDouble(rs.getString("Tutar")));

				dataContract.add(contract);
			}

		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;

	}

	@Override
	public AracYakitModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
