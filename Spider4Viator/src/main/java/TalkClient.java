import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TalkClient {
	public static void main(String args[]) throws Exception {
		
		Socket socket = new Socket("127.0.0.1", 4700);
		BufferedWriter clntSend = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		BufferedReader svrReturn = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String readline = br.readLine(); // 从系统标准输入读入一字符串
		while (!readline.equals("bye")) {
			clntSend.write(readline);
			clntSend.flush();
			System.out.println("Client send:" + readline);
			Thread.sleep(1000);
			System.out.println("Server return:" + svrReturn.readLine());
			System.out.println("Client port: " + socket.getPort());
			readline = br.readLine();
		}
		clntSend.close(); // 关闭Socket输出流
		svrReturn.close(); // 关闭Socket输入流
		socket.close(); // 关闭Socket
	}
}