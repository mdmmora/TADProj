package TAD.priorityqueue;

public interface PriorityQueueTAD<N extends Comparable<N>> {

	public void offer(N value);

	public N pull();

}