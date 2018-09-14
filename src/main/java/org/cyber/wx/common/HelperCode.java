/**
 * Copyright 2018
 * <p>
 *  Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.cyber.wx.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 *
 *
 * @date 2016年10月27日 下午9:59:27
 */
public class HelperCode extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public HelperCode() {

		put("helper_code", 200);
		put("helper_msg", "success");
	}
	
	public static HelperCode error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static HelperCode error(String msg) {
		return error(500, msg);
	}
	
	public static HelperCode error(int code, String msg) {
		HelperCode r = new HelperCode();
		r.put("helper_code", code);
		r.put("helper_msg", msg);
		return r;
	}

	public static HelperCode ok(String msg) {
		HelperCode r = new HelperCode();
		r.put("helper_msg", msg);
		return r;
	}
	
	public static HelperCode ok(Map<String, Object> map) {
		HelperCode r = new HelperCode();
		r.putAll(map);
		return r;
	}
	
	public static HelperCode ok() {
		return new HelperCode();
	}

	@Override
	public HelperCode put(String key, Object value) {
		super.put(key, value);
		return this;
	}


	public HelperCode putMap (Map<String,Object> map) {
		super.putAll(map);
		return this;

	}

}
