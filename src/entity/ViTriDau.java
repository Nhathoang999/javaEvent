package entity;

public class ViTriDau {
	private String maViTri;
	private String tenViTri;
	public ViTriDau() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViTriDau(String maViTri, String tenViTri) {
		super();
		this.maViTri = maViTri;
		this.tenViTri = tenViTri;
	}
	public ViTriDau(String maViTri) {
		super();
		this.maViTri = maViTri;
	}
	public String getMaViTri() {
		return maViTri;
	}
	public void setMaViTri(String maViTri) {
		this.maViTri = maViTri;
	}
	public String getTenViTri() {
		return tenViTri;
	}
	public void setTenViTri(String tenViTri) {
		this.tenViTri = tenViTri;
	}
	@Override
	public String toString() {
		return "ViTriDau [maViTri=" + maViTri + ", tenViTri=" + tenViTri + "]";
	}
	
	
}
