package tr.org.sd.dal;

public class AracYakitModel {

	private int Id;
	private String Tarih;
	private String Plaka;
	private String YakitTuru;
	private String Miktar;
	private String OdemeTuru;
	private String BelgeNo;
	private String AktifKm;
	private Double Tutar;

	public int getId() {
		return Id;
	}

	public void setId(final int id) {
		Id = id;
	}

	public String getTarih() {
		return Tarih;
	}

	public void setTarih(final String tarih) {
		Tarih = tarih;
	}

	public String getPlaka() {
		return Plaka;
	}

	public void setPlaka(final String plaka) {
		Plaka = plaka;
	}

	public String getYakitTuru() {
		return YakitTuru;
	}

	public void setYakitTuru(final String yakitTuru) {
		YakitTuru = yakitTuru;
	}

	public String getMiktar() {
		return Miktar;
	}

	public void setMiktar(final String miktar) {
		Miktar = miktar;
	}

	public String getOdemeTuru() {
		return OdemeTuru;
	}

	public void setOdemeTuru(final String odemeTuru) {
		OdemeTuru = odemeTuru;
	}

	public String getBelgeNo() {
		return BelgeNo;
	}

	public void setBelgeNo(final String belgeNo) {
		BelgeNo = belgeNo;
	}

	public String getAktifKm() {
		return AktifKm;
	}

	public void setAktifKm(final String aktifKm) {
		AktifKm = aktifKm;
	}

	public Double getTutar() {
		return Tutar;
	}

	public void setTutar(final Double tutar) {
		Tutar = tutar;
	}

	@Override
	public String toString() {
		return Plaka;
	}

	public Object[] getVeriler() {
		final Object veriler[] = { Id, Tarih, Plaka, YakitTuru, Miktar, OdemeTuru, BelgeNo, AktifKm, Tutar };

		return veriler;
	}

}
