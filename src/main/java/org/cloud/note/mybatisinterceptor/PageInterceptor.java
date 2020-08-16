package org.cloud.note.mybatisinterceptor;

import lombok.SneakyThrows;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author wangqianlong
 * @create 2020-08-02 13:19
 */
@Intercepts(value = {@Signature(type = StatementHandler.class, method = "prepare"
        , args = {Connection.class, Integer.class})
})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println();
        return invocation.proceed();
    }

    @SneakyThrows
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            BoundSql boundSql = statementHandler.getBoundSql();
            Class clazz = BoundSql.class;
            Field field = null;
            field = clazz.getDeclaredField("sql");
            field.setAccessible(true);
            String sql = (String) field.get(boundSql);
            String newSql =sql.concat(" or 1=1");
            field.set(boundSql, newSql);
        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
