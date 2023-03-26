package com.GraphesOrientesValues;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

class IGrapheTest {
	// graphe de l'exercice 3.1 du poly de maths
	// avec en plus un noeud isole : J
	private String g31 = 
			  "A-C(2), A-D(1), "
			+ "B-G(3), "
			+ "C-H(2), "
			+ "D-B(3), D-C(5), D-E(3), "
			+ "E-C(1), E-G(3), E-H(7), "
			+ "F:, "
			+ "G-B(2), G-F(1), "
			+ "H-F(4), H-G(2), "
			+ "I-H(10), "
			+ "J:";


	@org.junit.jupiter.api.Test
	void exo3_1Maths() {
		GrapheLArcs gla = new GrapheLArcs(g31);
		tester3_1(gla);
	}
	
	void tester3_1(GrapheLArcs g) {
		List<String> sommets = List.of("A","B","C","D","E","F","G","H","I","J");
		assertEquals(sommets, g.getSommets());
		assertTrue(g.contientSommet("C"));
		assertFalse(g.contientSommet("c"));
		assertTrue(g.contientArc("C","H"));
		assertFalse(g.contientArc("H","C"));
		assertEquals(7,g.getValuation("E", "H"));
		assertEquals(List.of("B","C", "E"), g.getSucc("D"));
		assertEquals(g31, g.toString());
	}

}
