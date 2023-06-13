import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
	private JFrame Logare = new JFrame();
	private JTextField textnume;
	private JPasswordField password;
	private String username;
	private String parola;
	private Conexiune c = new Conexiune();

	public Login() {
		Logare.setResizable(false);
		Logare.setTitle("Autentificare\r\n");
		Logare.setBackground(Color.LIGHT_GRAY);
		Logare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Logare.setBounds(100, 100, 431, 303);

		JPanel pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Logare.setContentPane(pane);
		Logare.getContentPane().setLayout(null);

		JLabel Nume = new JLabel("Nume");
		Nume.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nume.setBounds(43, 30, 41, 20);
		Logare.getContentPane().add(Nume);

		textnume = new JTextField();
		textnume.setText("Martin Hannah");
		textnume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textnume.setBounds(43, 50, 136, 25);
		Logare.getContentPane().add(textnume);
		textnume.setColumns(10);

		JLabel Parola = new JLabel("Parola\r\n");
		Parola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Parola.setBounds(43, 90, 44, 20);
		Logare.getContentPane().add(Parola);

		password = new JPasswordField();
		password.setText("LGL12QTJ7TH");
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		password.setBounds(43, 110, 136, 31);
		Logare.getContentPane().add(password);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Administrator", "Client" }));
		comboBox.setBounds(241, 50, 121, 25);
		// Logare.getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("Categorie");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(241, 30, 67, 20);
		// Logare.getContentPane().add(lblNewLabel);

		JButton conectare = new JButton("Conectare\r\n");
		conectare.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		conectare.setBackground(new Color(51, 153, 255));
		conectare.setBounds(43, 194, 136, 43);
		conectare.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				JFrame message = new JFrame();
				try {
					if (c.verificareclient(textnume.getText(), password.getText()) == false) {
						JOptionPane.showMessageDialog(message, "Parola sau numele introdus nu este corect\n", "eroare",
								JOptionPane.ERROR_MESSAGE);
					} else {
						username = textnume.getText();
						parola = password.getText();
						test frame = new test(username, parola);
						frame.setVisible(true);
					}

					{
						// System.out.println("Conectare reusita");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Logare.getContentPane().add(conectare);

		JButton Inregistram = new JButton("Inregistrare");
		Inregistram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inregistrare q = new Inregistrare();
				q.setVisible(true);
			}
		});
		Inregistram.setBackground(new Color(51, 153, 255));
		Inregistram.setBounds(241, 194, 136, 43);
		pane.add(Inregistram);

		Logare.setVisible(true);
	}

}