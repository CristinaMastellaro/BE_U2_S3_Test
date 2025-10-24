package cristinamastellaro.BE_U2_S3_Test.security;

import cristinamastellaro.BE_U2_S3_Test.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Auth header presente e scritto bene
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            throw new UnauthorizedException("Check that the Authorization header is compiled correctly");

        // Se corretto, estraiamo il token
        String token = header.replace("Bearer ", "");

        // Verifichiamo che il token sia valido
        jwtTools.verifyToken(token);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        boolean isLogin = new AntPathMatcher().match("/login/**", request.getServletPath());
        boolean isTrial = new AntPathMatcher().match("/events/**", request.getServletPath());
        return isLogin || isTrial;
    }
}
