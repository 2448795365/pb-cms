package com.puboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.puboot.common.util.ResultUtil;
import com.puboot.model.BizTags;
import com.puboot.service.BizTagsService;
import com.puboot.vo.base.PageResultVo;
import com.puboot.vo.base.ResponseVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 后台标签配置
 *
 * @author Linzhaoguan
 * @version V1.0
 * @date 2019年9月11日
 */
@Controller
@RequestMapping("tag")
@AllArgsConstructor
public class TagController {

    private final BizTagsService tagsService;


    @PostMapping("list")
    @ResponseBody
    public PageResultVo loadTags(BizTags bizTags, Integer limit, Integer offset) {
        IPage<BizTags> bizTagsPage = tagsService.pageTags(bizTags, limit, offset);
        return ResultUtil.table(bizTagsPage.getRecords(), bizTagsPage.getTotal());
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(BizTags bizTags) {
        Date date = new Date();
        bizTags.setCreateTime(date);
        bizTags.setUpdateTime(date);
        boolean i = tagsService.save(bizTags);
        if (i) {
            return ResultUtil.success("新增标签成功");
        } else {
            return ResultUtil.error("新增标签失败");
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, Integer id) {
        BizTags bizTags = tagsService.getById(id);
        model.addAttribute("tag", bizTags);
        return "tag/detail";
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(BizTags bizTags) {
        bizTags.setUpdateTime(new Date());
        boolean i = tagsService.updateById(bizTags);
        if (i) {
            return ResultUtil.success("编辑标签成功");
        } else {
            return ResultUtil.error("编辑标签失败");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(Integer id) {
        boolean i = tagsService.removeById(id);
        if (i) {
            return ResultUtil.success("删除标签成功");
        } else {
            return ResultUtil.error("删除标签失败");
        }
    }

    @PostMapping("/batch/delete")
    @ResponseBody
    public ResponseVo deleteBatch(@RequestParam("ids[]") Integer[] ids) {
        int i = tagsService.deleteBatch(ids);
        if (i > 0) {
            return ResultUtil.success("删除标签成功");
        } else {
            return ResultUtil.error("删除标签失败");
        }
    }
}
