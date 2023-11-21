package ldu.dao;

import ldu.ldu.entity.Branch;
import ldu.ldu.entity.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component

public interface IDao {

    @Select("SELECT * FROM branch WHERE branch_name like #{name}")
    List<Branch> find(@Param("name") String name);

    @Select("SELECT * FROM branch WHERE branch_city like #{branch_city}")
    List<Branch> findbycity(@Param("branch_city") String branch_city);

    @Insert("INSERT INTO branch ( branch_name,branch_city,assets ) VALUES (#{branch_name},#{branch_city},#{assets})")
    int insert(Branch stu);

    @Delete("DELETE FROM branch WHERE branch_name=#{name}")
    int delete(@Param("name") String name);

    @Update("Update branch set assets=#{ assets}, branch_city=#{branch_city} where branch_name=#{branch_name}")
    int update(Branch stu);

    @Select("select * from branch")
    List<Branch> findall();

    @Select("select * from Users")
    List<Users> findalluser();

    @SelectProvider(type = sqlInject.class, method = "selectBySql")
    List<Users> login(String username, String password);

}
