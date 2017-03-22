package org.companyLog.provider;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.SqlBuilder;

import com.mysql.jdbc.StringUtils;

/**
 * @TODO：
 * @fileName : org.companyLog.provider.SqlProvider.java
 * date | author | version |   
 * 2017年3月17日 | Jiong | 1.0 |
 */
public class SqlSelectProvider {
	
	public SqlSelectProvider() {
		// TODO Auto-generated constructor stub
	}
	
    @SuppressWarnings("unchecked")
	public String select(Map<String,Object> param){
//    	return "select * from log";
    	Map<String,String> eqCondition 
    		= (Map<String, String>) param.get("eqCondition");
    	Map<String,String> likeCondition 
		= (Map<String, String>) param.get("likeCondition");
    	String order = (String) param.get("order");
    	String table = (String) param.get("table");
    	String colums = (String) param.get("colums");
    	//整型出错
//    	Integer index = Integer.valueOf((String) param.get("index"));
//    	Integer rows = Integer.valueOf((String) param.get("rows"));
    	String index = (String) param.get("index");
    	String rows = (String) param.get("rows");
    	
    	if(StringUtils.isNullOrEmpty(colums)){
    		colums = "*";
    	}
    	SQL sql = new SQL().SELECT(colums).FROM(table);
        if(null != eqCondition){
        	for(String key:eqCondition.keySet()){
        		if(!StringUtils.isNullOrEmpty(eqCondition.get(key))){
        			sql.WHERE(key+" = "+eqCondition.get(key));
        		}
        		
        	}
        }
        if(null != likeCondition){
        	for(String key:likeCondition.keySet()){
        		if(!StringUtils.isNullOrEmpty(likeCondition.get(key))){
        			sql.WHERE(key+" like "+likeCondition.get(key));
        		}
        	}
        }
        if(!StringUtils.isNullOrEmpty(order)){
        	sql.ORDER_BY(order);
        }
        String sqlStr = sql.toString();
        if(!StringUtils.isNullOrEmpty(index) && !StringUtils.isNullOrEmpty(rows)){
        	sqlStr += " limit "+index+","+rows;
        }
        System.out.println("-----sqlSelectProvider:"+sqlStr);
        return sqlStr;
    }
    
    
    @SuppressWarnings("unchecked")
   	public String selectCount(Map<String,Object> param){
//       	return "select * from log";
       	Map<String,String> eqCondition 
       		= (Map<String, String>) param.get("eqCondition");
       	Map<String,String> likeCondition 
   		= (Map<String, String>) param.get("likeCondition");
       	String table = (String) param.get("table");
       	//整型出错
       	SQL sql = new SQL().SELECT("count(1)").FROM(table);
           if(null != eqCondition){
           	for(String key:eqCondition.keySet()){
           		sql.WHERE(key+" = "+eqCondition.get(key));
           		
           	}
           }
           if(null != likeCondition){
           	for(String key:likeCondition.keySet()){
           		sql.WHERE(key+" like "+likeCondition.get(key));
           	}
           }
           String sqlStr = sql.toString();
           System.out.println("-----sqlSelectProvider:"+sqlStr);
           return sqlStr;
       }
}

