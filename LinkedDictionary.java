/*
 * NAME: Samir Macias
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary <K,V> implements DictionaryInterface<K,V> {
	
	private Node firstNode; // Reference to first node of chain
	private int numberOfEntries;
	
	public LinkedDictionary()
		{
		initializeDataFields();
		} // end default constructor
	private void initializeDataFields() {
		// TODO Auto-generated method stub
		firstNode = null;
		numberOfEntries = 0;
	}
	private class Node
	{
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue)
		{
			key = searchKey;
			value = dataValue;
			next = null;
		} // end constructor
	
		private Node(K searchKey, V dataValue, Node nextNode)
		{
			key = searchKey;
			value = dataValue;
			next = nextNode;
		} // end constructor

		private K getKey()
		{
			return key;
		} // end getKey

		private V getValue()
		{
			return value;
		} // end getValue
		
		private void setValue(V newValue)
		{
		value = newValue;
		} // end setValue

		private Node getNextNode()
		{
		return next;
		} // end getNextNode

		private void setNextNode(Node nextNode)
		{
		next = nextNode;
		} // end setNextNode
	} // end Node
	
	
	
	// Same as in SortedLinkedDictionary.
	// Since iterators implement Iterator, methods must be public.
	private class KeyIterator implements Iterator<K>
		{
		private Node nextNode;
	
		private KeyIterator()
			{
			nextNode = firstNode;
			} // end default constructor
	
		public boolean hasNext()
			{
			return nextNode != null;
			} // end hasNext
	
		public K next()
		{
			K result;
		
			if (hasNext())
			{
			result = nextNode.getKey();
			nextNode = nextNode.getNextNode();
			}
			else
			{
			throw new NoSuchElementException();
			} // end if
		
			return result;
		} // end next
	
		public void remove()
		{
		throw new UnsupportedOperationException();
		} // end remove
	} // end KeyIterator

	private class ValueIterator implements Iterator<V>
		{
		private Node nextNode;
	
		private ValueIterator()
		{
		nextNode = firstNode;
		} // end default constructor
	
		public boolean hasNext()
		{
		return nextNode != null;
		} // end hasNext
	
		public V next()
		{
		V result;
	
		if (hasNext())
		{
		result = nextNode.getValue();
		nextNode = nextNode.getNextNode();
		}
		else
		{
		throw new NoSuchElementException();
		} // end if
	
		return result;
		} // end next
	
		public void remove()
		{
		throw new java.lang.UnsupportedOperationException();
		} // end remove
	} // end getValueIterator

	@Override
	public V add(K key, V value) {
		V result = null;
		
		// Search chain for a node containing key
		Node currentNode = firstNode;
		while( (currentNode != null) && !key.equals(currentNode.getKey()))
		{
			currentNode = currentNode.getNextNode();
		}
		if(currentNode == null)
		{
			Node newNode = new Node(key,value);
			newNode.setNextNode(firstNode);
			firstNode = newNode;
			numberOfEntries++;
		}
		else
		{
			//key in dictionary: replace corresponding value
			result = currentNode.getValue();
			currentNode.setValue(value); // replace value
		}
		return result;
	}

	@Override
	public V remove(K key) {
		V result = null;
		
		if(!isEmpty())
		{
			// Search chain for a node containing key
			// save reference to preceding node
				Node currentNode = firstNode;
				Node nodeBefore = null;
				
			while((currentNode != null) && !key.equals(currentNode.getKey()))
			{
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			}
			
			if(currentNode != null)
			{
				Node nodeAfter = currentNode.getNextNode();
				
				if(nodeBefore == null)
					firstNode = nodeAfter;
				else 
					nodeBefore.setNextNode(nodeAfter);
				
				result = currentNode.getValue();
				numberOfEntries--;
			}// end if
		} // end if
		return result;
	}

	@Override
	public V getValue(K key) {
		V result = null;
		// Search for a node that contains key
		Node currentNode = firstNode;
		
		while((currentNode != null) && !key.equals((currentNode.getKey())))
			{
				currentNode = currentNode.getNextNode();
			}
		if(currentNode != null)
		{
			// Search key found
			result = currentNode.getValue();
		}
		return result;
	}

	@Override
	public boolean contains(K key) {
		return getValue(key) != null;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}	

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		initializeDataFields();
	}
	
}

