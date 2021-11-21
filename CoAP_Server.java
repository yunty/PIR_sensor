package week9;

import org.ws4d.coap.core.rest.CoapResourceServer;

public class CoAP_Server {
	private static CoAP_Server coapServer;
	private CoapResourceServer resourceServer;
	
	public static void main(String[] args) {
		coapServer = new CoAP_Server();
		coapServer.start();
	}

	public void start() {
		System.out.println("===Run Test Server ===");
		
		if (this.resourceServer != null)	this.resourceServer.stop();
		this.resourceServer = new CoapResourceServer();

		LED led = new LED();
		PIR_sensor pir_sensor = new PIR_sensor();
		pir_sensor.registerServerListener(resourceServer);
		this.resourceServer.createResource(led);
		this.resourceServer.createResource(pir_sensor);
		
		try {
			this.resourceServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				Thread.sleep(3000);
				pir_sensor.optional_change();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}

