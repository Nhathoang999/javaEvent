package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import entity.ViTriDau;

public class ViTriDau_DAO {

	public ArrayList<ViTriDau> getAlltbVTD(){
		ArrayList<ViTriDau> dsvttd = new ArrayList<ViTriDau>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ViTriThiDau";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maVT = rs.getString(1);
				String tenVT = rs.getString(2);
				ViTriDau v = new ViTriDau(maVT, tenVT);
				dsvttd.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsvttd;
	}
}
