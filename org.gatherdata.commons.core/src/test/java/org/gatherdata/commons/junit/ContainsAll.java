package org.gatherdata.commons.junit;

import java.util.Collection;
import java.util.Vector;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class ContainsAll<ItemType> extends TypeSafeMatcher<Iterable<ItemType>> {

    private Vector<ItemType> requiredVector;

    private String failureReason = "";

	private int initialSize;
    
    public ContainsAll(Collection<ItemType> requiredItems) {
        this.requiredVector =  new Vector<ItemType>(requiredItems);
        this.initialSize = requiredVector.size();
    }

    @Override
    public boolean matchesSafely(Iterable<ItemType> availableItems) {
        if (availableItems == null) {
            failureReason = "actual is null";
            return false;
        }
        if (!(availableItems instanceof Iterable<?>)) {
            failureReason = "actual is not Iterable<>";
            return false;
        }
        
        for (ItemType item : (Iterable<ItemType>)availableItems) {
            if (requiredVector.contains(item)) {
                requiredVector.remove(item);
            }
        }
        if (!requiredVector.isEmpty()) {
        	failureReason = requiredVector.size() + " items not found (perhaps hash and equals are different)";
        }
        
        return requiredVector.isEmpty();
    }

    public void describeTo(Description description) {
        description.appendText("expected " + initialSize + " items.");
        description.appendText(" " + failureReason);
    }
        
    @Factory
    public static <T, K> Matcher<Iterable<K>> containsAll(Collection<K> requiredItems) {
        return new ContainsAll<K>(requiredItems);
    }


}