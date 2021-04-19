package no.hvl.dat110.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash
{

	private static BigInteger hashint;

	public static BigInteger hashOf(String entity)
	{
		// Task: Hash a given string using MD5 and return the result as a BigInteger.

		try
		{
			// we use MD5 with 128 bits digest
			MessageDigest md = MessageDigest.getInstance("MD5");
			// compute the hash of the input 'entity'
			md.update(entity.getBytes());
			byte[] digest = md.digest();

			// convert the hash into hex format
			String hexString = toHex(digest);

			// convert the hex into BigInteger
			hashint = new BigInteger(hexString, 16);
		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return the BigInteger
		return hashint;
	}

	public static BigInteger addressSize()
	{
		// Task: compute the address size of MD5

		// get the digest length
		int digestlen = 0;
		try
		{
			digestlen = MessageDigest.getInstance("MD5").getDigestLength();
			
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// compute the number of bits = digest length * 8
		int numBits = digestlen * 8;
		
		// compute the address size = 2 ^ number of bit
		BigInteger adSize = BigInteger.TWO.pow(numBits);
		
		// return the address size
		return adSize;
	}

	public static int bitSize()
	{
		int digestlen = 0;
		
		// find the digest length
		try
		{
			digestlen = MessageDigest.getInstance("MD5").getDigestLength();
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return digestlen * 8;
	}

	public static String toHex(byte[] digest)
	{
		StringBuilder strbuilder = new StringBuilder();
		for (byte b : digest)
		{
			strbuilder.append(String.format("%02x", b & 0xff));
		}
		return strbuilder.toString();
	}

}
