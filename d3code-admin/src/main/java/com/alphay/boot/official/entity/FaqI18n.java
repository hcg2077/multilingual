package com.alphay.boot.official.entity;

import com.alphay.boot.official.annotation.TranslationField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("off_faq_i18n")
public class FaqI18n {

    /**
     * ID 主键
     */
    @ApiModelProperty(name = "分类国际化ID",required = true)
    @TableId(value = "i18n_id",type = IdType.AUTO)
    private Integer i18nId;

    @ApiModelProperty(name = "问答ID",required = true)
    @TableField(value = "faq_id")
    private Integer faqId;

    @ApiModelProperty(name = "语言标志",required = true)
    @TableField(value = "lang")
    private String lang;

    @TranslationField
    @ApiModelProperty(name = "常见问题概述",required = true)
    @TableField(value = "faq_title")
    private String faqTitle;

    @TranslationField
    @ApiModelProperty(name = "常见问题详细",required = true)
    @TableField(value = "faq_question")
    private String faqQuestion;

    @TranslationField
    @ApiModelProperty(name = "常见问题解答",required = true)
    @TableField(value = "faq_answers")
    private String faqAnswers;

}
