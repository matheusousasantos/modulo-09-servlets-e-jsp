package test;

import org.junit.Test;

import connection.SingleConnection;

public class TestBanco {
	
	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}

}
