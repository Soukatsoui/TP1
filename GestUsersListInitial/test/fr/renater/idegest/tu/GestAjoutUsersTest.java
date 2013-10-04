package fr.renater.idegest.tu;

import java.io.IOException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class GestAjoutUsersTest extends TestCase {
	/** l'OUT */
	private GestAjoutUsers gau;                           // 1

	@Before
	protected void setUp() throws Exception {
		try {
			this.gau = new GestAjoutUsers("testusers.xml");   // 2
		} catch (IOException e) {
			fail("Création de l'OUT impossible !");
		}
	}

	@Test
	public void test2PremiersCarsGenUid() {
		String uid = this.gau.genUid("Bob", "Martin");                      // 3 
		assertTrue("Les 2 premiers caractères sont valides", uid.startsWith("bm"));  // 4
	}

	
	
	//Question 1
	
//	@Test
//	public void testMinusculeUid() {
//		String uid = this.gau.genUid("Bob", "Martin");
//		assertEquals("Les caractères sont en minuscule", uid.toLowerCase(), uid);
//	}
	
	
	
	//Question 2
	
	@Test
	public void testLongueurUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertTrue("Le nombre de caractères est compris entre 5 et 9", uid.length() >=5 && uid.length() <=9);
		uid = this.gau.genUid("Bob", "Martibzetbzetn");
		assertTrue("Le nombre de caractères est compris entre 5 et 9", uid.length() >=5 && uid.length() <=9);
	}
	
	
	
	//Questions 1 et 3
	
	@Test
	public void testCaracSpecUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		String CaracSpec = "([a-z]*)";	
		assertTrue("Aucun caractère spécial n'est présent et les caractères sont en minuscule", uid.matches(CaracSpec));
	}
	
	
	
	//Question 4
	
	@Test
	public void testUidDejaUtil() {
		User uid = this.gau.addUser("Bob", "Martin");
		User uid2 = this.gau.addUser("Bernard", "Martin");
		System.out.println(uid +"\n"+ uid2);
		assertTrue("Les UIDs sont différents", uid.getLogin() != uid2.getLogin());
	}
	
	
	
	//Question 5
	
	@Test
	public void testLongueurPass() {
		User uid = this.gau.addUser("Bob", "Martin");
		System.out.println(uid.getPass());
		assertTrue("Le mot de passe est de bonne longueur", uid.getPass().length() == 8);
	}
	
	
	
//	Question 6
	
	@Test
	public void testEgalPass() {
		String pass1 = gau.genPassword(8);
		String pass2 = gau.genPassword(8);
		System.out.println(pass1 +"\n"+ pass2);
		assertTrue("Les mots de passe sont différents", pass1 != pass2);
	}
}