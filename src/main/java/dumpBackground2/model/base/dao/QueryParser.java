package dumpBackground2.model.base.dao;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpBackground2.tools.Util;



public class QueryParser {

	private static final Logger logger = LoggerFactory.getLogger(QueryParser.class);

	public static Map<Integer, String> getSortingMap(String queryTemplate, String pattern) {
	//	logger.info("get_sorting_map_started");
		logger.info("queryTemplate:"+queryTemplate);
		logger.info("Pattern:"+pattern);
		Map<Integer, String> resultMap = null;

		if (Util.isNotNullOrEmpty(queryTemplate)) {
			String queryTemplateTrimmed = queryTemplate.trim().toUpperCase();
			//logger.info("query_trim:"+queryTemplateTrimmed);
			String whereTail = null;
			String withoutTail = null;
			if (!queryTemplateTrimmed.startsWith("INSERT")) {
			whereTail = queryTemplateTrimmed.split("WHERE")[1];
			withoutTail = queryTemplateTrimmed.split("WHERE")[0];
			}
			resultMap = new TreeMap<>();
			Map<Integer, String> fieldsMap = new HashMap<>();
			if (queryTemplateTrimmed.startsWith("SELECT") || queryTemplateTrimmed.startsWith("DELETE")) {
				String bracedIndex = patternMatcher(whereTail,pattern);
				String fieldName = whereTail.split("=")[0].trim();
				String indexString  = bracedIndex.substring(1,bracedIndex.length()-1);
				int counter = Integer.valueOf(indexString);
				fieldsMap.put(counter, fieldName.toLowerCase());
			}
			
			
			if (queryTemplateTrimmed.startsWith("INSERT")) {

				String splittedQuery = queryTemplate.toUpperCase().split("VALUES")[0];
				//logger.info("splittedQuery:"+splittedQuery);
				String csvBraces = patternMatcher(splittedQuery, pattern);
				//logger.info("splittedQuery:"+csvBraces);
				String csvColumnNames = csvBraces.substring(1, csvBraces.length() - 1);

				String[] columnNamesArray = csvColumnNames.split(",");
				int counter = 0;
				
				for (int i = 0; i < columnNamesArray.length; i++) {
					fieldsMap.put(counter, columnNamesArray[i].toLowerCase());
					counter++;
				}
			}
			if(queryTemplateTrimmed.startsWith("UPDATE")) {
				logger.info("QueryTemplate:"+queryTemplateTrimmed);
				logger.info(pattern);
				String updateSetCsv = queryTemplateTrimmed.split("SET")[1];
				updateSetCsv = updateSetCsv.split("WHERE")[0].trim();
				String[] updateSetCsvSplitted = updateSetCsv.split(",");
				for(int i=0;i<updateSetCsvSplitted.length;i++) {
					String fieldNameEquals = updateSetCsvSplitted[i].trim();
					String fieldName = fieldNameEquals.split("=")[0].trim();
					String bracedIndex = patternMatcher(fieldNameEquals,pattern);
					String indexString  = bracedIndex.substring(1,bracedIndex.length()-1);
					int counter = Integer.valueOf(indexString);
					fieldsMap.put(counter, fieldName.toLowerCase());
					
				}
				String bracedIndex = patternMatcher(whereTail,pattern);
				String fieldName = whereTail.split("=")[0].trim();
				String indexString  = bracedIndex.substring(1,bracedIndex.length()-1);
				int counter = Integer.valueOf(indexString);
				fieldsMap.put(counter, fieldName.toLowerCase());
				
			}
			resultMap.putAll(fieldsMap);
		}
		//logger.info("result:"+resultMap);
		//logger.info("get_sorting_map_finished");
		return resultMap;
	}

	private static String patternMatcher(String value, String pattern) {
		//logger.info("Value:"+value);
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(value);
		String resultString = null;
		while (matcher.find()) {
			// logger.info("between_Parentheses_by_matcher:" + matcher.group(0));
			resultString = matcher.group(0).toString();
		}
		return resultString;
	}
	
	public static <T> String buildQueryString(String queryTemplate,String regex, Class<T> type,T object) {
		Map<Integer, String> sortingMap = QueryParser.getSortingMap(queryTemplate, regex);
		
		
		Map<String, Object> annotated = ReflectionExHelper.annotationVsClass(type, object);
		MessageFormat fmt = new MessageFormat(queryTemplate);
		Object[] inputValues = new Object[sortingMap.size()];
		for (Map.Entry<Integer, String> sortingEntry : sortingMap.entrySet()) {
			int place = sortingEntry.getKey();
			String fieldName = sortingEntry.getValue();
			Object value = annotated.get(fieldName);
			inputValues[place] = value;
		}
		String resultString = fmt.format(inputValues);
		logger.info("Result_Query:" + resultString);
		return resultString;
	}

}
