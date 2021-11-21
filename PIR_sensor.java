package week9;



import java.util.List;

import org.ws4d.coap.core.enumerations.CoapMediaType;
import org.ws4d.coap.core.rest.BasicCoapResource;
import org.ws4d.coap.core.rest.CoapData;
import org.ws4d.coap.core.tools.Encoder;


public class PIR_sensor extends BasicCoapResource{
	
	private String value = "0";
	PIR2 pir = new PIR2();
	
	private PIR_sensor(String path, String value, CoapMediaType mediaType) {
		super(path, value, mediaType);
	}

	public PIR_sensor() {
		this("/pir", "0", CoapMediaType.text_plain);
	}

	@Override
	public synchronized CoapData get(List<String> query, List<CoapMediaType> mediaTypesAccepted) {
		return get(mediaTypesAccepted);
	}
	
	@Override
	public synchronized CoapData get(List<CoapMediaType> mediaTypesAccepted) {
		String sensing_data = pir.getData();
		if(sensing_data.equals("braekin")) {
			this.value = "breakin";
		}else if(sensing_data.equals("empty")) {
			this.value = "empty";
		}
		return new CoapData(Encoder.StringToByte(this.value), CoapMediaType.text_plain);
	}
	@Override
	public synchronized String getResourceType() {
		return "Raspberry pi 4 PIR Sensor";
	}
	public synchronized void optional_change() {
		String sensing_data = pir.getData();
		String p=sensing_data;
		if(sensing_data==this.value) {
			System.out.println(sensing_data);
		}
		else {
			this.value=p;
			this.changed(this.value);
		}
	}
}
