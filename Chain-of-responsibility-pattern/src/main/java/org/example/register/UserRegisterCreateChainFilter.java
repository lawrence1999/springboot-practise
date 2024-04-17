/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.register;


import org.example.chain.AbstractChainHandler;
import org.example.service.MarkEnum;

/**
 * 用户注册责任链过滤器接口
 * 注册责任链具体的组件实现该接口
 */
public interface UserRegisterCreateChainFilter<Object> extends AbstractChainHandler<Object> {

    /**
     * 标记集成此接口的mark，子类无需重写mark()
     * @return
     */
    @Override
    default String mark() {
        return MarkEnum.USER_REGISTER_FILTER.name();
    }
}
