package br.pucrs.adt.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.pucrs.adt.BinaryTreeLinked;
import br.pucrs.adt.BinaryTreeTADFull;

public class BinaryTreeTADTests {
	private BinaryTreeTADFull<String> arvBin;
	
	@Before
	public void setUp() throws Exception {
		String [] vet = {"Raiz", 
						"F1_1", "F1_2",
						"F2_1", "F2_2","F2_3", "F2_4",
						"F3_1", "F3_2","F3_3", "F3_4","F3_5", "F3_6","F3_7", "F3_8"};
	
		arvBin = new BinaryTreeLinked<String>(vet);
	}

	@Test
	public void ConstructorArrayTest() {
		assertEquals("Raiz " +
					 "F1_1 F1_2 " +
				     "F2_1 F2_2 F2_3 F2_4 " +
				     "F3_1 F3_2 F3_3 F3_4 F3_5 F3_6 F3_7 F3_8 ", 
				     arvBin.toString());
	}

	@Test
	public void ConstructorArrayVazioTest() {
		arvBin = new BinaryTreeLinked<>(new String[0]);
		assertEquals("", 
				     arvBin.toString());
	}


}
