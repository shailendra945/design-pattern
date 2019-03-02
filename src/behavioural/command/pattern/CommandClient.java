package behavioural.command.pattern;

public class CommandClient {

	public static void main(String[] args) {
		
		Light light=new Light();
		LightOnCommand litOnCmd=new LightOnCommand(light);
		LightOffCommand litOffCmd=new LightOffCommand(light);
		
		//switch on light
		RemoteControl rctrl=new RemoteControl(litOnCmd);
		rctrl.pressButton();
		System.out.println("is light on :"+light.isOn());
		
		//switch off light
		RemoteControl rctrl1=new RemoteControl(litOffCmd);
	    rctrl1.pressButton();
	    System.out.println("is light off :"+!light.isOn());
		
	}

}
