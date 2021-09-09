package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.org.sd.dal.GorevEmriModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class GorevEmriManager extends DBConnection implements IGenericService<GorevEmriModel> {

	@Override
	public void insert(final GorevEmriModel contract) {
		final Connection connection = getConnection();
		try {
			final Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO arac_gorev (GorevTarihi,GorevliPersonel,GorevTuru,GidilecekYer,SoforId,PlakaId,CikisSaati,CikisKm,DonusSaati,DonusKm,YapilanKm) VALUES ('"
							+ contract.getGorevTarihi() + "','" + contract.getGorevliPersonel() + "','"
							+ contract.getGorevTuru() + "','" + contract.getGidilecekYer() + "',"
							+ contract.getSoforId() + ",'" + contract.getPlakaId() + "'," + contract.getCikisSaati()
							+ "," + contract.getCikisKm() + "," + contract.getDonusSaati() + "," + contract.getDonusKm()
							+ "," + contract.getYapilanKm() + ")");

			statement.close();
			connection.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(final GorevEmriModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		final Connection connection = getConnection();
		final String sql = "DELETE FROM arac_gorev WHERE Id=" + id + "";
		try {
			final Statement statement = connection.createStatement();
			return statement.executeUpdate(sql) > 0;

		} catch (final SQLException e) {
			return false;
			// e.printStackTrace();
		}
	}

	@Override
	public List<GorevEmriModel> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GorevEmriModel> getList() {
		final List<GorevEmriModel> dataContract = new ArrayList<GorevEmriModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery(
					"SELECT arac_gorev.Id,GorevTarihi,GorevliPersonel,GorevTuru,GidilecekYer,sofor.AdSoyad,plaka.Plaka,"
							+ "CikisSaati,DonusSaati,CikisKm,DonusKm,YapilanKm FROM arac_gorev "
							+ "LEFT JOIN sofor ON arac_gorev.SoforId=sofor.Id "
							+ "LEFT JOIN plaka ON arac_gorev.PlakaId=plaka.Id ORDER BY arac_gorev.Id DESC LIMIT 100");
			while (rs.next()) {
				final GorevEmriModel contract = new GorevEmriModel();
				// final SoforContract contract1 = new SoforContract();
				// final PlakaContract contract2 = new PlakaContract();

				contract.setId(rs.getInt("Id"));
				contract.setGorevTarihi(rs.getString("GorevTarihi"));
				contract.setGorevliPersonel(rs.getString("GorevliPersonel"));
				contract.setGorevTuru(rs.getString("GorevTuru"));
				contract.setGidilecekYer(rs.getString("GidilecekYer"));
				contract.setAdSoyad(rs.getString("AdSoyad"));
				contract.setPlaka(rs.getString("Plaka"));
				contract.setCikisSaati(rs.getString("CikisSaati"));
				contract.setDonusSaati(rs.getString("DonusSaati"));
				contract.setCikisKm(rs.getInt("CikisKm"));
				contract.setDonusKm(rs.getInt("DonusKm"));
				contract.setYapilanKm(rs.getInt("YapilanKm"));

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
	public GorevEmriModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

	// arama data access layer
	public List<GorevEmriModel> getSearch(final String name) {

		final List<GorevEmriModel> dataContract = new ArrayList<GorevEmriModel>();
		final Connection connection = getConnection();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet rs = statement.executeQuery(
					"SELECT arac_gorev.Id,GorevTarihi,GorevliPersonel,GorevTuru,GidilecekYer,sofor.AdSoyad,plaka.Plaka,"
							+ "CikisSaati,DonusSaati,CikisKm,DonusKm,YapilanKm FROM arac_gorev "
							+ "LEFT JOIN sofor ON arac_gorev.SoforId=sofor.Id "
							+ "LEFT JOIN plaka ON arac_gorev.PlakaId=plaka.Id WHERE sofor.AdSoyad LIKE '%" + name
							+ "%'");
			while (rs.next()) {
				final GorevEmriModel contract = new GorevEmriModel();
				contract.setId(rs.getInt("Id"));
				contract.setGorevTarihi(rs.getString("GorevTarihi"));
				contract.setGorevliPersonel(rs.getString("GorevliPersonel"));
				contract.setGorevTuru(rs.getString("GorevTuru"));
				contract.setGidilecekYer(rs.getString("GidilecekYer"));
				contract.setAdSoyad(rs.getString("AdSoyad"));
				contract.setPlaka(rs.getString("Plaka"));
				// contract.setSoforId(rs.getInt("SoforId"));
				// contract.setPlakaId(rs.getInt("PlakaId"));
				contract.setCikisSaati(rs.getString("CikisSaati"));
				contract.setDonusSaati(rs.getString("DonusSaati"));
				contract.setCikisKm(rs.getInt("CikisKm"));
				contract.setDonusKm(rs.getInt("DonusKm"));
				contract.setYapilanKm(rs.getInt("YapilanKm"));

				dataContract.add(contract);
			}

		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;

	}

	@Override
	public void delete(final GorevEmriModel contract) {
		// TODO Auto-generated method stub

	}

}
