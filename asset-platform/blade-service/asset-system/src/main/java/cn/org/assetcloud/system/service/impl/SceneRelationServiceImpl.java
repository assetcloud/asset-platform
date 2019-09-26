package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.SceneRelation;
import cn.org.assetcloud.system.mapper.SceneRelationMapper;
import cn.org.assetcloud.system.service.ISceneRelationService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-29
 */
@Service
@AllArgsConstructor
public class SceneRelationServiceImpl extends ServiceImpl<SceneRelationMapper, SceneRelation> implements ISceneRelationService {

    @Override
    public boolean saveBatch(Long rid, List<String> userIds) {
        List<SceneRelation> relations = new ArrayList<>();
        userIds.forEach(map -> {
            SceneRelation record = new SceneRelation();
            record.setUid(map);
            record.setRid(rid);
            relations.add(record);
        });
        return this.saveOrUpdateBatch(relations);
    }

    @Override
    public boolean removeBatch(Long rid, List<String> userIds) {
        return this.update(Wrappers.<SceneRelation>update().lambda()
                .in(SceneRelation::getUid, userIds)
                .eq(SceneRelation::getRid, rid)
                .set(SceneRelation::getIsDeleted, 1));
    }
}
