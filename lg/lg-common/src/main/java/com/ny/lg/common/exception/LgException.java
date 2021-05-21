package com.ny.lg.common.exception;


import com.ny.lg.common.constants.BizCode;

public class LgException extends RuntimeException {
    private BizCode bizCode;

    public LgException(BizCode bizCode) {
        super(bizCode.getMessage());
        this.bizCode = bizCode;
    }

    public LgException(BizCode bizCode, Exception e) {
        super(e);
        this.bizCode = bizCode;
    }

    public BizCode getBizCode() {
        return bizCode;
    }
}
