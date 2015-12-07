package oh3ebf.demo.service;


import javax.ejb.Stateless;
import oh3ebf.demo.model.Users;


@Stateless
public class UserService extends DataAccessService<Users>{
    
    public UserService(){
        super(Users.class);
    }   
}
