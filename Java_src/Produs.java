import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.sql.*;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class Produs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private JPanel ProdusPane = new JPanel();
	private JTextField txtProdus;
	private Conexiune c = new Conexiune();
	private int stoc = 0;
	private JTextField txtPret;
	private JTextField txtReviews;
	private JTextField txtMarca;
	private JTextField txtTara;
	private JTextField txtStoc;
	private JSpinner spinner;
	private int cantitate;

	@SuppressWarnings("removal")
	public Produs(int Produs, String Numar_Cont, JTabbedPane z) {
		String Actiune = "SELECT \r\n" + "    produse.Descriere,\r\n" + "    produse.Valoare_Unitara,\r\n"
				+ "    produse.Garantie,\r\n" + "    furnizor.Nume,\r\n" + "    furnizor.Tara,\r\n"
				+ "produse.stoc, \r\n" + "    magazin.stele\r\n" + "FROM\r\n" + "    furnizor\r\n" + "        JOIN\r\n"
				+ "    produse ON furnizor.Id_Furnizor = produse.Id_Furnizor\r\n" + "        LEFT JOIN\r\n"
				+ "    magazin ON magazin.Produs = produse.Descriere\r\n" + "WHERE\r\n" + "    produse.Id_Produs ="
				+ Produs + "\r\n" + "ORDER BY Descriere;";
		ResultSet rez = c.executequery(Actiune);
		ProdusPane.setBackground(Color.GRAY);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		panel.setBackground(new Color(255, 228, 181));
		ProdusPane.setBounds(10, 11, 667, 428);
		ProdusPane.setLayout(null);
		ProdusPane.add(panel);
		panel.setLayout(null);

		JLabel labelProdus = new JLabel("Produs");
		labelProdus.setForeground(Color.CYAN);
		labelProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelProdus.setBounds(10, 11, 55, 36);
		ProdusPane.add(labelProdus);

		JLabel labelPret = new JLabel("Pret");
		labelPret.setForeground(Color.CYAN);
		labelPret.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPret.setBounds(221, 11, 32, 22);
		ProdusPane.add(labelPret);

		JLabel LabelMarca = new JLabel("Marca");
		LabelMarca.setForeground(Color.CYAN);
		LabelMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LabelMarca.setBounds(10, 94, 47, 22);
		ProdusPane.add(LabelMarca);

		JLabel Reviews = new JLabel("Reviews");
		Reviews.setForeground(Color.CYAN);
		Reviews.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Reviews.setBounds(440, 11, 64, 22);
		ProdusPane.add(Reviews);

		JLabel labelTara = new JLabel("Tara");
		labelTara.setForeground(Color.CYAN);
		labelTara.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelTara.setBounds(229, 94, 36, 22);
		ProdusPane.add(labelTara);

		JButton btnNewButton = new JButton("Inchide Fereastra");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z.remove(ProdusPane);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setBounds(440, 318, 177, 48);
		ProdusPane.add(btnNewButton);

		txtProdus = new JTextField();
		txtProdus.setEditable(false);
		txtProdus.setBackground(Color.LIGHT_GRAY);
		txtProdus.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtProdus.setBounds(10, 58, 156, 25);
		ProdusPane.add(txtProdus);
		txtProdus.setColumns(10);

		txtPret = new JTextField();
		txtPret.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPret.setEditable(false);
		txtPret.setColumns(10);
		txtPret.setBackground(Color.LIGHT_GRAY);
		txtPret.setBounds(221, 56, 80, 25);
		ProdusPane.add(txtPret);

		txtReviews = new JTextField();
		txtReviews.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtReviews.setEditable(false);
		txtReviews.setColumns(10);
		txtReviews.setBackground(Color.LIGHT_GRAY);
		txtReviews.setBounds(438, 56, 156, 25);
		ProdusPane.add(txtReviews);

		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMarca.setEditable(false);
		txtMarca.setColumns(10);
		txtMarca.setBackground(Color.LIGHT_GRAY);
		txtMarca.setBounds(5, 119, 156, 25);
		ProdusPane.add(txtMarca);

		txtTara = new JTextField();
		txtTara.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTara.setEditable(false);
		txtTara.setColumns(10);
		txtTara.setBackground(Color.LIGHT_GRAY);
		txtTara.setBounds(216, 119, 156, 25);
		ProdusPane.add(txtTara);

		txtStoc = new JTextField();
		txtStoc.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStoc.setEditable(false);
		txtStoc.setColumns(10);
		txtStoc.setBackground(Color.LIGHT_GRAY);
		txtStoc.setBounds(435, 119, 156, 25);
		ProdusPane.add(txtStoc);

		try {
			txtProdus.setText(rez.getString("Descriere"));
			txtPret.setText(rez.getString("Valoare_Unitara") + " lei");
			txtReviews.setText(rez.getString("Stele"));
			txtMarca.setText(rez.getString("Nume"));
			txtTara.setText(rez.getString("Tara"));
			txtStoc.setText(rez.getString("Stoc"));
			stoc = rez.getInt("Stoc");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel labelStoc = new JLabel("Stoc");
		labelStoc.setForeground(Color.CYAN);
		labelStoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelStoc.setBounds(440, 94, 36, 22);
		ProdusPane.add(labelStoc);

		spinner = new JSpinner();
		spinner.setSize(new Dimension(0, 5));
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setForeground(Color.RED);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setBackground(Color.BLUE);
		spinner.setBounds(440, 167, 154, 36);
		ProdusPane.add(spinner);
		JButton Comanda = new JButton("Comanda Produs");
		Comanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet res = c.executequery("select stoc from produse where Id_Produs=" + Produs);
				cantitate = (int) spinner.getValue();
				JFrame message = new JFrame();
				String ActiuneComanda = "call comandare(? ,?, ?);";
				if (stoc <= 0 || cantitate > stoc)
					JOptionPane.showMessageDialog(message, "Stoc epuizat sau  excede stocul curent\n", "eroare",
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int newstoc = res.getInt(1);
						CallableStatement pstm = c.getConect().prepareCall(ActiuneComanda);
						pstm.setString(1, Numar_Cont);
						pstm.setInt(2, Produs);
						pstm.setInt(3, cantitate);
						pstm.execute();
						txtStoc.setText(String.valueOf(newstoc));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		Comanda.setBackground(new Color(0, 191, 255));
		Comanda.setBounds(440, 223, 154, 64);
		ProdusPane.add(Comanda);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 183, 362, 201);
		ProdusPane.add(scrollPane);
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("Review Persoane");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(10, 158, 113, 19);
		ProdusPane.add(lblNewLabel);
		Actiune = "select persoana.Nume,persoana.Prenume ,review.Descriere,review.Stele from persoana join review\r\n"
				+ "on persoana.Numar_Cont=review.Numar_Cont\r\n" + "where review.Id_Produs=" + Produs;
		rez = c.executequery(Actiune);
		try {
			do {
				if (rez.getString("Descriere") != null) {
					/*
					 * System.out.println(rez.getString("Nume") + " " + rez.getString("Prenume") +
					 * ":\t" + rez.getString("Stele") + " stele  \nZice:" +
					 * rez.getString("Descriere") + "\n");
					 */
					textArea.setText(textArea.getText() + "\n" + rez.getString("Nume") + " " + rez.getString("Prenume")
							+ ":\t" + rez.getString("Stele") + " stele\nZice: " + rez.getString("Descriere") + "\n");
				} else {
					/*
					 * System.out.println(rez.getString("Nume") + " " + rez.getString("Prenume") +
					 * ":\t" + rez.getString("Stele") + " stele");
					 */
					textArea.setText(textArea.getText() + "\n" + rez.getString("Nume") + " " + rez.getString("Prenume")
							+ ":\t" + rez.getString("Stele") + " stele");

				}
			} while (rez.next());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public JPanel getProdusPane() {
		return ProdusPane;
	}

	public void setProdusPane(JPanel produsPane) {
		ProdusPane = produsPane;
	}
}
