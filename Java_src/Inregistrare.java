import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Inregistrare extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNume;
	private JTextField txtPrenume;
	private JTextField txtData;
	private JTextField txtUser;
	private JTextField txtParola;
	private Conexiune c = new Conexiune();
	private String cont;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Inregistrare() {
		Random q = new Random();
		int m = q.nextInt() % 10;
		if (m < 0)
			m = -m;
		cont = String.valueOf(m);
		for (int i = 1; i < 8; i++) {
			m = q.nextInt() % 10;
			if (m < 0)
				m = -m;
			cont = cont.concat(String.valueOf(m));
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNume = new JLabel("Nume\r\n");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNume.setBounds(10, 11, 49, 14);
		contentPane.add(lblNume);

		JLabel lblPrenume = new JLabel("Prenume\r\n");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrenume.setBounds(145, 9, 60, 19);
		contentPane.add(lblPrenume);

		JLabel lblData = new JLabel("Data Nasterii\r\n");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setBounds(271, 9, 87, 19);
		contentPane.add(lblData);

		JLabel lbluser = new JLabel("Username");
		lbluser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbluser.setBounds(10, 117, 67, 19);
		contentPane.add(lbluser);

		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParola.setBounds(145, 117, 41, 19);
		contentPane.add(lblParola);

		txtNume = new JTextField();
		txtNume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNume.setBounds(10, 48, 87, 25);
		contentPane.add(txtNume);
		txtNume.setColumns(10);

		txtPrenume = new JTextField();
		txtPrenume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrenume.setColumns(10);
		txtPrenume.setBounds(143, 48, 87, 25);
		contentPane.add(txtPrenume);

		txtData = new JTextField();
		txtData.setText("y-m-d\r\n");
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtData.setColumns(10);
		txtData.setBounds(271, 48, 87, 25);
		contentPane.add(txtData);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setColumns(10);
		txtUser.setBounds(10, 158, 87, 25);
		contentPane.add(txtUser);

		txtParola = new JTextField();
		txtParola.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtParola.setColumns(10);
		txtParola.setBounds(145, 158, 87, 25);
		contentPane.add(txtParola);

		JButton Inregistrare = new JButton("Inregistrare\r\n");
		Inregistrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement pstm = c.getConect().prepareCall("call create_user(?,?,?,?,?,?);");
					pstm.setString(1, txtUser.getText());
					pstm.setString(2, txtParola.getText());
					pstm.setString(3, cont);
					pstm.setString(4, txtData.getText());
					pstm.setString(5, txtNume.getText());
					pstm.setString(6, txtPrenume.getText());
					pstm.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		Inregistrare.setBackground(new Color(30, 144, 255));
		Inregistrare.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Inregistrare.setBounds(271, 161, 111, 27);
		contentPane.add(Inregistrare);
	}
}
