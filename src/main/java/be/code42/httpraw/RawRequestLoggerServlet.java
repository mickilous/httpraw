package be.code42.httpraw;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by cmi on 30/12/15.
 */
public class RawRequestLoggerServlet extends HttpServlet {

    static final Logger LOG = LoggerFactory.getLogger(RawRequestLoggerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traceHttp(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traceHttp(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traceHttp(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traceHttp(req, resp);
    }

    private void traceHttp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info(req.getMethod() + " " + req.getRequestURI() + " " + req.getProtocol());

        LOG.info("--- Header ---");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            LOG.info(header + ": " + req.getHeader(header));
        }

        LOG.info("--- Parameters ---");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameter = parameterNames.nextElement();
            LOG.info(parameter + " = " + req.getParameter(parameter));
        }

        LOG.info("--- Content ---");
        ServletInputStream inputStream = null;
        try {
            inputStream = req.getInputStream();
            String body = IOUtils.toString(inputStream);
            LOG.info(body);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

    }

}
