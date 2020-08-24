import junit.framework.Assert;
import org.apache.logging.log4j.*;
//import org.apache.logging.log4j.*;
import org.eclipse.jetty.server.*;
import java.io.*;


public class RequestLogFactory {

    private Logger logger;

    public RequestLogFactory(Logger logger) {
        this.logger = logger;
    }

    AbstractNCSARequestLog create() {
        return new AbstractNCSARequestLog() {
            @Override
            protected boolean isEnabled() {
                return true;
            }

            @Override
            public void write(String s) throws IOException {
                logger.info(s);
            }
        };
    }
}