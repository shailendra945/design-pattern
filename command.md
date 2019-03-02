The Command, which allows the requester of a particular action to be decoupled from the object that performs the action. Where the Chain of Responsibility pattern forwarded requests along a chain, the Command pattern forwards the request to a specific module.

Command in the Real World :
-----------------------------
One example of the command pattern being executed in the real world is the idea of a table order at a restaurant: the waiter takes the order, which is a command from the customer.This order is then queued for the kitchen staff.  The waiter tells the chef that the a new order has come in, and the chef has enough information to cook the meal.The Command Pattern
The Command pattern is known as a behavioural pattern,as it's used to manage algorithms, relationships and responsibilities between objects. Thedefinition of Command provided in the original Gang of Four book on DesignPatterns states: 

Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations

So what does this mean in a class diagram? 

refer command class diagram image.



Command declares an interface for all commands, providing a simple execute() method which asks the Receiver of the command to carry out an operation. The Receiver has the knowledge of what to do to carry out the request.  The Invoker holds a command and can get the Command to execute a request by calling the execute method. The Client creates ConcreteCommands and sets a Receiver for the command. The ConcreteCommand defines a binding between the action and the receiver. When the Invoker calls execute the ConcreteCommand will run one or more actions on the Receiver.

The following sequence diagram shows the relationship in a clearer way: 

refer command sequence diagram

The Command Pattern is useful when:
------------------------------------------

A history of requests is needed
You need callback functionality
Requests need to be handled at variant times or in variant orders
The invoker should be decoupled from the object handling the invocation.
You'll see command being used a lot when you need to have multiple undo operations, where a stack of the recently executed commands are maintained. To implement the undo, all you need to do is get the last Command in the stack and execute it's undo() method.

You'll also find Command useful for wizards, progress bars, GUI buttons and menu actions, and other transactional behaviour. 

So How Does It Work In Java?
Let's use a remote control as the example. Our remote is the center of home automation and can control everything. We'll just use a light as an example, that we can switch on or off, but we could add many more commands.

First we'll create our command interface:

//Command
public interface Command{
  public void execute();
}


Now let's create two concrete commands. One will turn on the lights, another turns off lights:

//Concrete Command
public class LightOnCommand implements Command{
  //reference to the light
  Light light;
  public LightOnCommand(Light light){
    this.light = light;
  }
  public void execute(){
    light.switchOn();
  }
}


//Concrete Command
public class LightOffCommand implements Command{
  //reference to the light
  Light light;
  public LightOffCommand(Light light){
    this.light = light;
  }
  public void execute(){
    light.switchOff();
  }
}


Light is our receiver class, so let's set that up now:

//Receiver
public class Light{
  private boolean on;
  public void switchOn(){
    on = true;
  }
  public void switchOff(){
    on = false;
  }
}


Our invoker in this case is the remote control.

//Invoker
public class RemoteControl{
  private Command command;
  public void setCommand(Command command){
    this.command = command;
  }
  public void pressButton(){
    command.execute();
  }
}


Finally we'll set up a client to use the invoker

//Client
public class Client{
  public static void main(String[] args)    {
    RemoteControl control = new RemoteControl();
    Light light = new Light();
    Command lightsOn = new LightsOnCommand(light);
    Command lightsOff = new LightsOffCommand(light);
    //switch on
    control.setCommand(lightsOn);
    control.pressButton();
    //switch off
    control.setCommand(lightsOff);
    control.pressButton();
  }
}

Watch Out for the Downsides:
------------------------------
This pattern ends up forcing a lot of Command classes that will make your design look cluttered - more operations being made possible leads to more command classes. Intelligence required of which Command to use and when leads to possible maintainence issues for the central controller.
