package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyIter = key.iterator();
        HashTrieNode curr = (HashTrieNode) root;
        while (keyIter.hasNext()) {
            A temp = keyIter.next();
            if (!curr.pointers.containsKey(temp)) {
                curr.pointers.put(temp, new HashTrieNode());
            }
            curr = curr.pointers.get(temp);  
        }
        if (curr.value != null) {
            V retValue = curr.value;
            curr.value = value;
            return retValue;
        } else {
            curr.value = value;
            this.size++;
            return null;
        }
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyIter = key.iterator();
        HashTrieNode curr = (HashTrieNode) root;
        while (keyIter.hasNext()) {
            A temp = keyIter.next();
            if (!curr.pointers.containsKey(temp)) {
                return null;
            }
            curr = curr.pointers.get(temp);  
        }    
        return curr.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyIter = key.iterator();
        HashTrieNode curr = (HashTrieNode) root;
        while (keyIter.hasNext()) {
            A temp = keyIter.next();
            if (!curr.pointers.containsKey(temp)) {
                return false;
            }
            curr = curr.pointers.get(temp);  
        } 
        return true;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyIter = key.iterator();
        HashTrieNode curr = (HashTrieNode) root;
        Boolean noRoute = false;
        int counter = -1;
        int lastNode = 0;
        while (keyIter.hasNext() && !noRoute) {
            A temp = keyIter.next();
            
            if (curr.pointers.containsKey(temp)) { 
                counter++;
                if (curr.value != null) {
                    lastNode = counter;
                }
                curr = curr.pointers.get(temp);

            } else {
                noRoute = true;
            }
        }
        if (!noRoute && curr.value != null) {
            this.size --;
            curr.value = null;
        }
        keyIter = key.iterator();
        curr = (HashTrieNode) root;
        lastNode--;
        if (!noRoute && curr.pointers.isEmpty()) {
            while (lastNode > 0) {
                curr = curr.pointers.get(keyIter.next());
                lastNode--;
            }
            curr.pointers.remove(keyIter.next());
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = new HashTrieNode();
    }
}
