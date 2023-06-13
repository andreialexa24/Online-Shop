import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class test extends JFrame {

	private JPanel contentPane;
	private Conexiune c = new Conexiune();
	private JTable table;
	private String Numar_Cont;
	private JTabbedPane tabbedPane;
	private int n;
	private DefaultTableModel model;

	/*
	 * public static void main(String[] args) throws Exception { test q = new
	 * test("sda", "sad"); q.setVisible(true);
	 * 
	 * }
	 */
	public test(String username, String Password) {
		ResultSet rez = null;

		rez = c.executequery("Select Numar_Cont from client where client.Username=\"" + username + "\";");
		try {
			Numar_Cont = rez.getString("Numar_Cont");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String Actiune = "select count(*) from proiect.magazin ;";
		rez = c.executequery(Actiune);
		n = 0;
		try {
			n = rez.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] Coloane = { "Produs", "Pret", "Companie", "Review" };
		model = new DefaultTableModel(Coloane, 0);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 667, 428);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Magazin", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(21, 11, 363, 364);
		panel.add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JButton Cont = new JButton("Vizualizare Cont");
		Cont.setBackground(new Color(100, 149, 237));
		Cont.setBounds(447, 43, 155, 41);
		Cont.setFont(new Font("Tahoma", Font.BOLD, 13));
		Cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cont viz = new Cont(username, Password, tabbedPane);
				tabbedPane.add("Cont", viz.getContPanel());
			}
		});
		panel.add(Cont);

		JButton Produs = new JButton("Vizualizare Produs");
		Produs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object s = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				ResultSet produs = c
						.executequery("Select Id_Produs from Produse where Descriere=\"" + s.toString() + "\";");
				int Id = 0;
				try {
					Id = produs.getInt("Id_Produs");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Produs p = new Produs(Id, Numar_Cont, tabbedPane);
				tabbedPane.add("Produs", p.getProdusPane());
			}
		});
		Produs.setBackground(new Color(100, 149, 237));
		Produs.setFont(new Font("Tahoma", Font.BOLD, 13));
		Produs.setBounds(447, 132, 169, 41);
		panel.add(Produs);

		JButton Reviewbtn = new JButton("Scrie Review");
		Reviewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object s = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());

				ResultSet produs = c
						.executequery("Select Id_Produs from Produse where Descriere=\"" + s.toString() + "\";");
				int Id = 0;
				try {
					Id = produs.getInt("Id_Produs");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Review r = new Review(Numar_Cont, Id, model, table);
				r.setVisible(true);
			}
		});
		Reviewbtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		Reviewbtn.setBackground(new Color(100, 149, 237));
		Reviewbtn.setBounds(447, 213, 157, 41);
		panel.add(Reviewbtn);

		JButton Log_Out = new JButton("Deconectare");
		Log_Out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Log_Out.setFont(new Font("Tahoma", Font.BOLD, 13));
		Log_Out.setBackground(new Color(100, 149, 237));
		Log_Out.setBounds(447, 296, 157, 41);
		panel.add(Log_Out);
		Actiune = "select produs,companie,pret,stele from proiect.magazin;";
		rez = c.executequery(Actiune);
		for (int i = 0; i < n; i++) {
			try {
				model.insertRow(i, new Object[] { rez.getString("produs"), rez.getString("pret") + " lei",
						rez.getString("companie"), rez.getFloat("Stele") });
				rez.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
