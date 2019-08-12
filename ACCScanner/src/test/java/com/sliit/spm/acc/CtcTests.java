/**
 * 
 */
package com.sliit.spm.acc;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.sliit.spm.model.Line;

/**
 * @author Vimukthi Rajapaksha
 * @date Aug 12, 2019
 */
@RunWith(Parameterized.class)
public class CtcTests {

	private String line;
	private int expectedResult;
	private Line lineObj;

	public CtcTests(String line, int expectedResult) {
		this.line = line;
		this.expectedResult = expectedResult;
		lineObj = new Line();
	}

	@Parameters
	public static Collection<?> params() {
		return Arrays.asList(new Object[][] { 
			{ "", 0 },
			{ "if(true) {", 1 },
			{ "if((number == 0) || (number == 1)) {", 2 },
			{ "for (int i = 0; i < array.length; i++) {", 2 },
			{ "while (100 > 5) {", 2 },
			{ "} catch (Exception e) {", 1 } });
	}

	@Test
	public void testCalcCtc() {
		assertEquals(expectedResult, Ctc.generateCtc(lineObj, line));
	}

}
