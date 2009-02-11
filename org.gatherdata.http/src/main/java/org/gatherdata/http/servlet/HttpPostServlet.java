package org.gatherdata.http.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.net.DnsUrlFactory;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.core.spi.EnvelopeSource;
import org.gatherdata.core.spi.support.EnvelopeDistributor;

public class HttpPostServlet extends HttpServlet implements EnvelopeSource {

    /**
     * 
     */
    private static final long serialVersionUID = -6032736927474461471L;
    
    Log log = LogFactory.getLog(HttpPostServlet.class);

    private EnvelopeDistributor distributor = new EnvelopeDistributor();

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
        	Envelope postedDataInEnvelope = HttpRequestEnvelopeFactory.stuffEnvelopeFromPost(request);

        	distribute(postedDataInEnvelope);

            respondSuccess(request, response);
        } catch (Exception e) {
            log.warn("http-post of instance data failed, because: "
                    + e.getLocalizedMessage());
            respondError(request, response, e);
        }

    }

    protected void distribute(Envelope envelopeToDistribute) {
    	distributor.distribute(envelopeToDistribute);
	}

	protected void respondError(HttpServletRequest request, HttpServletResponse response, Exception e) {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html");

        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.println("<html><body align='center'>");
            writer.println("<h1>GATHER HTTP-POST Servlet</h1>");
            writer.println("<p>");
            e.printStackTrace(writer);
            writer.println("</p>");
            dumpHeaders(request, writer);
            writer.println("</body></html>");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void respondSuccess(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");

        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.println("<html><body align='center'>");
            writer.println("<h1>GATHER HTTP-POST Servlet</h1>");
            writer.println("<p>");
            writer.println("Success!");
            writer.println("</p>");
            dumpHeaders(request, writer);
            writer.println("</body></html>");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    protected void dumpHeaders(HttpServletRequest request, PrintWriter writer) {
    	writer.println("<h2>Request Headers</h2>");
    	
    	writer.println("<ul>");
    	Enumeration<String> headerE = request.getHeaderNames();
		while (headerE.hasMoreElements()) {
			String headerName = headerE.nextElement();
			writer.println("<li>" + headerName + ": " + request.getHeader(headerName) + "</li>");
		}
    	writer.println("</ul>");
	}

	protected String readRequestData(BufferedReader reader) throws IOException {
        StringBuffer content = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        return content.toString();
    }

    protected URI createUriFrom(String remoteHost, String remoteAddr,
            String requestURL) {
        String dnsName = (remoteHost != null) ? remoteHost : remoteAddr;
        return DnsUrlFactory.createDnsUri(null, dnsName, null);
    }

    public int getEnvelopesSent() {
        return distributor.getEnvelopesSent();
    }

    public boolean subscribe(EnvelopeDestination destination) {
        return distributor.subscribe(destination);
    }

    public boolean unsubscribe(EnvelopeDestination destination) {
        return distributor.unsubscribe(destination);
    }
}
