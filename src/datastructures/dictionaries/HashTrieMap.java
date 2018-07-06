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
        // travels down tree using iterator output, creating children if child DNE
        while (keyIter.hasNext()) {
            A charK = keyIter.next();
            if (!curr.pointers.containsKey(charK)) {
                curr.pointers.put(charK, new HashTrieNode());
            }
            curr = curr.pointers.get(charK);  
        }
        
        // returns existing value at node, null if DNE
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
        HashTrieNode node = findHelper(key);
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    @Override
    public boolean findPrefix(K key) {
        if (findHelper(key) == null) {
            return false;
        } else {
            return true;
        }
    }
    
    /*
     * Helper method for finding node in Trie Map given a key, throws new
     * IllegalArgumentException if key is null, returns null if there is no
     * node for given key
     */
    private HashTrieNode findHelper(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyIter = key.iterator();
        HashTrieNode curr = (HashTrieNode) root;
        // travels down tree using iterator output, returning null if child DNE
        while (keyIter.hasNext()) {
            A charK = keyIter.next();
            if (!curr.pointers.containsKey(charK)) {
                return null;
            }
            curr = curr.pointers.get(charK);  
        }
        return curr;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyIter = key.iterator();
        HashTrieNode curr = (HashTrieNode) root;
        // reference to the last node that contained a value before reaching delete value
        HashTrieNode lastValue = null;
        // which child of last value to remove if last value has multiple children
        A toRemove = null;
        // flag if have no such node exists
        Boolean noRoute = false;
        // how many nodes will be deleted with this function, for updating size
        int nodesToRemove = 0;
        
        while (keyIter.hasNext() && !noRoute) {
            A charK = keyIter.next();
            if (curr.pointers.containsKey(charK)) { 
                
                if (curr.value != null || curr.pointers.keySet().size() >= 2) {
                    lastValue = curr;
                    toRemove = charK;
                    nodesToRemove = 0;
                }
                
                nodesToRemove++;
                curr = curr.pointers.get(charK);

            } else {
                noRoute = true;
            }
        }
        // if there was something to delete, either delete the value, delete tree up to
        // the last node with a value or delete entire tree (only one value in tree)
        if (!noRoute) {
            if (curr.pointers.isEmpty()) {
                if (lastValue == null) {
                    clear();
                } else {
                    lastValue.pointers.remove(toRemove);
                    this.size -= nodesToRemove;
                }
            } else {
                curr.value = null;
            }
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = new HashTrieNode();
    }
}
