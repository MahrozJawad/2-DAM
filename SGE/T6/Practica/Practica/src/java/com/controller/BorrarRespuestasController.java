
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Respuesta;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("borrarRespuesta.htm")
public class BorrarRespuestasController {
    private JdbcTemplate jdbcTemplate;
    
    public BorrarRespuestasController() {
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int idEncuesta =  Integer.parseInt(request.getParameter("idEncuesta"));
        int idRespuesta = Integer.parseInt(request.getParameter("idRespuesta"));
        
        Preguntas datos = this.SelectPreguntas(idEncuesta);
        Respuesta datosRespuesta = this.SelectRespuestas(idRespuesta);
        
        mav.setViewName("borrarRespuesta");
        
        mav.addObject("preguntas", new Preguntas(datos.getId(),datos.getTextoPregunta()));
        mav.addObject("respuestas", datosRespuesta);
        
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("respuestas") Respuesta resp,
                                HttpServletRequest request) {
    
    int id = Integer.parseInt(request.getParameter("idEncuesta"));
    int idRespuesta = Integer.parseInt(request.getParameter("idRespuesta"));
    
            this.jdbcTemplate.update("delete from respuesta where idRespuesta=?",
                                     idRespuesta);
            return new ModelAndView("redirect:/consultar.htm?idEncuesta=" + id);
    } 

    private Preguntas SelectPreguntas(int id) {

        final Preguntas pregunta = new Preguntas();
        String sql = "SELECT * FROM encuesta where idEncuesta = '"+id+"'";
        return (Preguntas) jdbcTemplate.query(sql, new ResultSetExtractor<Preguntas>(){
            public Preguntas extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    pregunta.setTextoPregunta(rs.getString("textoPregunta"));
                    pregunta.setId(id);
                }
                return pregunta;
            }
        });
    }
    
    private Respuesta SelectRespuestas(int id) {
        final Respuesta respuesta = new Respuesta();
        String sql = "SELECT * FROM respuesta where idRespuesta = '"+id+"'";
        return (Respuesta) jdbcTemplate.query(sql, new ResultSetExtractor<Respuesta>(){
            public Respuesta extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    respuesta.setTextoRespuesta(rs.getString("textoRespuesta"));
                    respuesta.setNumeroRespuestas(rs.getInt("numeroRespuestas"));
                }
                return respuesta;
            }
        });
    }
}
