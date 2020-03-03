package com.secondhand.util.support;

import java.io.Serializable;

public interface IResultCode extends Serializable {

    String getMessage();

    int getCode();

}
