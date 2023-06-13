import java.sql.*;

public class Conexiune {
	private final String user = "root";
	private final String parola = "MYSQL132";
	private final String baza = "jdbc:mysql://localhost:3306/Proiect";
	private Connection conect;
	private Statement stm;
	private ResultSet rez;

	public Connection getConect() {
		return conect;
	}

	public void setConect(Connection conect) {
		this.conect = conect;
	}

	public Statement getStm() {
		return stm;
	}

	public void setStm(Statement stm) {
		this.stm = stm;
	}

	public Conexiune() {
		try {
			conect = DriverManager.getConnection(baza, user, parola);
			stm = conect.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("eroare exeptie");
		}

		if (conect == null) { //
			System.out.println("JDBC connection is not established");
			return;
		} else
			System.out.println("Congratulations, JDBC connection is established successfully.\n");

	}

	public boolean verificareclient(String nume, String parola) throws Exception {
		stm = this.conect.createStatement();
		String Actiune = "Select Username,Parola from client " + "where Username=\"" + nume + "\"and Parola=\"" + parola
				+ "\"";
		rez = null;
		try {
			rez = stm.executeQuery(Actiune);
		} catch (Exception e) {
			System.out.println("eroare exceptie verificare");
		}

		if (rez.next() == false)
			return false;
		else
			return true;
	}

	public ResultSet executequery(String Actiune) {
		try {
			rez = stm.executeQuery(Actiune);
			rez.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * if(rez==null) //System.out.println("query gol");
		 */

		return rez;
	}

}
