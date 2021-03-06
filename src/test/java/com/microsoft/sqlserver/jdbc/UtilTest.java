/*
 * Microsoft JDBC Driver for SQL Server Copyright(c) Microsoft Corporation All rights reserved. This program is made
 * available under the terms of the MIT License. See the LICENSE file in the project root for more information.
 */
package com.microsoft.sqlserver.jdbc;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


/**
 * Tests the Util class
 *
 */
@RunWith(JUnitPlatform.class)
@Tag("AzureDWTest")
public class UtilTest {

    @Test
    public void readGUIDtoUUID() throws SQLException {
        UUID expected = UUID.fromString("6F9619FF-8B86-D011-B42D-00C04FC964FF");
        byte[] guid = new byte[] {-1, 25, -106, 111, -122, -117, 17, -48, -76, 45, 0, -64, 79, -55, 100, -1};
        assertEquals(expected, Util.readGUIDtoUUID(guid));
    }

    @Test
    public void testLongConversions() {
        writeAndReadLong(Long.MIN_VALUE);
        writeAndReadLong(Long.MIN_VALUE / 2);
        writeAndReadLong(-1);
        writeAndReadLong(0);
        writeAndReadLong(1);
        writeAndReadLong(Long.MAX_VALUE / 2);
        writeAndReadLong(Long.MAX_VALUE);
    }

    private void writeAndReadLong(long valueToTest) {
        byte[] buffer = new byte[8];
        Util.writeLong(valueToTest, buffer, 0);
        long newLong = Util.readLong(buffer, 0);
        assertEquals(valueToTest, newLong);
    }

}
