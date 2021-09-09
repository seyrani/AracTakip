package tr.org.sd.dal;

public class KullaniciYetkiModel {

	private int Id;
	private int YetkiId;
	private int KullaniciId;
	private String Sifre;

	public int getId() {
		return Id;
	}

	public void setId(final int id) {
		Id = id;
	}

	public int getYetkiId() {
		return YetkiId;
	}

	public void setYetkiId(final int yetkiId) {
		YetkiId = yetkiId;
	}

	public int getKullaniciId() {
		return KullaniciId;
	}

	public void setKullaniciId(final int kullaniciId) {
		KullaniciId = kullaniciId;
	}

	public String getSifre() {
		return Sifre;
	}

	public void setSifre(final String sifre) {
		Sifre = sifre;
	}

	@Override
	public String toString() {
		return Id + " " + YetkiId + " " + KullaniciId + " " + Sifre;
	}

}
