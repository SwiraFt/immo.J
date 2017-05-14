package exceptions;

public class DataBaseException extends Exception{
	
	String message;
	
	public DataBaseException(String message){
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return this.message;
	}

}
