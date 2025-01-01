package entity;

public class CauThu {
	private String maCauThu;
	private String tenCauThu;
	private int tuoi;
	private ViTriDau ViTriDau;
	public CauThu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CauThu(String maCauThu, String tenCauThu, int tuoi, entity.ViTriDau viTriDau) {
		super();
		this.maCauThu = maCauThu;
		this.tenCauThu = tenCauThu;
		this.tuoi = tuoi;
		ViTriDau = viTriDau;
	}
	
	
	
	
	public CauThu(String maCauThu) {
		super();
		this.maCauThu = maCauThu;
	}
	@Override
	public String toString() {
		return "CauThu [maCauThu=" + maCauThu + ", tenCauThu=" + tenCauThu + ", tuoi=" + tuoi + ", ViTriDau=" + ViTriDau
				+ "]";
	}
	public String getMaCauThu() {
		return maCauThu;
	}
	public void setMaCauThu(String maCauThu) {
		this.maCauThu = maCauThu;
	}
	public String getTenCauThu() {
		return tenCauThu;
	}
	public void setTenCauThu(String tenCauThu) {
		this.tenCauThu = tenCauThu;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public ViTriDau getViTriDau() {
		return ViTriDau;
	}
	public void setViTriDau(ViTriDau viTriDau) {
		ViTriDau = viTriDau;
	}
	
	
}
