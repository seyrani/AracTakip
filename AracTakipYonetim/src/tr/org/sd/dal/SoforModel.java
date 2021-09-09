package tr.org.sd.dal;

public class SoforModel {

	private int Id;
	private String AdSoyad;

	public int getId() {
		return Id;
	}

	public void setId(final int id) {
		Id = id;
	}

	public String getAdSoyad() {
		return AdSoyad;
	}

	public void setAdSoyad(final String adSoyad) {
		AdSoyad = adSoyad;
	}

	@Override
	public String toString() {
		return AdSoyad;
	}

}
