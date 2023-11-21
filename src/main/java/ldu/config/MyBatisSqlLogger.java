package ldu.config;


import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisSqlLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.getMappedStatements().forEach(mappedStatement -> {
            SqlSource sqlSource = mappedStatement.getSqlSource();
            if (sqlSource instanceof DynamicSqlSource) {
                BoundSql boundSql = sqlSource.getBoundSql(null);
                String sql = boundSql.getSql();
                logger.debug("Mapped Statement ID: {}, SQL: {}", mappedStatement.getId(), sql);
            }
        });
    }
}