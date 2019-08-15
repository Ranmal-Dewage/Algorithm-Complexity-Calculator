/**
 * 
 */
package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Tenusha Guruge
 * @date Aug 12, 2019
 */
@RunWith(Parameterized.class)
public class CsTest {

	private String lineData;
	private int expectedResult;
	private Line line;
	private List<String> methodsAndVariables = new ArrayList<>();

	@Before
	public void before(){
		methodsAndVariables.add("count");
		methodsAndVariables.add("fibonacci");
		methodsAndVariables.add("number");
		methodsAndVariables.add("main");
		methodsAndVariables.add("args");
	}

	public CsTest(String line, int expectedValue) {
		this.lineData = line;
		this.expectedResult = expectedValue;
		this.line = new Line();
	}

	@Parameters
	public static Collection<?> params() {
		return Arrays.asList(new Object[][] { 
			{ "", 0 }, 
			{ "public static long fibonacci(long number) {", 4 },
			{ "if((number == 0) || (number == 1)) {", 8 },
			{ "return number;", 1 },
			{ "public static void main(String args[ ]) {", 4 },
			{ "for (int count = 0; count <= 10; count++){", 10 }
		});
	}

	@Test
	public void testCalcCs() {
		Cs.calcCs(line, lineData,methodsAndVariables);
		assertEquals(expectedResult, line.getCs());
	}

}
