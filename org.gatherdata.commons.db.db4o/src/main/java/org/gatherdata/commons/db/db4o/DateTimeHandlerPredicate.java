/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o;

import org.joda.time.DateTime;

import com.db4o.reflect.ReflectClass;
import com.db4o.reflect.generic.GenericReflector;
import com.db4o.reflect.jdk.JdkReflector;
import com.db4o.typehandlers.TypeHandlerPredicate;

public class DateTimeHandlerPredicate implements TypeHandlerPredicate {


    public boolean match(ReflectClass classReflector) {
        GenericReflector reflector = new GenericReflector(null, new JdkReflector(Thread.currentThread()
                .getContextClassLoader()));
        ReflectClass claxx = reflector.forClass(DateTime.class);
        boolean res = claxx.equals(classReflector);
        return res;
    }

}
