import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 *
 * Created by jiely on 2017/7/13.
 */
public class GrpcServer {
    public int port = 50051;
    public Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();

        System.out.println("service start...");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        final GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }


    // 实现 定义一个实现服务接口的类
    public class GreeterImpl extends GreeterGrpc.GreeterImplBase {


        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("service:"+req.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello : " + req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
//            int i = 10/0;
        }
    }
}