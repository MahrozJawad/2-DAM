
package com.controller;

import com.modelos.Conectar;
import com.modelos.EncuestaValidar;
import com.modelos.Preguntas;
import com.modelos.marcaValidar;
import com.modelos.marcas;
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

    private marcaValidar validaMarca;
    private JdbcTemplate jdbcTemplate;
    
    public AddController() {
        this.validaMarca = new marcaValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("AgregarMarca", new marcas());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("marcas") marcas m,
                                BindingResult result,
                                SessionStatus status,
                                HttpServletRequest request) {
    this.validaMarca.validate(m, result);
    
    if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("AgregarMarca", new marcas());
            return  mav;
        } else {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("add");
                mav.addObject("marcas", new marcas());

                this.jdbcTemplate.update("insert into marcas (idMarca,nombreMarca) values (?,?)",
                                         m.getIdMarca(),m.getNombreMarca());
                return new ModelAndView("redirect:home.htm");
            }
        }
    } 
    

