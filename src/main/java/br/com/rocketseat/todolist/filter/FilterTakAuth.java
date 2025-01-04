package br.com.rocketseat.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rocketseat.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTakAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // pegar a autenticação (usario/senha)

        var authorization = request.getHeader("Authorization");
        var user_password_encoded = authorization.substring("Basic".length()).trim();
        byte[] password_decoded = Base64.getDecoder().decode(user_password_encoded);
        var final_password = new String (password_decoded);

        String [] credentials = final_password.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println("<< Chegou na autorização >>");

        // validar usuário:
        var user = this.userRepository.findUserByUsername(username);

        if (user != null) { // validar a senha:

            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (passwordVerify.verified) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401);
            }

        } else {
            response.sendError(401, "Usuário não autorizado.");
        }


    }
}
