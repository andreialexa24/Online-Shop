import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cont extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private JPanel ContPanel = new JPanel();
	private JTextField txtNume;
	private JTextField txtPrenume;
	private JTextField txtData;
	private JTextField txtAdresa;
	private Conexiune c = new Conexiune();

	public Cont(String username, String password, JTabbedPane z) {
		ResultSet rez = null;
		String numarCont = null;
		String Actiune = "Select Numar_Cont from proiect.client where Username=\"" + username + "\" and Parola=\""
				+ password + "\";";
		rez = c.executequery(Actiune);
		try {
			numarCont = rez.getString("Numar_Cont");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String Actiune2 = "SELECT persoana.Nume,persoana.Prenume,persoana.Data_Nasterii "
				+ "from proiect.persoana where persoana.Numar_Cont=\"" + numarCont + "\";";
		rez = c.executequery(Actiune2);

		ContPanel.setBackground(Color.GRAY);
		ContPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 181));
		panel.setBounds(10, 11, 647, 405);
		ContPanel.add(panel);
		panel.setLayout(null);

		JLabel labelPrenume = new JLabel("Prenume");
		labelPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPrenume.setBounds(190, 25, 79, 25);
		panel.add(labelPrenume);

		JLabel labelNume = new JLabel("Nume");
		labelNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNume.setBounds(10, 25, 52, 25);
		panel.add(labelNume);

		JLabel labelData = new JLabel("Data Nasterii\r\n");
		labelData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelData.setBounds(365, 25, 114, 25);
		panel.add(labelData);

		JLabel labelAdresa = new JLabel("Adresa");
		labelAdresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAdresa.setBounds(10, 151, 61, 25);
		panel.add(labelAdresa);

		txtNume = new JTextField();
		txtNume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNume.setBackground(new Color(250, 235, 215));
		txtNume.setEditable(false);
		txtNume.setBounds(10, 69, 136, 25);
		panel.add(txtNume);
		txtNume.setColumns(10);

		txtPrenume = new JTextField();
		txtPrenume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrenume.setEditable(false);
		txtPrenume.setColumns(10);
		txtPrenume.setBackground(new Color(250, 235, 215));
		txtPrenume.setBounds(190, 69, 136, 25);
		panel.add(txtPrenume);

		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBackground(new Color(250, 235, 215));
		txtData.setBounds(365, 69, 136, 25);
		panel.add(txtData);

		txtAdresa = new JTextField();
		txtAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAdresa.setEditable(false);
		txtAdresa.setColumns(10);
		txtAdresa.setBackground(new Color(250, 235, 215));
		txtAdresa.setBounds(10, 187, 316, 33);
		panel.add(txtAdresa);

		try {
			txtNume.setText(rez.getString("Nume"));
			txtPrenume.setText(rez.getString("Prenume"));
			txtData.setText(rez.getString("Data_Nasterii"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Actiune3 = "SELECT adresa.Oras,adresa.Strada,adresa.Bloc,adresa.Apartament "
				+ "from proiect.adresa where adresa.Numar_Cont=\"" + numarCont + "\";";
		String adresa = null;
		try {
			rez = c.executequery(Actiune3);
			adresa = rez.getString("Oras") + ", " + rez.getString("Strada") + ", " + rez.getString("Bloc") + ", "
					+ rez.getString("Apartament");
			txtAdresa.setText(adresa);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		JButton btnNewButton = new JButton("Inchide Fereastra");
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(365, 187, 151, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z.remove(ContPanel);
			}
		});
		panel.add(btnNewButton);

		JLabel labelIstoric = new JLabel("Istoric");
		labelIstoric.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIstoric.setBounds(10, 243, 55, 25);
		panel.add(labelIstoric);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 267, 516, 115);
		panel.add(scrollPane);

		JTextArea txtIstoric = new JTextArea();
		scrollPane.setViewportView(txtIstoric);
		txtIstoric.setBackground(new Color(255, 165, 0));
		txtIstoric.setEditable(false);

		String Actiune4 = "SELECT comanda.Cantitate,comanda.Data_Comanda,produse.Descriere \r\n"
				+ "from comanda join produse on produse.Id_Produs=comanda.Id_Produs\r\n"
				+ "join client on client.Numar_Cont=comanda.Numar_Cont and " + "comanda.Numar_Cont=\"" + numarCont
				+ "\";";
		rez = c.executequery(Actiune4);
		try {
			do
				txtIstoric.setText(txtIstoric.getText() + "\n" + "Produs:  " + rez.getString("Descriere")
						+ " Cantitate:  " + rez.getInt("Cantitate") + " Data-" + rez.getString("Data_Comanda") + "\n");
			while (rez.next());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public JPanel getContPanel() {
		return ContPanel;
	}

}
