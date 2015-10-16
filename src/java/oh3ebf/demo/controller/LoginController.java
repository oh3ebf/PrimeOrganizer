package oh3ebf.demo.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import oh3ebf.demo.service.UserService;

/**
 * Login Controller class allows only authenticated users to log in to the web
 * application.
 *
 * @author Emre Simtay <emre@simtay.com>
 */
//@Named
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    //@Inject
    //private transient Logger logger;
    @Inject
    private UserService service;

    private String username;
    private String password;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    //  Getters and Setters
    /**
     * @return username
     */
    public String getUsername() {
        // hack for invalidate session when browser is closed before logout
        invalidateSession();
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Listen for button clicks on the #{loginController.login} action,
     * validates the username and password entered by the user and navigates to
     * the appropriate page.
     *
     * @param actionEvent
     */
    public void login(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            String navigateString = "";
            // Checks if username and password are valid if not throws a ServletException
            request.login(username, password);
            /*
             // gets the user principle and navigates to the appropriate page            
             Principal principal = request.getUserPrincipal();
            
             if (request.isUserInRole("Administrator")) {
             navigateString = "/admin/AdminHome.xhtml";
             } else if (request.isUserInRole("Manager")) {
             navigateString = "/manager/ManagerHome.xhtml";
             } else if (request.isUserInRole("User")) {
             navigateString = "/user/UserHome.xhtml";
             }
             */
            navigateString = "/faces/application/index.xhtml";

            try {
                //logger.log(Level.INFO, "User ({0}) loging in #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {
                //logger.log(Level.SEVERE, "IOException, Login Controller" + "Username : " + principal.getName(), ex);
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        } catch (ServletException e) {
            //logger.log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }
    }

    /**
     * Listen for logout button clicks on the #{loginController.logout} action
     * and navigates to login screen.
     */
    public void logout() {
        invalidateSession();

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
    }

    /** Function invalidates session
     * 
     */
    private void invalidateSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //logger.log(Level.INFO, "User ({0}) loging out #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
        if (session != null) {
            session.invalidate();
        }
    }
}
