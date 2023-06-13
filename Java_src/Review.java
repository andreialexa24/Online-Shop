import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Review extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Conexiune c = new Conexiune();
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Review(String NumarCont, int Produs, DefaultTableModel model, JTable table) {
		ResultSet rez = c.executequery("Select Descriere from produse where Id_Produs=" + Produs);
		try {
			setTitle(rez.getString("Descriere"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Stelelabel = new JLabel("Stele");
		Stelelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Stelelabel.setForeground(Color.RED);
		Stelelabel.setBounds(10, 20, 33, 19);
		contentPane.add(Stelelabel);

		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.RED);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		comboBox.setBounds(75, 20, 78, 18);
		contentPane.add(comboBox);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(255, 165, 0));
		textArea.setBounds(10, 60, 250, 126);
		contentPane.add(textArea);

		JButton Scrie = new JButton("Scrie");
		Scrie.addActionListener(new ActionListener() {
			String Actiune = "call create_review(? ,?, ?,?);";

			public void actionPerformed(ActionEvent e) {
				try {
					String txt = textArea.getText();
					if (txt.isBlank()) {
						txt = null;
					}
					CallableStatement pstm = c.getConect().prepareCall(Actiune);
					String z = comboBox.getSelectedItem().toString();
					pstm.setInt(1, Produs);
					pstm.setFloat(2, Float.valueOf(z));
					pstm.setString(3, NumarCont);
					pstm.setString(4, txt);
					pstm.execute();
					int n = table.getRowCount();
					ResultSet res;
					res = c.executequery("Select Stele from magazin");
					for (int i = 0; i < n; i++)
						try {
							model.setValueAt(res.getFloat(1), i, 3);
							res.next();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Scrie.setBackground(new Color(65, 105, 225));
		Scrie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Scrie.setBounds(171, 20, 89, 19);
		contentPane.add(Scrie);
	}
}
