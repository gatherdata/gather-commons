/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o;

import org.joda.time.DateTime;

import com.db4o.ext.Db4oIOException;
import com.db4o.foundation.PreparedComparison;
import com.db4o.internal.ByteArrayBuffer;
import com.db4o.internal.Comparable4;
import com.db4o.internal.DefragmentContext;
import com.db4o.internal.delete.DeleteContext;
import com.db4o.marshall.Context;
import com.db4o.marshall.ReadBuffer;
import com.db4o.marshall.ReadContext;
import com.db4o.marshall.WriteBuffer;
import com.db4o.marshall.WriteContext;
import com.db4o.typehandlers.TypeHandler4;
import com.db4o.typehandlers.ValueTypeHandler;

public class DateTimeHandler implements TypeHandler4, ValueTypeHandler, Comparable4 {


    private void writeDate(DateTime dateTime, WriteBuffer writeBuffer) {
        System.out.println("write: " + dateTime.getMillis());
        writeBuffer.writeLong(dateTime.getMillis());
    }

    private DateTime readDate(ReadBuffer readBuffer) {
        long millis = readBuffer.readLong();
        System.out.println("read: " + millis);
        return new DateTime(millis);
    }
    
    public void defragment(DefragmentContext defContext) {
        System.out.println("defragment");
        DateTime date = readDate(defContext.sourceBuffer());
        writeDate(date, defContext.targetBuffer());

    }

    public void delete(DeleteContext delContext) throws Db4oIOException {
        System.out.println("delete");
        delContext.readSlot();
    }

    public void write(WriteContext writeContext, Object obj) {
        DateTime dateTime = (DateTime)obj;
        writeDate(dateTime, writeContext);
    }

    public Object read(ReadContext readContext) {
        return readDate(readContext);
    }

    public PreparedComparison prepareComparison(Context context, Object obj) {
        final DateTime thisDate = (DateTime) obj;
        PreparedComparison result = new PreparedComparison()
        {
          public int compareTo(Object otherObj)
          {
              DateTime otherDate = (DateTime) otherObj;
            return thisDate.compareTo(otherDate);
          }
        };
        return result;
    }
    
    

}
