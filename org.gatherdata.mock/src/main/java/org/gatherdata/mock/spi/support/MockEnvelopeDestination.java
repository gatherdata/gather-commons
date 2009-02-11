package org.gatherdata.mock.spi.support;

import java.io.Serializable;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.gatherdata.core.io.QualifiedType;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.spi.EnvelopeDestination;

public class MockEnvelopeDestination implements EnvelopeDestination {
	
    final QualifiedType[] qualifiedTypes;     
    public MockEnvelopeDestination() {
        QualifiedType anything = null;
        try {
            anything = new QualifiedType(new MimeType("*", "*"), "*");
        } catch (MimeTypeParseException e) {
            e.printStackTrace();
        }
        qualifiedTypes = new QualifiedType[] {
                anything
        };
 
    }
    
	int envelopesReceived = 0;

	public int getEnvelopesReceived() {
		return envelopesReceived;
	}

	public void accept(Envelope envelope) {
		envelopesReceived++;
	}

	public boolean canAcceptContentOfClass(
			Class<? extends Serializable> contentClass) {
		return true;
	}

	public boolean canAcceptContentOfType(MimeType type) {
		return true;
	}

    public QualifiedType[] getAcceptableTypes() {
        return qualifiedTypes;
    }

}
