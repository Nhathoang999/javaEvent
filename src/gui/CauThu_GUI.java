package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.VolatileImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import dao.CauThu_DAO;
import dao.ViTriDau_DAO;
import entity.CauThu;
import entity.ViTriDau;

public class CauThu_GUI extends JFrame  implements ActionListener, MouseListener {

	private static final long serialVersionUID = -1554680235689968471L;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnXoa;
	private JButton btnKetThuc;

	private DefaultTableModel dataModel;
	private JTable table;

	private JScrollPane scroll;

	private JComboBox<String> cboViTriThiDau;
	private JTextField txtMaCauThu;
	private JTextField txtTenCauThu;
	private JTextField txtTuoi;

	private JButton btnLoc;

	private CauThu_DAO ct_dao;

	private ViTriDau_DAO vt_dao;

	public CauThu_GUI() {
		
		ConnectDB.getInstance().connect();
		ct_dao = new CauThu_DAO();
		vt_dao = new ViTriDau_DAO();
		
		
		setTitle("Bài thi cuối kỳ - TL HSK Java - HK2 (2020-2021)");
		setSize(630, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Box b, b1, b2, b3, b4, b5, b6, b7, b8;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());

		b.add(Box.createVerticalStrut(10));
		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		JLabel lblTieuDe, lblMaVDV, lblTenVDV, lblViTri, lblTuoi;
		b1.add(lblTieuDe = new JLabel("-THÔNG TIN CẦU THỦ- ", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTieuDe.setForeground(Color.BLUE);

		b2.add(lblMaVDV = new JLabel("  Mã số cầu thủ:  ", JLabel.RIGHT));
		b2.add(txtMaCauThu = new JTextField());
		b2.add(Box.createHorizontalStrut(50));
		b3.add(lblTenVDV = new JLabel("Tên cầu thủ:  ", JLabel.RIGHT));
		b3.add(txtTenCauThu = new JTextField());
		b3.add(Box.createHorizontalStrut(50));
		b4.add(lblViTri = new JLabel("Vị trí thi đấu:  ", JLabel.RIGHT));
		b4.add(cboViTriThiDau = new JComboBox<String>());
		
		
		
		
		b4.add(Box.createHorizontalStrut(300));

		b5.add(lblTuoi = new JLabel("Tuổi:  ", JLabel.RIGHT));
		b5.add(txtTuoi = new JTextField());
		b5.add(Box.createHorizontalStrut(50));

		DefaultComboBoxModel<String> dataModelLop = new DefaultComboBoxModel<String>();

		cboViTriThiDau.setModel(dataModelLop);

		lblViTri.setPreferredSize(lblMaVDV.getPreferredSize());
		lblTenVDV.setPreferredSize(lblMaVDV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaVDV.getPreferredSize());

		b6.add(Box.createHorizontalStrut(40));
		b6.add(btnThem = new JButton("Thêm Mới "));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnLuu = new JButton("Lưu "));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnXoa = new JButton("Xóa"));
		b6.add(Box.createHorizontalStrut(50));
		b6.add(btnKetThuc = new JButton("Kết Thúc"));

		String[] tieuDe = { "Mã Số", "Tên cầu thủ", "Tuổi", "vị trí thi đấu" };
		b7.add(scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0))));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách cầu thủ:"));

		JLabel lblName;
		b8.add(lblName = new JLabel("Họ tên sv: ................massv:22667391"));
		lblName.setFont(new Font("Times", Font.ITALIC, 12));
		lblName.setForeground(Color.RED);
		b8.add(Box.createHorizontalStrut(50));
		b8.add(btnLoc= new JButton("   Lọc danh sách cầu thủ theo vị trí thi đấu "));
		btnLoc.setFont(new Font("Times New Roman", Font.ITALIC,14 ));
		btnLoc.setForeground(Color.BLUE);
		table.addMouseListener(this);
		btnKetThuc.addActionListener(this);
		btnLoc.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		table.addMouseListener(this);
		
		filltable();
		udcomboB();
	}

	public void udcomboB() {
		ViTriDau_DAO ds = new ViTriDau_DAO();
		List<ViTriDau> list = ds.getAlltbVTD();
		for(ViTriDau v : list)
			cboViTriThiDau.addItem(v.getMaViTri());
	}
	
	public void filltable() {
		CauThu_DAO ds = new CauThu_DAO();
		List<CauThu> list = ds.getAlltbCT();
		for(CauThu c : list) {
			String [] rowData = {c.getMaCauThu(), c.getTenCauThu(), c.getTuoi()+"", c.getViTriDau().getMaViTri()};
			dataModel.addRow(rowData);
		}
		table.setModel(dataModel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnKetThuc))
			thoat();
		if(o.equals(btnThem))
			xoatrang();
		if(o.equals(btnLoc)) 
			loc();
		if(o.equals(btnLuu))
			them();
		if(o.equals(btnXoa))
			xoa();
	}
	
	
	
	public void xoa() {
		int row = table.getSelectedRow();
		if(row<0) {
			JOptionPane.showMessageDialog(this, "chon hang");
			return;
		}
		int xn = JOptionPane.showConfirmDialog(this, "Ban co chac k", "xac nhan", JOptionPane.YES_NO_OPTION);
		if(xn == JOptionPane.YES_OPTION) {
			String maCT = (String) table.getValueAt(row, 0);
			if(ct_dao.delete(maCT)) {
				dataModel.removeRow(row);
				JOptionPane.showMessageDialog(this, "xoa thanh cong");
				xoatrang();
			}else {
			JOptionPane.showMessageDialog(this, "xxoa that bai");
			}
		}
		
	}
	public void them() {
		String ma = txtMaCauThu.getText();
		String ten = txtTenCauThu.getText();
		String tuoiStr = txtTuoi.getText();
		String vt = cboViTriThiDau.getSelectedItem().toString();
		
		if(!ma.matches("^VDV\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "ma phai....");
			txtMaCauThu.requestFocus();
			return;
		}
		
		if(!ten.matches("[a-zA-Z0-9 ]+$")) {
			JOptionPane.showMessageDialog(this, "ten phai....");
			txtTenCauThu.requestFocus();
			return;
		}
		
		int tuoi;
		try {
			tuoi = Integer.parseInt(tuoiStr);
			if(tuoi <18 || tuoi >23) {
				JOptionPane.showMessageDialog(this, "k hop le");
				txtTuoi.requestFocus();
				return;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "tuoi la s nguyen");
			txtTuoi.requestFocus();
			return;
		}
		
		
		ViTriDau vitr = new ViTriDau(vt);
		CauThu ct = new CauThu(ma, ten, tuoi, vitr);
		try {
			ct_dao.create(ct);
			dataModel.addRow(new Object [] {ct.getMaCauThu(), ct.getTenCauThu(), ct.getTuoi(), ct.getViTriDau().getMaViTri()});
			JOptionPane.showMessageDialog(this, "them thanh cong");
			xoatrang();
		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "trung");
		}
		
	}
	
	public void loc() {
		String vtri = cboViTriThiDau.getSelectedItem().toString();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		
		List<CauThu> dsct = ct_dao.getAlltbCT();
		for(CauThu ct : dsct) {
			if(ct.getViTriDau().getMaViTri().equalsIgnoreCase(vtri)) {
				model.addRow(new Object [] {ct.getMaCauThu(), ct.getTenCauThu(), ct.getTuoi(), ct.getViTriDau().getMaViTri()});
			}
		}
		if(model.getRowCount()==0) {
			JOptionPane.showMessageDialog(this, "khong 1 ai");
		}
	}

	public void xoatrang() {
		txtMaCauThu.setText("");
		txtTenCauThu.setText("");
		txtTuoi.setText("");
		cboViTriThiDau.setSelectedIndex(0);;
		txtMaCauThu.requestFocus();
	}
	
	public void thoat() {
		int xn = JOptionPane.showConfirmDialog(this, "Ban co chac k", "xac nhan", JOptionPane.YES_NO_OPTION);
		if(xn == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaCauThu.setText(dataModel.getValueAt(row, 0).toString());
		txtTenCauThu.setText(dataModel.getValueAt(row, 1).toString());
		txtTuoi.setText(dataModel.getValueAt(row, 2).toString());
		cboViTriThiDau.setSelectedItem(dataModel.getValueAt(row, 3).toString());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
