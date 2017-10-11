package tn.esprit.utile;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



public class EncrypterClass {

	  private static final int iterations = 20000;
	    private static final int saltLen = 64;
	    private static final int desiredKeyLen = 512;

	    private static String hash(String value, byte[] salt) throws Exception 
	    {
	        if (value == null || value.length() == 0)
	            throw new IllegalArgumentException("Empty passwords are not supported.");
	        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        SecretKey key = f.generateSecret(new PBEKeySpec(value.toCharArray(), salt, iterations, desiredKeyLen));
	        return Base64.getEncoder().encodeToString(key.getEncoded());
	    }

	    public static class Password
	    {
	        public static String getSaltedPassword( String password ) throws Exception 
	        {
	            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);        
	            return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
	        }

	        public static boolean match(String password, String saltedPassword) throws Exception
	        {
	            String[] saltAndPass = saltedPassword.split("\\$");
	            if (saltAndPass.length != 2) 
	            {
	                return false;
	            }
	            String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));
	            return hashOfInput.equals(saltAndPass[1]);
	        }
	    } 
	
}
