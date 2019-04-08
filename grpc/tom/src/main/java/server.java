import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by leesin on 2018/8/20.
 *
 */
@WebServlet(name="a",value = "/server")
public class server extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        final GrpcServer server = new GrpcServer();
        server.start();
        try {
            server.blockUntilShutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
