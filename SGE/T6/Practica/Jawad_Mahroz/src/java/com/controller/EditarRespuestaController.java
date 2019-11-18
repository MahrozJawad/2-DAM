
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Respuesta;
import com.modelos.RespuestaValidar;
import com.modelos.marcas;
import com.modelos.modeloValidar;
import com.modelos.modelos;
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
@RequestMapping("editarModelo.htm")
public class EditarRespuestaController {

    modeloValidar validarModelo;
    private JdbcTemplate jdbcTemplate;
    
    public EditarRespuestaController() {
        this.validarModelo = new modeloValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int idmarca =  Integer.parseInt(request.getParameter("idMarca"));
        int idModelo = Integer.parseInt(request.getParameter("idModelo"));
        
        marcas datos = this.SelectMarcas(idmarca);
        modelos datosModelos = this.SelectModelos(idModelo);
        
        //mav.setViewName("editarModelo");
        
        mav.addObject("marcas", new marcas(datos.getIdMarca(),datos.getNombreMarca()));
        mav.addObject("modelos", datosModelos);
        
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("modelos") modelos mod,
                                BindingResult result,
                                SessionStatus status,
                                HttpServletRequest request) {
    this.validarModelo.validate(mod, result);
    
    if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            //mav.setViewName("editarModelo");
            mav.addObject("modelos", new modelos());
            
            int idMarca =  Integer.parseInt(request.getParameter("idMarca"));
            marcas datos = this.SelectMarcas(idMarca);
            mav.addObject("marcas", new marcas(datos.getIdMarca(),datos.getNombreMarca()));
            return  mav;
        } else {
            int id = Integer.parseInt(request.getParameter("idMarca"));
            int idMod = Integer.parseInt(request.getParameter("idModelo"));
    
            this.jdbcTemplate.update("update modelos set nombreModelo=?, stock=? where idModelo=?",
                                     mod.getNombreModelo(),mod.getStock(),idMod);
            return new ModelAndView("redirect:/consultar.htm?idMarca=" + id);
        }
    
    
    } 

    private marcas SelectMarcas(int id) {

        final marcas marca = new marcas();
        String sql = "SELECT * FROM marcas where idMarca = '"+id+"'";
        return (marcas) jdbcTemplate.query(sql, new ResultSetExtractor<marcas>(){
            public marcas extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    marca.setNombreMarca(rs.getString("nombreMarca"));
                    marca.setIdMarca(id);
                }
                return marca;
            }
        });
    }
    
    private modelos SelectModelos(int id) {
        final modelos model = new modelos();
        String sql = "SELECT * FROM modelos where idModelo = '"+id+"'";
        return (modelos) jdbcTemplate.query(sql, new ResultSetExtractor<modelos>(){
            public modelos extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    model.setNombreModelo(rs.getString("nombreModelo"));
                    model.setStock(rs.getInt("stock"));
                }
                return model;
            }
        });
    }
}
