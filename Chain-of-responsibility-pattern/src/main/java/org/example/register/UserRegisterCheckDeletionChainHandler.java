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


import org.example.dto.Input;
import org.springframework.stereotype.Component;

/**
 * 用户注册检查证件号是否多次注销
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：12306）获取项目资料
 */
@Component
public final class UserRegisterCheckDeletionChainHandler implements UserRegisterCreateChainFilter<Input> {



    @Override
    public void handler(Input input) {
        System.out.println("UserRegisterCheckDeletionChainHandler已通过");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
