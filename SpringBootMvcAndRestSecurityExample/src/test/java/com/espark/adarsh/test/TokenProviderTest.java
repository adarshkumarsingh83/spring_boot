/*
 * Copyright (c) 2015 Espark And ©Adarsh Development Services @copyright All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Espark nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.espark.adarsh.test;


import com.espark.adarsh.security.JwtTokenProvider;
import io.jsonwebtoken.lang.Assert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
public class TokenProviderTest extends ApplicationTests {

    @Autowired(required = true)
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void encryptAndDecryptToken(){
        final Map<String,Object> tokenData=new HashMap<String,Object>(){
            {
                put("userName","Adarsh");
                put("expire", DateTime.now().plusDays(1).getMillis());
            }
        };
        final String token = this.jwtTokenProvider.getTokenFromValue(tokenData);
        Assert.notNull(token,"Token is Empty");
        LOGGER.info("Token ",token);
        final Map<String,Object> tokenDataDecrypt=this.jwtTokenProvider.getTokenValue(token);
        Assert.notNull(token,"Token is Empty");
        LOGGER.info("Token Data ",tokenDataDecrypt);
    }
}
