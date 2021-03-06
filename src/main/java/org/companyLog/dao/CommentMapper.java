package org.companyLog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.companyLog.bean.Comment;

public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Wed Mar 15 12:40:08 CST 2017
     */
    @Delete({
        "delete from comment",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Wed Mar 15 12:40:08 CST 2017
     */
    @Insert({
        "insert into comment (id, log_id, ",
        "author_id, create_time, ",
        "reply_id, content, ",
        "reply_content)",
        "values (#{id,jdbcType=VARCHAR}, #{logId,jdbcType=VARCHAR}, ",
        "#{authorId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{replyId,jdbcType=VARCHAR}, #{content,jdbcType=LONGVARCHAR}, ",
        "#{replyContent,jdbcType=LONGVARCHAR})"
    })
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Wed Mar 15 12:40:08 CST 2017
     */
    @Select({
        "select",
        "id, log_id, author_id, create_time, reply_id, content, reply_content",
        "from comment",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="log_id", property="logId", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="reply_id", property="replyId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="reply_content", property="replyContent", jdbcType=JdbcType.LONGVARCHAR)
    })
    Comment selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Wed Mar 15 12:40:08 CST 2017
     */
    @Select({
        "select",
        "id, log_id, author_id, create_time, reply_id, content, reply_content",
        "from comment"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="log_id", property="logId", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="reply_id", property="replyId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="reply_content", property="replyContent", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Comment> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Wed Mar 15 12:40:08 CST 2017
     */
    @Update({
        "update comment",
        "set log_id = #{logId,jdbcType=VARCHAR},",
          "author_id = #{authorId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "reply_id = #{replyId,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR},",
          "reply_content = #{replyContent,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Comment record);
    
    /*-----------------------------------------自定义----------*/

    //分页查询
    @Select({
        "select",
        "c1.id, c1.log_id, c1.author_id, c1.create_time, c1.reply_id, c1.content",
        ",c2.content as reply_content, u1.nickname,u2.nickname as replyName",
        "from comment c1",
        "left join user u1",
        	"on c1.author_id = u1.id",
        "left join comment c2",
        	"on c1.reply_id = c2.id",
        "left join user u2",
        	"on c2.author_id = u2.id",
        "where c1.log_id = #{0,jdbcType=VARCHAR}",
        "order by c1.create_time",
        "limit #{1},#{2}"
    })
    @Results({
    	 @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
         @Result(column="log_id", property="logId", jdbcType=JdbcType.VARCHAR),
         @Result(column="author_id", property="authorId", jdbcType=JdbcType.VARCHAR),
         @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
         @Result(column="reply_id", property="replyId", jdbcType=JdbcType.VARCHAR),
         @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
         
         @Result(column="reply_content", property="replyContent", jdbcType=JdbcType.LONGVARCHAR),
    	 @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
    	 @Result(column="replyName", property="replyName", jdbcType=JdbcType.VARCHAR)
    })
    List<Map<Object,Object>> selectPageByLogId(String logId,int index,int num);
    
  //TODO  查询总数
    @Select({
        "select",
        "count(id)",
        "from comment",
        "where log_id = #{logId,jdbcType=VARCHAR}"
    })
    int countTotal(String logId);
}