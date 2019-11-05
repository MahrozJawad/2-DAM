
package com.controller;

import com.modelos.Conectar;
import com.modelos.Usuarios;
import com.modelos.UsuariosValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add.htm")
public class AddController {

    UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
    
    public AddController() {
        this.usuariosValidar = new UsuariosValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("usuarios", new Usuarios());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuarios") Usuarios usuario,
                                BindingResult result,
                                SessionStatus status) {
    this.usuariosValidar.validate(usuario, result);
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("usuarios", new Usuarios());
            return  mav;
        } else {
            this.jdbcTemplate.update("insert into usuarios (nombre,correo) values (?,?)",
                                     usuario.getNombre(),usuario.getCorreo());
            return new ModelAndView("redirect:/home.htm");
        }
    } 
    
}
