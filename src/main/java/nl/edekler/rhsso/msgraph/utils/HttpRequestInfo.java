package nl.edekler.rhsso.msgraph.utils;

import org.apache.http.RequestLine;
import org.apache.http.StatusLine;

import java.util.List;

/**
 * Http response info transfer object.<br/>
 *
 * @author <a href="mailto:edwin.de.kler@gmail.com">Edwin de Kler</a>
 * @since 1.0.0
 */
public class HttpRequestInfo {

    private long duration;

    /* request properties */
    private RequestLine requestLine;
    private List<String> requestHeaders;
    private String requestEntity;

    /* response properties */
    private StatusLine statusLine;
    private List<String> responseHeaders;
    private String responseEntity;

    public HttpRequestInfo() {
        this.duration = 0;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    /**
     * Is valid status code.
     *
     * @return <code>true</code> when statuscode >= 200 and < 300.
     */
    public final boolean isValidStatusCode() {
        return getStatusLine().getStatusCode() >= 200 && getStatusLine().getStatusCode() < 300;
    }

    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public List<String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(List<String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestEntity() {
        return requestEntity;
    }

    public void setRequestEntity(String requestEntity) {
        this.requestEntity = requestEntity;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public List<String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(List<String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(String responseEntity) {
        this.responseEntity = responseEntity;
    }

    public long getDuration() { return duration; }

    public void setDuration(long duration) { this.duration = duration; }

    @Override
    public String toString() {
        return String.format("%s %s %s [%s ms]", requestLine.getMethod(), requestLine.getUri(), statusLine, getDuration());
    }
}
