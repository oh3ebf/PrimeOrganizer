/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package oh3ebf.demo.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author kimkr
 */
//@Named(value = "settingsController")
//@Dependent
@ManagedBean
@SessionScoped
public class SettingsController implements Serializable {

    /**
     * Creates a new instance of SettingsController
     */
    public SettingsController() {
    }
    
}
