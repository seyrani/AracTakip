package tr.org.sd.dal;

public class KullaniciModel {

	private int Id;
	private String AdiSoyadi;
	private String Eposta;

	public int getId() {
		return Id;
	}

	public void setId(final int id) {
		Id = id;
	}

	public String getAdiSoyadi() {
		return AdiSoyadi;
	}

	public void setAdiSoyadi(final String adiSoyadi) {
		AdiSoyadi = adiSoyadi;
	}

	public String getEposta() {
		return Eposta;
	}

	public void setEposta(final String eposta) {
		Eposta = eposta;
	}

	@Override
	public String toString() {
		return AdiSoyadi;
	}

}
