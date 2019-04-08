import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by leesin on 2018/8/23.
 */
@WebServlet(name="a",value = "/client")
public class S extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        GrpcClient client = new GrpcClient("10.0.1.224",50051);
        for(int i=0;i<5;i++){
            client.greet("world:"+i);
        }
    }

}
