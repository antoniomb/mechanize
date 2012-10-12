package com.gistlabs.mechanize.query;



/**
 * @author Martin Kersten <Martin.Kersten.mk@gmail.com>
 */
public class QueryBuilder extends AbstractQueryBuilder {

	public static Query everything() {
		return new Query().everything();
	}

	public static Query inBrackets(Query query) {
		return new Query().inBrackets(query);
	}

	public static Query not(Query query) {
		return new Query().not(query);
	}

	public static Query by(String attribute, String value) {
		return new Query().by(attribute, value);
	}

	public static Query by(String attribute, Pattern pattern) {
		return new Query().by(attribute, pattern);
	}

	public static Query by(String [] attributeNames, String value) {
		return new Query().by(attributeNames, value);
	}

	public static Query by(String [] attributeNames, Pattern pattern) {
		return new Query().by(attributeNames, pattern);
	}

	public static Query byAny(String string) {
		return new Query().byAny(string);
	}

	public static Query byAny(Pattern pattern) {
		return new Query().byAny(pattern);
	}

	public static Query byNodeName(String string) {
		return new Query().byNodeName(string);
	}

	public static Query byNodeName(Pattern pattern) {
		return new Query().byNodeName(pattern);
	}

	public static Query byNodeValue(String string) {
		return new Query().byNodeValue(string);
	}

	public static Query byNodeValue(Pattern pattern) {
		return new Query().byNodeValue(pattern);
	}
}