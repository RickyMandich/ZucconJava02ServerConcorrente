import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        Socket client = new Socket(InetAddress.getByName("127.0.0.1"), 20000);
        System.out.println("Apertura connessione");
        InputStream in = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        OutputStream out = client.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        String s = br.readLine();

        while (true){

            System.out.println("Risposta dal server: " + s);

            s = scan.nextLine();

            bw.write(s + "\n");
            bw.flush();

            if(s.equals("termina connessione")) {
                br.close();
                bw.close();
                client.close();
                break;
            }

            System.out.println("Data rischiesta al server");
            s = br.readLine();
            System.out.println("Risposta dal server: "+s);
        }
    }
}
