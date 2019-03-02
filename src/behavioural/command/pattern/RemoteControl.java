package behavioural.command.pattern;

//invoker
public class RemoteControl {
	
	private Command command;
	
	public RemoteControl(Command command) {
		this.command=command;
	}
	
	public void pressButton() {
		command.execute();
	}

}
