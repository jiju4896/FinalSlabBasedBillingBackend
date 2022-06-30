package com.slabBased.project.configuration;

import com.slabBased.project.entity.Token;
import com.slabBased.project.repository.TokenRepository;
import com.slabBased.project.services.Implementation.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public String HEADER_STRING = "Authorization";

    public String TOKEN_PREFIX = "Bearer";

    @Resource(name = "userService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private String authToken;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, java.io.IOException {

        String header = req.getHeader(HEADER_STRING);
        String username = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = (header.replace(TOKEN_PREFIX, "")).trim();

            try {
                List<Token> tokenList = tokenRepository.findAll();
                for (Token token : tokenList) {
                    if (passwordEncoder.matches(authToken, token.getTokenCopy())) {
                        userService.setTokenIdFromRequest(token.getTokenId());
                        username = jwtTokenProvider.getUsernameFromToken(authToken);
                        break;


                    }
                }


            } catch (IllegalArgumentException e) {
                logger.error("An error occurred while fetching Username from Token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("The token has expired", e);
            } catch (SignatureException e) {
                logger.error("Authentication Failed. Signature not valid.");
            }
        } else {
            logger.warn("Couldn't find bearer string, header will be ignored");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenProvider.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthenticationToken(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}
