package fr.renater.idegest.tu;

import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class GestAjoutUsersTest extends TestCase {

	private	GestAjoutUsers gau;

	@Before
	public void setUp() throws Exception {
		try {
			this.gau = new GestAjoutUsers("testures.xml");
		} catch (IOException e) {
			fail("création de l'OUT impossible");
		}
	}

	@Test
	public void test2PremiersCarsGenUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertTrue("les 2 Premiers caractères sont valides", uid.startsWith("bm"));
	}
	
	@Test
	public void testToutEnMinusculeUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertEquals("Les caractères sont en minuscule", uid.toLowerCase(), uid);
	}
	
	@Test
	public void testLongueurUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertTrue("la longueur de la chaine est entre 5 et 9", uid.length() >= 5 && uid.length() <= 9);
		uid = this.gau.genUid("Bob", "Martinjfjfttfj");
		assertTrue("la longueur de la chaine est entre 5 et 9", uid.length() >= 5 && uid.length() <= 9);
	}
	
	@Test
	public void testCaraterIndesirableUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		String caract = "([a-z]*)";
		assertTrue ("Aucun caractère spécial n'est présent", uid.matches(caract));
		uid = this.gau.genUid("ômen", "àéïöù^y");
		assertTrue ("Aucun caractère spécial n'est présent", uid.matches(caract));
	}
	
	@Test
	public void testDoublonUid() {
		User uid = this.gau.addUser("Bob", "Martin");
		User uid2	= this.gau.addUser("Benoit", "Martin");
		System.out.println(uid + "\n" + uid2);
		assertTrue ("Les UIDs sont différents", uid.getLogin() != uid2.getLogin());
	}
	
	@Test
	public void testEgalPass() {
		String pass1 = gau.genPassword(8);
		String pass2 = gau.genPassword(8);
		System.out.println(pass1 + "\n" + pass2);
		assertFalse ("Les mots de passe sont différents", pass1.equals(pass2));
	}

}