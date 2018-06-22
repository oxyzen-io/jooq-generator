/**
 * Copyright (C) 2015 Red Siwon Choi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zoyi.jooq.lib;

import org.javalite.common.Inflector;
import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.tools.StringUtils;

/**
 * Created by red on 11/14/15.
 */
public class JooqGeneratorStrategy extends DefaultGeneratorStrategy {
  /**
   * Override this method to define how your Java classes and Java files should
   * be named. This example applies no custom setting and uses CamelCase versions
   * instead
   */
  @Override
  public String getJavaClassName(Definition definition, Mode mode) {

    StringBuilder result = new StringBuilder();

    // [#4562] Some characters should be treated like underscore
    result.append(Inflector.singularize(StringUtils.toCamelCase(
        definition.getOutputName()
            .replace(' ', '_')
            .replace('-', '_')
            .replace('.', '_')
    )));

    if (mode == Mode.RECORD) {
      result.append("Record");
    }
    else if (mode == Mode.DAO) {
      result.append("Dao");
    }
    else if (mode == Mode.INTERFACE) {
      result.insert(0, "I");
    }

    return result.toString();
  }
}
