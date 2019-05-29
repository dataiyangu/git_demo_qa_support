import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by jiely on 2017/7/15.
 */
public class GrpcClient {
    public final ManagedChannel channel;
    public final GreeterGrpc.GreeterBlockingStub blockingStub;


    public GrpcClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void greet(String name) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);


//        Field unknonwnFields = request.getClass().getDeclaredField("unknonwnFields");
//        if(unknonwnFields!=null){
//            Object o = unknonwnFields.get(request);
//            if(o!=null){
//                Class<?> buildr = o.getClass().getDeclaringClass("Buildr");
//                Method addField = buildr.getDeclaredMethod("addField");
//                if (addField != null) {
//                    addField.invoke(buildr.newInstance(), 1, null);
//                }
//            }
//        }
//
//


        System.out.println(request.getClass());

        System.out.println(response.getMessage());

    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        GrpcClient client = new GrpcClient("127.0.0.1",50051);
        for(int i=0;i<100;i++){
            client.greet("world:"+i);

        }

    }
}