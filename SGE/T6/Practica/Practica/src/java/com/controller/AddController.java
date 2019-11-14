
package com.controller;

import com.modelos.Conectar;
import com.modelos.EncuestaValidar;
import com.modelos.Preguntas;
import javax.servlet.http.HttpServletRequest;
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

    EncuestaValidar encuestaValidar;
    private JdbcTemplate jdbcTemplate;
    
    public AddController() {
        this.encuestaValidar = new EncuestaValidar();
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
    public ModelAndView form(@ModelAttribute("preguntas") Preguntas p,
                                BindingResult result,
                                SessionStatus status,
                                HttpServletRequest request) {
    this.encuestaValidar.validate(p, result);
    
    if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("AgregarEncuesta", new Preguntas());
            return  mav;
        } else {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("add");
                mav.addObject("preguntas", new Preguntas());

                this.jdbcTemplate.update("insert into encuesta (idEncuesta,textoPregunta) values (?,?)",
                                         p.getId(),p.getTextoPregunta());
                return new ModelAndView("redirect:/home.htm");
            }
        }
    } 
    

