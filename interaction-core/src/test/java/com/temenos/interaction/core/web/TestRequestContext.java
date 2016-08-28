package com.temenos.interaction.core.web;

/*
 * #%L
 * interaction-core
 * %%
 * Copyright (C) 2012 - 2013 Temenos Holdings N.V.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestRequestContext {

    @Test
    public void testInexistentHeader() {
         Map<String, List<String>> headers = new HashMap<>();
         List<String> singleElementList = new ArrayList<>();
         singleElementList.add("value0");
         headers.put("header0", singleElementList);
         
         RequestContext ctx = new RequestContext("\basepath", "\requesturi", null, headers);
         
         assertTrue(ctx.getHeaders("header1").isEmpty());
         assertNull(ctx.getFirstHeader("header1"));
    }

	@Test
	public void testSingleValueHeader() {
	     Map<String, List<String>> headers = new HashMap<>();
	     List<String> singleElementList = new ArrayList<>();
	     singleElementList.add("value0");
	     headers.put("header0", singleElementList);
	     
	     RequestContext ctx = new RequestContext("\basepath", "\requesturi", null, headers);
	     
	     assertEquals(1, ctx.getHeaders("header0").size());
	     assertEquals("value0", ctx.getHeaders("header0").get(0));
	     assertEquals("value0", ctx.getFirstHeader("header0"));
	}
	
    @Test
    public void testMultiValueHeader() {
        Map<String, List<String>> headers = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("value0");
        list.add("value1");
        list.add("value2");
        headers.put("header0", list);

        RequestContext ctx = new RequestContext("\basepath", "\requesturi", null, headers);

        assertEquals(3, ctx.getHeaders("header0").size());
        assertEquals("value0", ctx.getHeaders("header0").get(0));
        assertEquals("value1", ctx.getHeaders("header0").get(1));
        assertEquals("value2", ctx.getHeaders("header0").get(2));
        assertEquals("value0", ctx.getFirstHeader("header0"));
    }
}
