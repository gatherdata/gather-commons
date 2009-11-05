/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.db4o;

import org.joda.time.DateTime;
import org.osgi.framework.Bundle;

import com.db4o.reflect.ReflectClass;
import com.db4o.reflect.generic.GenericReflector;
import com.db4o.reflect.jdk.ClassLoaderJdkLoader;
import com.db4o.reflect.jdk.JdkLoader;
import com.db4o.reflect.jdk.JdkReflector;
import com.db4o.typehandlers.TypeHandlerPredicate;

public class DateTimeHandlerPredicate implements TypeHandlerPredicate {

    private GenericReflector reflector;

    public DateTimeHandlerPredicate(GenericReflector reflector) {
        this.reflector = reflector;
    }

    public boolean match(ReflectClass classReflector) {
        ReflectClass claxx = reflector.forClass(DateTime.class);
        boolean res = claxx.equals(classReflector);
        // System.out.println("match? " + classReflector + (res ? " == " : " != ") + claxx);
        return res;
    }

    class OSGiLoader implements JdkLoader {

        private final Bundle _bundle;
        private JdkLoader _loader;

        public OSGiLoader(Bundle bundle, JdkLoader loader) {
            _bundle = bundle;
            _loader = loader;
        }

        public Class loadClass(String className) {
            Class clazz = _loader.loadClass(className);
            if (clazz != null) {
                return clazz;
            }
            try {
                return _bundle.loadClass(className);
            } catch (ClassNotFoundException exc) {
                return null;
            }
        }

        public Object deepClone(Object context) {
            return new OSGiLoader(_bundle, (JdkLoader) _loader
                    .deepClone(context));
        }

    }

}
