package authentication;

import entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import request.AuthorizationException;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class JwtService {

  private String secret = "rFDtmeKMpxNC0BAuzz2vLWCJ0xtxgbQnlxsVwJbroAslQFVYBvw47zBsI2JktAB";

  private Map<String, String> userIdToTokenId = new ConcurrentHashMap<>();

  public String generateTokenFor(User user) {
    String tokenId = UUID.randomUUID().toString();
    String token = Jwts.builder()
      .claim("userId", Integer.toString(user.getId()))
      .claim("role", user.getRole().toString())
      .setId(tokenId)
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();

    String currentTokenId = userIdToTokenId.putIfAbsent(Integer.toString(user.getId()), tokenId);
    if (currentTokenId != null) {
      throw new AuthorizationException("User already logged in");
    }
    return token;
  }

  public Map<String, String> parse(String token) {
    try {
      Claims body = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();

      Map<String, String> claims = new HashMap<>();

      String userId = body.get("userId", String.class);
      claims.put("userId", userId);
      String role = body.get("role", String.class);
      claims.put("role", role);
      String tokenId = body.getId();

      String maybeTokenId = userIdToTokenId.get(userId);
      if (!tokenId.equals(maybeTokenId)) {
        throw new AuthorizationException("Deprecated token");
      }

      return claims;
    } catch(AuthorizationException ae) {
      throw ae;
    } catch (Exception ex) {
      throw new AuthorizationException("Cannot parse JWT token");
    }
  }

  public void logout(int userId) {
    userIdToTokenId.remove(Integer.toString(userId));
  }
}
