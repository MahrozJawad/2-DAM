
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.UsuariosValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        mav.addObject("AgregarEncuesta", new Preguntas());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("preguntas") Preguntas p) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("preguntas", new Preguntas());
            
            this.jdbcTemplate.update("insert into respuesta (idEncuesta,textoPregunta) values (?,?)",
                                     p.getId(),p.getTextoPregunta());
            return new ModelAndView("redirect:/home.htm");
        }
    } 
    

