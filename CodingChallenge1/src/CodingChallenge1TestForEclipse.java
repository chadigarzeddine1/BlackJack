import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodingChallenge1TestForEclipse {
	String filename = "src/CodingChallenge1.java";

	private boolean containsImportStatement() {
		boolean containsImport = false;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			while (line != null && !containsImport) {
				if (line.matches("import+\\s.*")) {
					containsImport = true;
				} 
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return containsImport;
	}
	
	/**
	Checks if the specified library is used anywhere in the code tested.  It checks
	for the word exactly.  If there is a variable name that contains the library name,
	this will result in a false positive.
	*/
	private boolean usesLibrary(String libraryName) {
		boolean usesLibrary = false;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			while (line != null && !usesLibrary) {
				if (line.contains(libraryName)) {
					usesLibrary = true;
				} 
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return usesLibrary;	
	}
	
	/**
		Checks if the coding construct is used in the class we're testing.  It expects the 
		construct to be preceded and followed by white space or parenthesis.
	*/
	private boolean usesConstruct(String constructName) {
		boolean usesConstruct = false;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			while (line != null && !usesConstruct) {
				if (line.matches(".*\\s+if+[\\s+,(].*")) {
					usesConstruct = true;
				} 
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return usesConstruct;	
	}
		
	@Test
	public void test_isLower_a() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		
		boolean expected = true;
		boolean actual = CodingChallenge1.isLowercaseChar('a');
		
		assertEquals("Checking if a is lower case letter", expected, actual);
	}

	@Test
	public void test_isLower_z() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		
		boolean expected = true;
		boolean actual = CodingChallenge1.isLowercaseChar('z');
		
		assertEquals("Checking if z is lower case letter", expected, actual);
	}

	@Test
	public void test_isLower_f() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		
		boolean expected = true;
		boolean actual = CodingChallenge1.isLowercaseChar('f');
		
		assertEquals("Checking if f is lower case letter", expected, actual);
	}

	@Test
	public void test_isLower_A() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		
		boolean expected = false;
		boolean actual = CodingChallenge1.isLowercaseChar('A');
		
		assertEquals("Checking if A is lower case letter", expected, actual);
	}

	@Test
	public void test_isLower_7() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		
		boolean expected = false;
		boolean actual = CodingChallenge1.isLowercaseChar('7');
		
		assertEquals("Checking if 7 is lower case letter", expected, actual);
	}

	
	@Test
	public void test_ceilingAfterMult_MultResultsInWholeNum() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		
		long expected = 6;
		long actual = CodingChallenge1.ceilingOfMultiplication(2,3.0);
		
		assertEquals("Value of ceiling(2 * 3.0)", expected, actual);
	}

	@Test
	public void test_ceilingAfterMult_MultResultsInNegativeNum() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		
		long expected = -6;
		long actual = CodingChallenge1.ceilingOfMultiplication(2,-3.0);
		
		assertEquals("Value of ceiling(2 * -3.0)", expected, actual);
	}

	@Test
	public void test_ceilingAfterMult_MultResultsInDecimal() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		
		long expected = 11;
		long actual = CodingChallenge1.ceilingOfMultiplication(3,3.5);
		
		assertEquals("Value of ceiling(3 * 3.5)", expected, actual);
	}

	@Test
	public void test_ceilingAfterMult_checkIfCastAfterMult() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		
		long expected = 31;
		long actual = CodingChallenge1.ceilingOfMultiplication(5,6.2);
		
		assertEquals("Value of ceiling(5 * 6.2)", expected, actual);
	}

	@Test
	public void test_addOctalDigits_12345() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		
		
		int expected = 15;
		int actual = CodingChallenge1.addOctalDigits(012345);
		
		assertEquals("testing octal 12345", expected, actual);
	}
	
	@Test
	public void test_addOctalDigits_0() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		
		int expected = 0;
		int actual = CodingChallenge1.addOctalDigits(0);
		
		assertEquals("testing octal 0", expected, actual);
	}

	@Test
	public void test_addOctalDigits_76543() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		
		int expected = 25;
		int actual = CodingChallenge1.addOctalDigits(076543);
		
		assertEquals("testing octal 76543", expected, actual);
	}
	
	@Test
	public void test_addOctalDigits_100() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		
		
		int expected = 1;
		int actual = CodingChallenge1.addOctalDigits(0100);
		
		assertEquals("testing octal 100", expected, actual);
	}

	@Test
	public void test_addOctalDigits_711111() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		
		int expected = 12;
		int actual = CodingChallenge1.addOctalDigits(0711111);
		
		assertEquals("testing octal 711111", expected, actual);
	}

	@Test
	public void test_addOctalDigits_longNumber() {
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		
		int expected = 38;
		int actual = CodingChallenge1.addOctalDigits(012345671234);
		
		assertEquals("testing octal 12345671234", expected, actual);
	}

	@Test
	public void test_indexOfFirstLowerCaseChar_emptyString() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		
		int expected = -1;
		int actual = CodingChallenge1.firstLowercaseChar("");
		
		assertEquals("testing empty string", expected, actual);
	}
	
	@Test
	public void test_indexOfFirstLowerCaseChar_noLowerCaseChars() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		int expected = -1;
		int actual = CodingChallenge1.firstLowercaseChar("ABCED1234&8*ZY");
		
		assertEquals("testing ABCED1234&8*ZY", expected, actual);
	}
	
	@Test
	public void test_indexOfFirstLowerCaseChar_firstCharIsLowerCase() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		int expected = 0;
		int actual = CodingChallenge1.firstLowercaseChar("abcdefg");
		
		assertEquals("testing abcdefg", expected, actual);
	}
	
	@Test
	public void test_indexOfFirstLowerCaseChar_lastCharIsLowerCase() {
		assertFalse("Don't use the Character class in your code.",usesLibrary("Character"));
		int expected = 6;
		int actual = CodingChallenge1.firstLowercaseChar("ABC$TYi");
		
		assertEquals("testing ABC$TYi", expected, actual);
	}
	

}
