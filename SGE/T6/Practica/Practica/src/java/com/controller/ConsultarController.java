
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Respuesta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
@RequestMapping("consultar.htm")
public class ConsultarController {
    
    private JdbcTemplate jdbcTemplate;
    
    public ConsultarController() {
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int idEncuesta =  Integer.parseInt(request.getParameter("idEncuesta"));
        Preguntas datos = this.SelectPreguntas(idEncuesta);
        
        String sql = "SELECT * FROM respuesta where idEncuesta = '"+idEncuesta+"'";
        List respuestas = jdbcTemplate.queryForList(sql);
        
        mav.setViewName("consultar");
        mav.addObject("pregunta", new Preguntas(idEncuesta,datos.getTextoPregunta()));
        mav.addObject("Respuestas", respuestas);
        return mav;
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
