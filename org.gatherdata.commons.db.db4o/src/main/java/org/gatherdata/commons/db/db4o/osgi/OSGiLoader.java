/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o.osgi;

import org.osgi.framework.Bundle;

import com.db4o.reflect.jdk.JdkLoader;

public class OSGiLoader implements JdkLoader {

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
