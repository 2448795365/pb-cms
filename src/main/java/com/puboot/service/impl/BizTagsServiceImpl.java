package com.puboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puboot.common.util.PageUtil;
import com.puboot.common.util.Pagination;
import com.puboot.mapper.BizTagsMapper;
import com.puboot.model.BizTags;
import com.puboot.service.BizTagsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Linzhaoguan
 * @version V1.0
 * @date 2019年9月11日
 */
@Service
@AllArgsConstructor
public class BizTagsServiceImpl extends ServiceImpl<BizTagsMapper, BizTags> implements BizTagsService {

    private final BizTagsMapper bizTagsMapper;

    @Override
    public List<BizTags> selectTags(BizTags bizTags) {
        return bizTagsMapper.selectTags(null, bizTags);
    }

    @Override
    public IPage<BizTags> pageTags(BizTags bizTags, Integer limit, Integer offset) {
        IPage<BizTags> page = new Pagination<>(PageUtil.getPageNo(limit, offset), limit);
        return page.setRecords(bizTagsMapper.selectTags(page, bizTags));
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return bizTagsMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
