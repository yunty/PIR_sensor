package week9;


import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

public class PIR2 {

	private GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalInput pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25);
	private static final int MAXTIMINGS  = 85;
	public PIR2() {
        
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }
        GpioUtil.export(3, GpioUtil.DIRECTION_OUT);
    }
	public String getData() {
        
		int laststate = Gpio.LOW;
		int counter = 0;
		String p="empty";
		for (int i = 0; i < MAXTIMINGS; i++) {
            while (Gpio.digitalRead(25) == laststate) {
                counter++;
                Gpio.delayMicroseconds(1);
                if (counter == 255) {
                    break;
                }
            }
            laststate = Gpio.digitalRead(25);
            if (counter == 255) {
                break;
            }
		}
		if(pir.isHigh()) {
			p="breakin";
		}
		return p;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
