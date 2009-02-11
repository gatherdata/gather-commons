package org.gatherdata.http.servlet;

import java.io.Serializable;
import java.net.URI;

import javax.activation.MimeType;

import org.gatherdata.core.model.Envelope;

/**
 * A mock sub-class of the HttpPostServlet, for testing.
 * 
 */
public class MockHttpPostServlet extends HttpPostServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -7414202145757344407L;
    
    public MimeType distributedContentType;

    public Serializable distributedContent;

    public URI distributedSource;
    
    @Override
    protected void distribute(Envelope envelopeToDistribute) {
        super.distribute(envelopeToDistribute);
        distributedContentType = envelopeToDistribute.getType();
        distributedContent = envelopeToDistribute.getContents();
        distributedSource = envelopeToDistribute.getSource();   
    }
}
