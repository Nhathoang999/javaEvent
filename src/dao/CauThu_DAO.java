package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import entity.CauThu;
import entity.ViTriDau;

public class CauThu_DAO {

	private ArrayList<CauThu> dsct;
	private CauThu ct;

	public CauThu_DAO() {
		dsct = new ArrayList<CauThu>();
		ct = new CauThu();
		
	}
	
	public ArrayList<CauThu> getAlltbCT(){
		dsct.clear();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from CauThu";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maCT = rs.getString(1);
				String tenCT = rs.getString(2);
				int siso = rs.getInt(3);
				ViTriDau vTri = new ViTriDau(rs.getString(4));
				CauThu c = new CauThu(maCT, tenCT, siso, vTri);
				dsct.add(c);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsct;
	}
	
	
	public boolean create(CauThu ct) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into CauThu values(?,?,?,?)");
			stmt.setString(1, ct.getMaCauThu());
			stmt.setString(2, ct.getTenCauThu());
			stmt.setInt(3, ct.getTuoi());
			stmt.setString(4, ct.getViTriDau().getMaViTri());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n >0;
	}
	
	public boolean delete(String maCauThu) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from CauThu where maCauThu=?");
			stmt.setString(1, maCauThu);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<CauThu> getCauThutheoViTri(String maVT){
		ArrayList<CauThu> dsct = new ArrayList<CauThu>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from CauThu where maViTri=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maVT);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCT = rs.getString(1);
				String tenCT = rs.getString(2);
				int siso = rs.getInt(3);
				ViTriDau vTri = new ViTriDau(rs.getString(4));
				CauThu c = new CauThu(maCT, tenCT, siso, vTri);
				dsct.add(c);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsct;
		
	}
}
