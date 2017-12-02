package com.cuwallet.repository.dao;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cuwallet.commons.exceptions.ErrorCode;
import com.cuwallet.commons.exceptions.InvalidRequestException;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.utils.Bytes;

public class CassandraUtils {
	
	public static String getStringFromCQLBlob(ByteBuffer blob) {
		return Bytes.toHexString(blob).split("x")[1];
	}
	
	public static ByteBuffer getCQLBlobFromHexString(String hexString) {
		return Bytes.fromHexString("0x" + hexString);
	}

	public static void ifParentAndChildPogIdsSame(Long parentPogId,Long childPogId)
	{
		if(parentPogId.equals(childPogId))
		{
			throw new InvalidRequestException(
					ErrorCode.INPUT_INVALID_PAYLOAD,"");	
		}
	}

	public static void checkRowExist(ResultSet result)
	{
		List<Row> rows = result.all();
		if(rows.size()>0)
		{
			throw new InvalidRequestException(
					ErrorCode.INPUT_INVALID_PAYLOAD,"");
		}
	}
	public static List<Row> extractRowsIfExists(ResultSet result) {
		List<Row> rows = result.all();
		if (rows.size() == 0) {
			throw new InvalidRequestException(
					ErrorCode.INPUT_INVALID_PAYLOAD,"");
		}
		return rows;
	}
	public static void isProductNameValid(String productName)
	{
		if(productName==null)
		{
			throw new InvalidRequestException(
					ErrorCode.POGID_NOT_EXISTS,"");	
		}
	}
	public static <T> void insertIfNotNull(Insert insertStatement, String columnName, T valueToInsert){
		if(valueToInsert != null)
			insertStatement.value(columnName, valueToInsert);
	}
	
	public static <T> void updateIfNotNull(Update updateStatement, String columnName, T valueToUpdate){
		if(valueToUpdate != null)
			updateStatement.with(QueryBuilder.set(columnName, valueToUpdate));
	}
	public static Set<String> getSetStringFromSetCQLBlob(Set<ByteBuffer> set) {
		Set<String> temp = new HashSet<String>();
		Iterator<ByteBuffer> it = set.iterator();
		while (it.hasNext()) {
			//ByteBuffer bf=ByteBuffer.wrap((it.next()).getBytes());
			temp.add(CassandraUtils.getStringFromCQLBlob(it.next()));
		}
		return temp;
	}
}
