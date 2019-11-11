
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Usuarios;
import com.modelos.UsuariosValidar;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("edit.htm")
public class EditController {

    UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
    
    public EditController() {
        this.usuariosValidar = new UsuariosValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id =  Integer.parseInt(request.getParameter("idEncuesta"));
        Preguntas datos = this.SelectPreguntas(id);
        mav.setViewName("edit");
        mav.addObject("preguntas", new Preguntas(datos.getId(),datos.getTextoPregunta()));
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("preguntas") Preguntas preguntas,
                                HttpServletRequest request) {
    
    int id = Integer.parseInt(request.getParameter("idEncuesta"));
            this.jdbcTemplate.update("update encuesta set textoPregunta=? where idEncuesta=?",
                                     preguntas.getTextoPregunta(),id);
            return new ModelAndView("redirect:/home.htm");
    } 

    private Preguntas SelectPreguntas(int id) {

        final Preguntas pregunta = new Preguntas();
        String sql = "SELECT * FROM encuesta where idEncuesta = '"+id+"'";
        return (Preguntas) jdbcTemplate.query(sql, new ResultSetExtractor<Preguntas>(){
            public Preguntas extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    pregunta.setTextoPregunta(rs.getString("textoPregunta"));
                }
                return pregunta;
            }
        });
    }
}
