package org.gatherdata.commons.junit;

import java.util.Collection;
import java.util.Vector;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class ContainsAll<ItemType> extends TypeSafeMatcher<Iterable<ItemType>> {

    private Vector<ItemType> requiredVector;

    private StringBuffer failureReason = new StringBuffer();

	private int initialSize = 0;
	private int availableSize = 0;
    
    public ContainsAll(Collection<ItemType> requiredItems) {
        this.requiredVector =  new Vector<ItemType>(requiredItems);
        this.initialSize = requiredVector.size();
    }

    @Override
    public boolean matchesSafely(Iterable<ItemType> availableItems) {
        if (availableItems == null) {
            failureReason.append("actual is null.");
            return false;
        }
        if (!(availableItems instanceof Iterable<?>)) {
            failureReason.append("actual is not Iterable<>.");
            return false;
        }
        
        for (ItemType item : (Iterable<ItemType>)availableItems) {
            availableSize++;
            if (requiredVector.contains(item)) {
                boolean wasRemoved = requiredVector.remove(item);
                if (!wasRemoved) failureReason.append("failed to remove " + item);
            } else {
                failureReason.append("skipping item: " + item + "\n");
            }
        }
        if (!requiredVector.isEmpty()) {
        	failureReason.append(requiredVector.size() + " items not found (perhaps hash and equals are different).");
        }
        
        return requiredVector.isEmpty();
    }

    public void describeTo(Description description) {
        description.appendText("expected " + initialSize + " items. Compared against " + availableSize + ".\n");
        description.appendText(failureReason.toString());
    }
        
    @Factory
    public static <T, K> Matcher<Iterable<K>> containsAll(Collection<K> requiredItems) {
        return new ContainsAll<K>(requiredItems);
    }


}