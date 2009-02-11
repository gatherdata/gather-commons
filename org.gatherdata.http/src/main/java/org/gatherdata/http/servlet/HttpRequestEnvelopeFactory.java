package org.gatherdata.http.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.servlet.http.HttpServletRequest;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.model.GenericEnvelope;
import org.gatherdata.core.net.DnsUrlFactory;

public class HttpRequestEnvelopeFactory {

    public static Envelope stuffEnvelopeFromPost(HttpServletRequest request) 
    throws IOException, MimeTypeParseException {
        return stuffEnvelopeFrom(request, request.getReader());
    }
    
    public static Envelope stuffEnvelopeFrom(HttpServletRequest request, Reader contentReader) 
    throws IOException, MimeTypeParseException {
    	Envelope stuffedEnvelope = null;
    	
        // generate a source URI from the request
        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        String requestURL = request.getRequestURL().toString();

        URI fromSource = createUriFrom(remoteHost, remoteAddr, requestURL);

        String content = readRequestData(new BufferedReader(contentReader));

        MimeType contentType = new MimeType(request.getContentType());

        stuffedEnvelope = new GenericEnvelope(fromSource, content, contentType);

        return stuffedEnvelope;
    }

	protected static String readRequestData(BufferedReader reader) throws IOException {
        StringBuffer content = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        return content.toString();
    }

    protected static URI createUriFrom(String remoteHost, String remoteAddr,
            String requestURL) {
        String dnsName = (remoteHost != null) ? remoteHost : remoteAddr;
        return DnsUrlFactory.createDnsUri(null, dnsName, null);
    }

}
