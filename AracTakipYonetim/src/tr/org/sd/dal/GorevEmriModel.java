package tr.org.sd.dal;

public class GorevEmriModel {

	private int Id;
	private String GorevTarihi;
	private String GorevliPersonel;
	private String GorevTuru;
	private String GidilecekYer;
	private int SoforId;
	private int PlakaId;
	private String CikisSaati;
	private int CikisKm;
	private String DonusSaati;
	private int DonusKm;
	private int YapilanKm;
	private String AdSoyad;
	private String Plaka;

	public GorevEmriModel() {
		// TODO Auto-generated constructor stub
	}

	public GorevEmriModel(final int id, final String gorevTarihi, final String gorevliPersonel, final String gorevTuru,
			final String gidilecekYer, final int soforId, final int plakaId, final String cikisSaati, final int cikisKm,
			final String donusSaati, final int donusKm, final int yapilanKm, final String adSoyad, final String plaka) {
		super();
		Id = id;
		GorevTarihi = gorevTarihi;
		GorevliPersonel = gorevliPersonel;
		GorevTuru = gorevTuru;
		GidilecekYer = gidilecekYer;
		SoforId = soforId;
		PlakaId = plakaId;
		CikisSaati = cikisSaati;
		CikisKm = cikisKm;
		DonusSaati = donusSaati;
		DonusKm = donusKm;
		YapilanKm = yapilanKm;
		AdSoyad = adSoyad;
		Plaka = plaka;
	}

	public int getId() {
		return Id;
	}

	public void setId(final int id) {
		Id = id;
	}

	public String getGorevTarihi() {
		return GorevTarihi;
	}

	public void setGorevTarihi(final String gorevTarihi) {
		GorevTarihi = gorevTarihi;
	}

	public String getGorevliPersonel() {
		return GorevliPersonel;
	}

	public void setGorevliPersonel(final String gorevliPersonel) {
		GorevliPersonel = gorevliPersonel;
	}

	public String getGorevTuru() {
		return GorevTuru;
	}

	public void setGorevTuru(final String gorevTuru) {
		GorevTuru = gorevTuru;
	}

	public String getGidilecekYer() {
		return GidilecekYer;
	}

	public void setGidilecekYer(final String gidilecekYer) {
		GidilecekYer = gidilecekYer;
	}

	public int getSoforId() {
		return SoforId;
	}

	public void setSoforId(final int soforId) {
		SoforId = soforId;
	}

	public int getPlakaId() {
		return PlakaId;
	}

	public void setPlakaId(final int plakaId) {
		PlakaId = plakaId;
	}

	public String getCikisSaati() {
		return CikisSaati;
	}

	public void setCikisSaati(final String cikisSaati) {
		CikisSaati = cikisSaati;
	}

	public int getCikisKm() {
		return CikisKm;
	}

	public void setCikisKm(final int cikisKm) {
		CikisKm = cikisKm;
	}

	public String getDonusSaati() {
		return DonusSaati;
	}

	public void setDonusSaati(final String donusSaati) {
		DonusSaati = donusSaati;
	}

	public int getDonusKm() {
		return DonusKm;
	}

	public void setDonusKm(final int donusKm) {
		DonusKm = donusKm;
	}

	public int getYapilanKm() {
		return YapilanKm;
	}

	public void setYapilanKm(final int yapilanKm) {
		YapilanKm = yapilanKm;
	}

	public String getAdSoyad() {
		return AdSoyad;
	}

	public void setAdSoyad(final String adSoyad) {
		AdSoyad = adSoyad;
	}

	public String getPlaka() {
		return Plaka;
	}

	public void setPlaka(final String plaka) {
		Plaka = plaka;
	}

	@Override
	public String toString() {

		return GorevTarihi + " " + GorevliPersonel + " " + GorevTuru + " " + GidilecekYer + " " + AdSoyad + " " + Plaka
				+ " " + CikisSaati + " " + DonusSaati + " " + CikisKm + " " + DonusKm + " " + YapilanKm;
	}

	public Object[] getVeriler() {
		final Object veriler[] = { Id, GorevTarihi, GorevliPersonel, GorevTuru, GidilecekYer, AdSoyad, Plaka,
				CikisSaati, DonusSaati, CikisKm, DonusKm, YapilanKm };

		return veriler;
	}

}
