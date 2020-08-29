package com.revature.util;

import junit.framework.TestCase;

import java.sql.Connection;

public class ConnectionFactoryTest extends TestCase {

    public void testGetConnection() {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        assertNotNull(conn);
    }
}