import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("Server is running...");
        for(;;){
            Socket sock = ss.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }
}
class Handler extends Thread{
    Socket sock;

    public Handler(Socket sock){
        this.sock = sock;
    }

    public void run(){
        try (InputStream input = this.sock.getInputStream()){
            try (OutputStream output = this.sock.getOutputStream()){
                handle(input,output);
            }
        } catch (Exception e){

        } finally {
            {
                try{
                    this.sock.close();
                }catch (IOException ioe){

                }
                System.out.println("Client disconnected");
            }
        }
    }

    private void handle(InputStream input,OutputStream output) throws IOException{
        System.out.println("Process new http request...");
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        var writer = new BufferedWriter(new OutputStreamWriter(output,StandardCharsets.UTF_8));

        boolean requestOk = false;
        String first = reader.readLine();
        if (first.startsWith("GET/HTTP/1.")){
            requestOk = true;
        }
        for(;;){
            String header = reader.readLine();
            if(header.isEmpty()){
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK": "Response Error");

        if(!requestOk){
            writer.write("HTTP/1.0 404 Not Found \r\n");
            writer.write("Content-Length:0\r\n");
            writer.write("\r\n");
        }else{
            String data = "<html><body><h1>Hello,world!</h1></body></html>";

            int length = data.getBytes(StandardCharsets.UTF_8).length;

            writer.write("HTTP/1.0 200OK\r\n");
            writer.write("Connection:close\r\n");
            writer.write("Content-Type:text/html\r\n");
            writer.write("Content-lengthL"+length+"\r\n");
            writer.write("\r\n");

            writer.write(data);
        }
        writer.flush();
    }

}
