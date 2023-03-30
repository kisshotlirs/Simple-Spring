package com.mojo.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zyl
 * @description: 资源加载接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
