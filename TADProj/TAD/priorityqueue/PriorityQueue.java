package TAD.priorityqueue;

public class PriorityQueue<N extends Comparable<N>> implements PriorityQueueTAD<N> {
	private int nroElem = 0;
	private N [] vet;
	public PriorityQueue() {
		vet = (N[]) new Comparable[1000];
	}
	
	public PriorityQueue(int t) {
		vet = (N[]) new Comparable[t];
	}

	private void maxHeapifyUp(N v [], int posAtual) { 
		N aux;
		int posPai = (posAtual % 2 == 1) ? (posAtual -1)/2 : (posAtual -1)/2;

		if (posAtual >= nroElem)
			throw new IllegalArgumentException();
		
		while (posAtual > 0) {
			if (v[posPai].compareTo(v[posAtual]) < 0) {
				aux = v[posPai];
				v[posPai] = v[posAtual];
				v[posAtual] = aux;

				posAtual = posPai;
				posPai = (posAtual % 2 == 1) ? (posAtual -1)/2 : (posAtual -1)/2;
			}
			else
				break;
		}
	} 

	private void maxHeapifyDown(N v [], int pai) { 
		N aux;
		int posFilhoMaior = 2 * pai + 1, posFilhoDireita = posFilhoMaior + 1;

		if (posFilhoMaior < nroElem) { 
			if ( posFilhoDireita < nroElem && v[posFilhoMaior].compareTo(v[posFilhoDireita]) < 0){
				posFilhoMaior = posFilhoDireita;
			}

			if (v[posFilhoMaior].compareTo(v[pai]) > 0) {
				aux = v[pai];
				v[pai] = v[posFilhoMaior];
				v[posFilhoMaior] = aux;
				maxHeapifyDown(v, posFilhoMaior); 
			} 
		} 
	} 
	
	/* (non-Javadoc)
	 * @see TAD.priorityqueue.PriorityQueueTAD#offer(N)
	 */
	@Override
	public void offer(N value) {
		if (nroElem == vet.length)
			throw new IllegalArgumentException();
		
		vet[nroElem++] = value;
		maxHeapifyUp(vet, nroElem - 1);
	}
	
	/* (non-Javadoc)
	 * @see TAD.priorityqueue.PriorityQueueTAD#pull()
	 */
	@Override
	public N pull() {
		N res = null;
		
		if (nroElem == 0)
			throw new IllegalArgumentException();
		
		res = vet[0];
		vet[0] = vet[nroElem-- - 1];
		
		maxHeapifyDown(vet, 0);
		return res;
	}
	
	public String toString() {
		String res = "";
		
		for (int i = 0; i < nroElem; i++)
			res += vet[i] + " ";
		
		return (res);
	}
}
