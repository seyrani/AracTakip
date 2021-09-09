package tr.org.sd.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import tr.org.sd.dal.KullaniciYetkiModel;
import tr.org.sd.helper.DBConnection;
import tr.org.sd.interfaces.IGenericService;

public class KullaniciYetkiManager extends DBConnection implements IGenericService<KullaniciYetkiModel> {

	@Override
	public void insert(final KullaniciYetkiModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final KullaniciYetkiModel contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(final int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<KullaniciYetkiModel> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public KullaniciYetkiModel GetPersoneIdVeSifre(final int kullaniciId, final String sifre) {

		final KullaniciYetkiModel contract = new KullaniciYetkiModel();
		final Connection connection = getConnection();
		try {
			final Statement statement = connection.createStatement();
			// select * from kullanici_yetki where KullaniciId=1
			final ResultSet rs = statement.executeQuery("SELECT * FROM kullanici_yetki WHERE KullaniciId ="
					+ kullaniciId + " AND Sifre='" + sifre.trim() + "'");

			while (rs.next()) {

				contract.setId(rs.getInt("Id"));
				contract.setKullaniciId(rs.getInt("KullaniciId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));

			}

			statement.close();
			connection.close();

		} catch (final Exception e) {
			System.out.println(e);
		}

		return contract;

	}

	@Override
	public List<KullaniciYetkiModel> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KullaniciYetkiModel getById(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final KullaniciYetkiModel contract) {
		// TODO Auto-generated method stub

	}

}
