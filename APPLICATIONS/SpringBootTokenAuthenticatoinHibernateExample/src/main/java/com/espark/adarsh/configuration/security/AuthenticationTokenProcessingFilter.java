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

package com.espark.adarsh.configuration.security;


import com.espark.adarsh.persistence.entites.impl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    private static Logger LOG = LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);

    @Autowired(required = true)
    private TokenProvider tokenProvider;

    private TokenService tokenService;

    @Autowired(required = true)
    private AuthenticationManager authenticationManager;

    private SecurityContextProvider securityContextProvider  = new SecurityContextProvider();

    private WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();

    public AuthenticationTokenProcessingFilter(AuthenticationManager authenticationManager,final TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService=tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOG.debug("Checking headers and parameters for authentication token...");

        String token = null;

        final HttpServletRequest httpServletRequest = this.asHttpServletRequest(request);
        if (httpServletRequest.getHeader("Authentication-token") != null) {
            token = httpServletRequest.getHeader("Authentication-token");
            LOG.debug("Found token '" + token + "' in request headers");
        }

        if (token != null) {
            if(this.tokenService.contains(token)){
                final SecurityContext securityContext = this.securityContextProvider.getSecurityContext();
                securityContext.setAuthentication(this.tokenService.retrieve(token));
            }else if (tokenProvider.isTokenValid(token)) {
                final User user = tokenProvider.getUserFromToken(token);
                LOG.debug("Inside-AuthenticationTokenProcessingFilter.java");
                this.authenticateUser(httpServletRequest, user,token);
            }
        }
        chain.doFilter(request, response);
    }

    private HttpServletRequest asHttpServletRequest(ServletRequest servletRequest){
        return (HttpServletRequest)servletRequest;
    }

    private HttpServletResponse asHttpServletResponse(ServletResponse servletResponse){
        return (HttpServletResponse)servletResponse;
    }

    private void authenticateUser(HttpServletRequest request, User user,String token) {
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPwd());
        authentication.setDetails(this.webAuthenticationDetailsSource.buildDetails(request));
        final SecurityContext securityContext = this.securityContextProvider.getSecurityContext();
        final Authentication authentication1=this.authenticationManager.authenticate(authentication);
        securityContext.setAuthentication(authentication);
        this.tokenService.store(token,authentication);
    }
}
