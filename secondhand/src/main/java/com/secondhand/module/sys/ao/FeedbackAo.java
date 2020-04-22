package com.secondhand.module.sys.ao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * @author Erica
 * @since 2020/4/19
 */
@Data
public class FeedbackAo {
    @NotEmpty(message = "ids集合不能为空")
    private List<Long> ids;
}
