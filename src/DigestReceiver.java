//callback to receive digest message of file
public interface DigestReceiver {
	
	void obtainDigest(String filename, byte[] data);

}
