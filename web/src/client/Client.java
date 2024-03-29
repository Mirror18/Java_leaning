import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost",8080);

        try (InputStream input = sock.getInputStream()){
            try (OutputStream output = sock.getOutputStream()){
                handle(input,output);
            }
        }
        sock.close();
        System.out.println("disconnected");

    }

    private static void handle(InputStream input,OutputStream output) throws IOException{
        var writer =new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        var reader =new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        Scanner scanner = new Scanner(System.in);
        System.out.println("[server]"+reader.readLine());
        for(;;){
            System.out.println(">>>");
            String s = scanner.nextLine();
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<<" + resp);
            if(resp.equals("bye")){
                break;
            }
        }
    }



}
