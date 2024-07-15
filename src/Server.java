import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Порт сервера: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое соединение установлено!");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String name = in.readLine();
                System.out.println("Получено: подключен " + name + " c порта " + clientSocket.getPort());

                out.println(String.format("Привет %s, порт вашего соединения %d", name, clientSocket.getPort()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
