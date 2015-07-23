import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TalkServer {
	public static void main(String args[]) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(20);
		ServerSocket server = new ServerSocket(4700);
		while (true) {
			Socket socket = server.accept();
			es.submit(new DoTransfer(socket));
		}
	}

	static class DoTransfer implements Runnable {
		private Socket skt;

		public DoTransfer(Socket skt) {
			this.skt = skt;
		}

		public void run() {
			try {
				BufferedReader clntSend = new BufferedReader(
						new InputStreamReader(skt.getInputStream()));
				BufferedWriter svrSend = new BufferedWriter(
						new OutputStreamWriter(skt.getOutputStream()));

				for (int i = 0; i < 10; i++) {
					svrSend.write(Thread.currentThread().getName() + "--->" + i);
					svrSend.flush();
//					clntSend.readLine();
					System.out.println("Server port: " + skt.getPort());
					System.out.println("ServerSocket port: "
							+ skt.getLocalPort());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}