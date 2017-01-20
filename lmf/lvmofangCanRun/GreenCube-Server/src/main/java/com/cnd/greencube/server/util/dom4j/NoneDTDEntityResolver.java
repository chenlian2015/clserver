/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.util.dom4j;

import java.io.Serializable;
import java.io.StringBufferInputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * XML文档解析
 * @author huxg
 *
 */
@SuppressWarnings("serial")
public class NoneDTDEntityResolver implements EntityResolver, Serializable {
	@SuppressWarnings("deprecation")
	public InputSource resolveEntity(String publicId, String systemId) {
		return new InputSource(new StringBufferInputStream(""));
	}
}
