package PramodDutta;import static io.restassured.RestAssured.*;
public class Message {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

// create Java object
	public static void main(String[] args) {
		Message m = new Message();
		m.setMessage("Hello");
	

	// restAssured
	
	Message msg= new Message();
	msg.setMessage("My message");
	
	 given().body(m).when().post("/Message");
	
	}
}