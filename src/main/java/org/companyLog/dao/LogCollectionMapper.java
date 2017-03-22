package org.companyLog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.companyLog.bean.LogCollection;

public interface LogCollectionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_collection
     *
     * @mbggenerated Sun Mar 19 00:34:47 CST 2017
     */
    @Delete({
        "delete from log_collection",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_collection
     *
     * @mbggenerated Sun Mar 19 00:34:47 CST 2017
     */
    @Insert({
        "insert into log_collection (id, user_id, ",
        "log_id, create_time)",
        "values (#{id,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
        "#{logId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(LogCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_collection
     *
     * @mbggenerated Sun Mar 19 00:34:47 CST 2017
     */
    @Select({
        "select",
        "id, user_id, log_id, create_time",
        "from log_collection",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_id", property="logId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    LogCollection selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_collection
     *
     * @mbggenerated Sun Mar 19 00:34:47 CST 2017
     */
    @Select({
        "select",
        "id, user_id, log_id, create_time",
        "from log_collection"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_id", property="logId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<LogCollection> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_collection
     *
     * @mbggenerated Sun Mar 19 00:34:47 CST 2017
     */
    @Update({
        "update log_collection",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "log_id = #{logId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(LogCollection record);
    
    //自定义
    @Select({
    	"select",
        "id, user_id, log_id, create_time",
        "from log_collection",
        "where log_id = #{0,jdbcType=VARCHAR}",
        "and user_id = #{1,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_id", property="logId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    LogCollection selectByLogIdAndUserId(String logId,String userId);
}