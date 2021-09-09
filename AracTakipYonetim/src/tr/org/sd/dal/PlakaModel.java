package tr.org.sd.dal;

public class PlakaModel {

	private int Id;
	private String Plaka;

	public int getId() {
		return Id;
	}

	public void setId(final int id) {
		Id = id;
	}

	public String getPlaka() {
		return Plaka;
	}

	public void setPlaka(final String plaka) {
		Plaka = plaka;
	}

	@Override
	public String toString() {
		return Plaka;
	}
}
