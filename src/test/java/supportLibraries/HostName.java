package supportLibraries;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostName {
	public String hostName() {
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current System IP Address::::" + ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		return ip.getHostAddress();
	}	
}
