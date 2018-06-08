package request;

import entities.Role;
import lombok.Data;

import javax.enterprise.context.RequestScoped;

@RequestScoped
@Data
public class RequestContext {

  private int userId;
  private Role userRole;
}
