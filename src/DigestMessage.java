import java.io.File;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

public class DigestMessage implements DigestReceiver {

	private static String filename;
	private byte[] digestData;



	public static void main(String[] args) {
		System.out.println("Enter absolute path of file: ");
		Scanner sc = new Scanner(System.in);
		filename = sc.nextLine();
		sc.close();

		DigestMessage dMessage = new DigestMessage();
		dMessage.produceDigest();

	}
   //start thread to produce digest
	public void produceDigest() {
		DigestProducer producer = new DigestProducer(filename, this);
		Thread thread = new Thread(producer);
		thread.start();
	}
	//print out filename and corresponding digest message
	@Override
	public String toString() {

		String result = "";
		
		if (digestData != null) {
			result = filename + ": " + DatatypeConverter.printHexBinary(digestData);
		} else {
			result = ("Unable to produce digest. Try again or check filename");
		}
		return result;

	}
	//method for receiving digest data with the filename
	@Override
	public void obtainDigest(String filename, byte[] data) {
		filename = this.filename;
		this.digestData = data;
		System.out.println(this);

	}

}
