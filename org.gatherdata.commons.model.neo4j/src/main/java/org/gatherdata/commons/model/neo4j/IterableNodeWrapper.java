/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model.neo4j;

import java.util.Iterator;

import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;

public class IterableNodeWrapper<T> implements Iterable<T> {
    
    Iterable<Node> nodes;
    NodeAdapter<T, ?> nodeAdapter;
    NeoService neo;
    
    public IterableNodeWrapper(NeoService neo, Iterable<Node> nodes, NodeAdapter<T, ?> nodeAdapter) {
        this.neo = neo;
        this.nodes = nodes;
        this.nodeAdapter = nodeAdapter;
    }

    public Iterator<T> iterator() {
        return new NodeIteratorWrapper(nodes.iterator());
    }

    class NodeIteratorWrapper implements Iterator<T> {
        
        Iterator<Node> wrappedIterator;

        public NodeIteratorWrapper(Iterator<Node> iteratorToWrap) {
            wrappedIterator = iteratorToWrap;
        }

        public boolean hasNext() {
            return wrappedIterator.hasNext();
        }

        public T next() {
            Node nextNode = wrappedIterator.next();
            return nodeAdapter.adaptFromNode(nextNode, neo);
        }

        public void remove() {
            wrappedIterator.remove();
        }
        
    }
}
