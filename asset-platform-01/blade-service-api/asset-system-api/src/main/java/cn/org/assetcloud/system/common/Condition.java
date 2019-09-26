package cn.org.assetcloud.system.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;

import java.util.Map;

/**
 * 条件构造器
 */
public class Condition {
    public Condition() {
    }

    public static <T> IPage<T> getPage(Query query) {
        Page page = new Page((long) Func.toInt(query.getPage(), 1), (long) Func.toInt(query.getSize(), 10));
        page.setAsc(Func.toStrArray(query.getAscs()));
        page.setDesc(Func.toStrArray(query.getDescs()));
        return page;
    }

    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper(entity);
    }

    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {
        query.remove("page");
        query.remove("size");
        QueryWrapper<T> qw = new QueryWrapper();
        qw.setEntity(BeanUtil.newInstance(clazz));
        if (Func.isNotEmpty(query)) {
            query.forEach((k, v) -> {
                if (Func.isNotEmpty(v)) {
                    qw.like(StringUtil.humpToUnderline(k), v);
                }

            });
        }

        return qw;
    }
}
