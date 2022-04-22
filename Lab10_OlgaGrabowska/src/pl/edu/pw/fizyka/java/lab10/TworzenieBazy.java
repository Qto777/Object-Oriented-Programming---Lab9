package pl.edu.pw.fizyka.java.lab10;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TworzenieBazy {

	
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection(	"jdbc:h2:./data/nazwabazyXYZ", "sa", "");
			// domyslnie nazwa uzytkownika to "sa" a dostep jest bez hasla - ""
			// Proba podlaczenia do bazy H2, ktora nie istnieje 
			// domyslnie powoduje utworzenie nowej instancji pustej bazy 
			// (w postaci pliku z rozszerzeniem *.db, np. nazwabazy.h2.db) w podkatalogu "data" 
			
			// Dymyslne tworzenie pustej bazy danych czasem moze generowac bledy,
			// dlatego mozliwe jest wylaczenie domyslnego tworzenia pustej bazy
			// conn = DriverManager.getConnection(	"jdbc:h2:nazwabazyXYZ;IFEXISTS=TRUE", "sa", "");
			
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		

	}

}