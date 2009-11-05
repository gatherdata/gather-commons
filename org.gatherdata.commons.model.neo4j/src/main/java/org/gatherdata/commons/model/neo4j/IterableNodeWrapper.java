/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
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
