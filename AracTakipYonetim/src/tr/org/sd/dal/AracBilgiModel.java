package tr.org.sd.dal;

public class AracBilgiModel {

	private int Id;
	private String Plaka;
	private String AracTuru;
	private String Marka;
	private String Model;
	private String ModelYil;
	private String BaslamaTarihi;
	private String BitisTarihi;
	private String GelenKm;
	private String SaseNo;
	private String Durum;

	public int getId() {
		return Id;
	}

	public String getSaseNo() {
		return SaseNo;
	}

	public void setSaseNo(final String saseNo) {
		SaseNo = saseNo;
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

	public String getAracTuru() {
		return AracTuru;
	}

	public void setAracTuru(final String aracTuru) {
		AracTuru = aracTuru;
	}

	public String getMarka() {
		return Marka;
	}

	public void setMarka(final String marka) {
		Marka = marka;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(final String model) {
		Model = model;
	}

	public String getModelYil() {
		return ModelYil;
	}

	public void setModelYil(final String modelYil) {
		ModelYil = modelYil;
	}

	public String getBaslamaTarihi() {
		return BaslamaTarihi;
	}

	public void setBaslamaTarihi(final String baslamaTarihi) {
		BaslamaTarihi = baslamaTarihi;
	}

	public String getBitisTarihi() {
		return BitisTarihi;
	}

	public void setBitisTarihi(final String bitisTarihi) {
		BitisTarihi = bitisTarihi;
	}

	public String getGelenKm() {
		return GelenKm;
	}

	public void setGelenKm(final String gelenKm) {
		GelenKm = gelenKm;
	}

	public String getDurum() {
		return Durum;
	}

	public void setDurum(final String durum) {
		Durum = durum;
	}

	@Override
	public String toString() {
		return Plaka;
	}

//	@Override
//	public String toString() {
//
//		return Plaka + "" + AracTuru + "" + Marka + "" + Model + "" + ModelYil + "" + BaslamaTarihi + "" + GelenKm + ""
//				+ Durum;
//	}

	public Object[] getVeriler() {
		final Object veriler[] = { Id, Plaka, AracTuru, Marka, Model, ModelYil, BaslamaTarihi, BitisTarihi, GelenKm,
				SaseNo, Durum };

		return veriler;
	}

}
