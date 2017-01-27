import java.io.*;
import javax.xml.bind.*;
import java.security.*;


public class DigestProducer implements Runnable {
	
	private String filename;
	private DigestReceiver digestCallback;
	
	public DigestProducer(String filename, DigestReceiver digestCallback) {
		this.filename = filename;
		this.digestCallback = digestCallback;
	
	}

	@Override
	public void run() {
	
		try{
			
			FileInputStream input = new FileInputStream(filename);
			MessageDigest sha_256 = MessageDigest.getInstance("SHA-256");
			DigestInputStream digestInput = new DigestInputStream(input, sha_256);
			
			while (digestInput.read() != -1) ;
			
				digestInput.close();
				byte[] digest_array = sha_256.digest();
				
			//send digest array  and filename back to DigestMessage class
				digestCallback.obtainDigest(filename, digest_array);
			
		}catch(IOException e){
			System.err.println(e);
			System.out.println("Problem with file name");
			
		}catch(NoSuchAlgorithmException ex){
			System.err.println(ex);
		}	
		
		
		
	}

}
