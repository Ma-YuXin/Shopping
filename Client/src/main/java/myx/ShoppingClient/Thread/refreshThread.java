package myx.ShoppingClient.Thread;

public class refreshThread extends Thread {

	public void run() {
		while (true) {

			try {
				sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
