package org.gatherdata.http.servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.gatherdata.mock.spi.support.MockEnvelopeDestination;
import org.junit.Test;
import org.mortbay.jetty.MimeTypes;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class HttpPostServletTest {

    /**
     * An empty http-post should not cause an exception, 
     * just return a bad request in the http response.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void shouldAcceptEmptyHttpPost() throws ServletException, IOException {
        HttpPostServlet postServlet = new HttpPostServlet();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        postServlet.doPost(mockRequest, mockResponse);
        assertEquals(HttpServletResponse.SC_BAD_REQUEST, mockResponse.getStatus());
    }

    @Test
    public void shouldAcceptPlainTextHttpPost() throws ServletException, IOException {
        HttpPostServlet postServlet = new HttpPostServlet();
        MockHttpServletRequest mockRequest = createMockPlainTextPostRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        postServlet.doPost(mockRequest, mockResponse);
        assertEquals(HttpServletResponse.SC_OK, mockResponse.getStatus());
    }
    
    @Test
    public void shouldDistributePlainTextFromHttpPost() throws Exception {
        MockHttpPostServlet postServlet = new MockHttpPostServlet();
        MockHttpServletRequest mockRequest = createMockPlainTextPostRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        postServlet.doPost(mockRequest, mockResponse);
        assertEquals(MimeTypes.TEXT_PLAIN, postServlet.distributedContentType.toString());
        assertNotNull(postServlet.distributedContent);
        assertNotNull(postServlet.distributedSource);
        assertEquals(1, postServlet.getEnvelopesSent());
    }
    
    @Test 
    public void shouldDistributeToSubscribedDestinations() throws Exception {
        MockHttpPostServlet postServlet = new MockHttpPostServlet();
        MockEnvelopeDestination mockDestination = new MockEnvelopeDestination();
        postServlet.subscribe(mockDestination);
        MockHttpServletRequest mockRequest = createMockPlainTextPostRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        postServlet.doPost(mockRequest, mockResponse);
        assertEquals(1, mockDestination.getEnvelopesReceived());
    }

    private MockHttpServletRequest createMockPlainTextPostRequest() throws UnsupportedEncodingException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        final String PLAIN_TEXT_CONTENT = "this is plain text";
        final String ENCODING = "UTF-8";
        final String TEXT_PLAIN_MIME_TYPE = "text/plain";
        mockRequest.setContent(PLAIN_TEXT_CONTENT.getBytes(ENCODING));
        mockRequest.setCharacterEncoding(ENCODING);
        mockRequest.setContentType(TEXT_PLAIN_MIME_TYPE);

        return mockRequest;
    }
}
