package com.fms.util;

import com.fms.Exception.UserIdCheckException;
import com.fms.constant.CommonConstant;
import com.fms.context.BaseContext;

import java.util.Objects;

public class ThreadUserIdUtil {
    public static void ThreadUserIdIsEqualsInputId(Integer id) {
        Integer currentId = BaseContext.getCurrentId();
        if (currentId != id) {
            throw new UserIdCheckException(CommonConstant.USERID_ONT_MATCHED);
        }
    }
}
