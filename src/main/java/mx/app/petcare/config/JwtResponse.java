package mx.app.petcare.config;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jwtToken;

    public JwtResponse() {
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
