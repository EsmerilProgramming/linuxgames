package br.com.linuxgames.model.vo;

import java.util.Comparator;

public class JogoHitComparator implements Comparator<Jogo>{

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * 	       first argument is less than, equal to, or greater than the
     *	       second.
     * @throws ClassCastException if the arguments' types prevent them from
     * 	       being compared by this Comparator.
     */
	public int compare(Jogo jogo1, Jogo jogo2) {
		long hits1 = jogo1.getHits();
		long hits2 = jogo2.getHits();

		if (hits1==hits2) return 0;
		if (hits1>hits2) return 1;
		return -1;
	}

}
